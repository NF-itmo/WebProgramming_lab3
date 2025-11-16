package org.web3.services.checker;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import org.web3.models.Point;
import org.web3.models.User;
import org.web3.repository.PointRepository;
import org.web3.services.checker.DTOs.CheckRequest;
import org.web3.services.checker.DTOs.CheckResultResponse;
import org.web3.services.checker.plot.Checker;
import org.web3.services.checker.plot.CheckerFunction;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@WebService
@HandlerChain(file = "/handlers/BearerAuthHandler.xml")
public class CheckerService {
    @Resource
    private WebServiceContext webServiceContext;

    private static final CheckerFunction checker = new Checker();
    private final PointRepository pointRepository = new PointRepository();
    private final Validator validator;

    public CheckerService() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    public CheckResultResponse check(
            @WebParam(name = "x") final float x,
            @WebParam(name = "y") final float y,
            @WebParam(name = "r") final float r
    ) {
        CheckRequest request = new CheckRequest(x, y, r);
        Set<ConstraintViolation<CheckRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Validation failed: " + errors);
        }

        MessageContext messageContext = webServiceContext.getMessageContext();
        DecodedJWT token = (DecodedJWT) messageContext.get("token");

        final Point point = Point.builder()
                .x(x)
                .y(y)
                .r(r)
                .isHitted(checker.check(x,y,r))
                .timestamp(OffsetDateTime.now())
                .user(
                        User.builder().id(token.getClaim("uid").asInt()).build()
                )
                .build();
        pointRepository.save(point);

        return new CheckResultResponse(
                point.isHitted(),
                point.getTimestamp()
        );
    }
}
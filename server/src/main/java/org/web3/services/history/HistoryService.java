package org.web3.services.history;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import org.web3.models.Point;
import org.web3.repository.PointRepository;
import org.web3.services.history.DTOs.PointHistoryResponse;
import org.web3.services.history.DTOs.PointHistoryResponseArray;

import java.util.List;

@WebService
@HandlerChain(file = "/handlers/BearerAuthHandler.xml")
public class HistoryService {
    @Resource
    private WebServiceContext webServiceContext;

    private final PointRepository pointRepository = new PointRepository();

    public PointHistoryResponseArray get(
            @WebParam(name = "start") final int start,
            @WebParam(name = "length") final int length
    ) {
        MessageContext messageContext = webServiceContext.getMessageContext();
        DecodedJWT token = (DecodedJWT) messageContext.get("token");

        int userId = token.getClaim("uid").asInt();

        List<Point> points = pointRepository.getByUserId(userId, length, start);

        return new PointHistoryResponseArray(
                points.stream()
                    .map(p -> new PointHistoryResponse(
                        p.getX(),
                        p.getY(),
                        p.getR(),
                        p.isHitted(),
                        p.getTimestamp()
                ))
                .toArray(PointHistoryResponse[]::new)
        );
    }

}


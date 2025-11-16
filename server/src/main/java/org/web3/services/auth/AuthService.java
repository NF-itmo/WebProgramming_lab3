package org.web3.services.auth;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.web3.models.User;
import org.web3.repository.UserRepository;
import org.web3.services.JWTAuth.StrictJWTBuilderFabric;
import org.web3.services.auth.DTOs.TokenResponse;

@WebService
public class AuthService {
    private final UserRepository userRepository = new UserRepository();
    private final StrictJWTBuilderFabric JWTBuilderFabric = new StrictJWTBuilderFabric(
            "AuthService",
            360_000
    );

    public TokenResponse login(
            @WebParam(name = "login") final String login,
            @WebParam(name = "password") final String password
    ) {
        final User user = userRepository.getByUsername(login);
        boolean check = BCrypt.checkpw(password, user.getPassword());
        if (check) {
            return new TokenResponse(
                    JWTBuilderFabric.newTokenBuilder(user.getUsername())
                            .addKey("uid", user.getId())
                            .buildToken()
            );
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    public TokenResponse register(
            @WebParam(name = "login") final String login,
            @WebParam(name = "password") final String password
    ) {
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        final User user = User.builder()
                .password(hashed_password)
                .username(login)
                .build();
        userRepository.save(user);

        return new TokenResponse(
                JWTBuilderFabric.newTokenBuilder(user.getUsername())
                        .addKey("uid", user.getId())
                        .buildToken()
        );
    }
}

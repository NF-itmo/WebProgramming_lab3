package org.web3.services.JWTAuth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class StrictJWTBuilderFabric {
    private final String issuer;
    private final int expirationDelta;
    private final Algorithm algorithm = JWTConfig.algorithm;


    public StrictJWTBuilderFabric(String issuer, int expirationDelta) {
        this.issuer = issuer;
        this.expirationDelta = expirationDelta;
    }

    public StrictJWTBuilder newTokenBuilder(String subject) {
        return new StrictJWTBuilder(
                JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(subject),
                expirationDelta
        );
    }
}

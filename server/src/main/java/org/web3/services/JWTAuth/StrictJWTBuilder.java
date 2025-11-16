package org.web3.services.JWTAuth;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.Date;

public class StrictJWTBuilder {
    private final Algorithm algorithm = JWTConfig.algorithm;
    private final JWTCreator.Builder tokenBuilder;
    private final int expirationDelta;

    StrictJWTBuilder(JWTCreator.Builder tokenBuilder, int expirationDelta) {
        this.tokenBuilder = tokenBuilder;
        this.expirationDelta = expirationDelta;
    }

    public StrictJWTBuilder addKey(String key, int value) {
        tokenBuilder.withClaim(key, value);
        return this;
    }

    public String buildToken() {
        return tokenBuilder.withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationDelta))
                .sign(algorithm);
    }
}

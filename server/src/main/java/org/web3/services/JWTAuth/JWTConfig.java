package org.web3.services.JWTAuth;

import com.auth0.jwt.algorithms.Algorithm;

class JWTConfig {
    public static final String secret = "mySuperSecretKey12345";
    public static final Algorithm algorithm = Algorithm.HMAC256(secret);
}

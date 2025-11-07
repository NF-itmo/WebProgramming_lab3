package org.web3.services.auth;

import jakarta.jws.WebService;
import org.web3.services.auth.DTOs.TokenResponse;

@WebService
public class AuthService {
    public TokenResponse login(final String login, final String password) {
        return new TokenResponse("token");
    }
    public TokenResponse register(final String login, final String password) {
        return new TokenResponse("token");
    }
}

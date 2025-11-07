package org.web3.services.checker;

import jakarta.jws.WebService;
import org.web3.services.checker.DTOs.CheckResultResponse;

@WebService
public class CheckerService {
    public CheckResultResponse check(final String x, final String y, final String r) {
        return new CheckResultResponse(true);
    }
}

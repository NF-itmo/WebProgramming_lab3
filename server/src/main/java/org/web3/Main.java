package org.web3;

import jakarta.xml.ws.Endpoint;
import org.web3.services.auth.AuthService;
import org.web3.services.checker.CheckerService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Starting web service...");

        Endpoint.publish(
                "http://0.0.0.0:8080/api/checker",
                new CheckerService()
        );
        Endpoint.publish(
                "http://0.0.0.0:8080/api/auth",
                new AuthService()
        );
    }
}
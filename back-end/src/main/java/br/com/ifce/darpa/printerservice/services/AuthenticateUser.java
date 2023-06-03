package br.com.ifce.darpa.printerservice.services;

public interface AuthenticateUser {

        Response execute(Request request);

        record Request(String email, String password) {

        }

        record Response(String token) {

        }
}

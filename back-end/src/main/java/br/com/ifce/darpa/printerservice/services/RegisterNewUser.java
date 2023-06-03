package br.com.ifce.darpa.printerservice.services;

public interface RegisterNewUser {

    Response execute(Request request);

     record Request(String firstName, String lastName, String email, String password) {

    }

    record Response(Long id, String firstName, String lastName, String email) {

    }
}

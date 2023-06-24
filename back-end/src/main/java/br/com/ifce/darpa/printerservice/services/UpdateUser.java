package br.com.ifce.darpa.printerservice.services;

public interface UpdateUser {

    Response execute(Long id, Request request);

    record Request(String lastName, String firstName, String email, String password) {

    }

    record Response(Long id, String lastName, String firstName, String email) {

    }
}

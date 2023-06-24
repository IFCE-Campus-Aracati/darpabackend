package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Role;

public interface SearchUser {

    Response execute(Request request);

    record Request(String email) {

    }

    record Response(Long id, String firstName, String lastName, String email, Role role) {

    }
}

package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Role;

public interface AuthenticateUser {

        Response execute(Request request);

        record Request(String email, String password) {

        }

        record UserDetails(String firstName, String lastName, String email) {

        }

        record Response(Role role, String token, UserDetails user) {

        }
}

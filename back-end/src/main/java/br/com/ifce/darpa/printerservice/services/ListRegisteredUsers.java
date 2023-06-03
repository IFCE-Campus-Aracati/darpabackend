package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Role;
import org.springframework.data.domain.Page;

public interface ListRegisteredUsers {

    Page<Response> execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record Response(Long id, String firstName, String lastName, String email, Role role) {

    }
}

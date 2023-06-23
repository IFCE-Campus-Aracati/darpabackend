package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Role;

import java.util.List;

public interface ListRegisteredUsers {

    Response execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record UserDetails(Long id, String name, String email, Role role) {

    }

    record Response(Long totalItems, List<UserDetails> users,
                    Integer totalPages, Integer currentPage) {

    }
}

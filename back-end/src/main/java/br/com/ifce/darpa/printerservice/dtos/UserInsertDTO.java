package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.Role;
import br.com.ifce.darpa.printerservice.models.User;

import java.util.Set;

public record UserInsertDTO(String name, String email, Set<Role> roles, String password) {

    public UserInsertDTO(User user) {
        this(user.getName(), user.getEmail(), user.getRoles(), user.getPassword());
    }
}

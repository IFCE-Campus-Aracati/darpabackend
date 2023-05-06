package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.Role;
import br.com.ifce.darpa.printerservice.models.User;

import java.util.Set;

public record UserDTO(Long id, String name, String email, Set<Role> roles) {

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }
}

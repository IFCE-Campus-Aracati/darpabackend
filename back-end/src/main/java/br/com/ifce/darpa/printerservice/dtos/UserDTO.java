package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.Role;
import br.com.ifce.darpa.printerservice.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserDTO(
        Long id,
        @NotBlank(message = "Campo nome é obrigatório")
        String name,
        @NotBlank(message = "Campo email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotNull(message = "Campo roles é obrigatório")
        Set<Role> roles) {

    public UserDTO(User user, Set<Role> roles) {
        this(user.getId(), user.getName(), user.getEmail(), roles);
    }
}

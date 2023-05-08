package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.Role;
import br.com.ifce.darpa.printerservice.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record UserInsertDTO(
        @NotBlank(message = "Campo nome é obrigatório")
        String name,
        @NotBlank(message = "Campo email é obrigatório")
        String email,
        @NotNull(message = "Campo roles é obrigatório")
        Set<Role> roles,
        @NotBlank(message = "Campo password é obrigatório")
          @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
                  message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula e um número")
        String password) {


    public UserInsertDTO(User user) {
        this(user.getName(), user.getEmail(), user.getRoles(), user.getPassword());
    }
}

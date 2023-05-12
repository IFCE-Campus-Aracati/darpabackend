package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,
        @NotBlank(message = "Campo nome é obrigatório")
        String firstName,
        @NotBlank(message = "Campo sobrenome é obrigatório")
        String lastName,
        @NotBlank(message = "Campo email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Campo roles é obrigatório")
        String roles) {

    public UserDTO(User user ) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name());
    }
}

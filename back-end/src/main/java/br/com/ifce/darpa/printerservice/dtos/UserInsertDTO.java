package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@UserInsertValid
public record UserInsertDTO(
        @NotBlank(message = "Campo nome é obrigatório")
        String firstName,

        @NotBlank(message = "Campo sobrenome é obrigatório")
        String lastName,
        @NotBlank(message = "Campo email é obrigatório")
        String email,
        @NotNull(message = "Campo roles é obrigatório")
        String role,
        @NotBlank(message = "Campo password é obrigatório")
          @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
                  message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula e um número")
        String password) {


    public UserInsertDTO(User user) {
        this(user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name(), user.getPassword());
    }
}

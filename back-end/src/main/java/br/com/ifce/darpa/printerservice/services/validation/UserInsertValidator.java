package br.com.ifce.darpa.printerservice.services.validation;

import br.com.ifce.darpa.printerservice.dtos.UserInsertDTO;
import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Optional<User> user = userRepository.findByEmail(dto.email());

        if(user.isPresent()) {
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
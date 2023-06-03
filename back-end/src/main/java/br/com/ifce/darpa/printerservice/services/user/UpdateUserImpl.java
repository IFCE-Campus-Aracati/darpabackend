package br.com.ifce.darpa.printerservice.services.user;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class UpdateUserImpl implements UpdateUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response execute(Long id, UpdateUser.Request request) {
        var target = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "user with id %s not found".formatted(id)
                ));

        validateNewValueAndExecuteUpdate(request.firstName(), validStringInput, target::setFirstName);
        validateNewValueAndExecuteUpdate(request.lastName(), validStringInput, target::setLastName);
        validateNewValueAndExecuteUpdate(request.email(), validEmail, target::setEmail);
        validateNewValueAndExecuteUpdate(request.password(), validPassWord, target::setPassword);

        userRepository.save(target);

        return new UpdateUser.Response(
                target.getId(),
                target.getFirstName(),
                target.getLastName(),
                target.getEmail()
        );

    }



    private final Predicate<String> validStringInput = input -> !input.isBlank() && !input.isEmpty();

    private final Predicate<String> validEmail = email -> email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private final Predicate<String> validPassWord = password -> password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=?-]).{8,}$");

    private <T> void validateNewValueAndExecuteUpdate(T newValue, Predicate<T> validation, Consumer<T> updateFunction) {
        if (Objects.nonNull(newValue) && validation.test(newValue)) {
            updateFunction.accept(newValue);
        }
    }
}

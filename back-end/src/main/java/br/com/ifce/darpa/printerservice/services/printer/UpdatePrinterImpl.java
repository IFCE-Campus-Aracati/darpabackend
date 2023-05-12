package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.UpdatePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class UpdatePrinterImpl implements UpdatePrinter {

    @Autowired
    private PrinterRepository printerRepository;

    @Override
    public void execute(UpdatePrinter.Request request) {
        var target = printerRepository.findById(request.id())
                .orElseThrow(() -> new NotFoundException(
                        "printer with id %d not found".formatted(request.id())
                ));

        validateNewValueAndExecuteUpdate(
                request.name(),
                input -> !input.isBlank() && !input.isEmpty(),
                target::setName
        );

        printerRepository.save(target);
    }

    private <T> void validateNewValueAndExecuteUpdate(T newValue, Predicate<T> validation, Consumer<T> updateFunction) {
        if (Objects.nonNull(newValue) && validation.test(newValue)) {
            updateFunction.accept(newValue);
        }
    }
}

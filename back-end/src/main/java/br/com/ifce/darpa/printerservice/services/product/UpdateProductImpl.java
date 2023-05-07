package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class UpdateProductImpl implements UpdateProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void execute(UpdateProduct.Request request) {
        var target = productRepository.findById(request.id())
                .orElseThrow(() -> new NotFoundException(
                        "product with id %s not found".formatted(request.id())
                ));

        validateNewValueAndExecuteUpdate(request.name(), validStringInput, target::setName);
        validateNewValueAndExecuteUpdate(request.description(), validStringInput, target::setDescription);
        validateNewValueAndExecuteUpdate(request.quantity(), validQuantity, target::setQuantity);

        productRepository.save(target);
    }

    private final Predicate<String> validStringInput = input -> !input.isBlank() && !input.isEmpty();
    private final Predicate<BigInteger> validQuantity = quantity -> quantity.compareTo(BigInteger.ZERO) >= 0;

    private <T> void validateNewValueAndExecuteUpdate(T newValue, Predicate<T> validation, Consumer<T> updateFunction) {
        if (Objects.nonNull(newValue) && validation.test(newValue)) {
            updateFunction.accept(newValue);
        }
    }
}

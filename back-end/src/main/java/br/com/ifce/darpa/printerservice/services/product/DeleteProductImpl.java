package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.DeleteProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductImpl implements DeleteProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void execute(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new NotFoundException(
                                    "product with id %d not found".formatted(id)
                            );
                        }
                );
    }
}

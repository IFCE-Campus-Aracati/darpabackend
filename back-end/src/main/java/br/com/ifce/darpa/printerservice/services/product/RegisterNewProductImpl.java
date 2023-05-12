package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewProductImpl implements RegisterNewProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public RegisterNewProduct.Response execute(RegisterNewProduct.Request request) {
        var savedProduct = productRepository.save(requestToProduct(request));
        return new RegisterNewProduct.Response(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getQuantity()
        );
    }

    private Product requestToProduct(RegisterNewProduct.Request request) {
        return new Product(null, request.name(), request.description(), request.quantity());
    }
}

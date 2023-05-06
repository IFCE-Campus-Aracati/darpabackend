package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.ProductNotFoundException;
import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.SearchProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchProductImpl implements SearchProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Response execute(Request request) {
        return productRepository
                .findByName(request.name())
                .map(this::productToResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        "product with name %s not found".formatted(request.name())
                ));
    }

    private SearchProduct.Response productToResponse(Product product) {
        return new SearchProduct.Response(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity()
        );
    }
}

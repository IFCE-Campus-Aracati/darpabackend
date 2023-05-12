package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredProductsImpl implements ListRegisteredProducts {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ListRegisteredProducts.Response> execute(ListRegisteredProducts.Request request) {
        return productRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(this::productToResponse);
    }

    private ListRegisteredProducts.Response productToResponse(Product product) {
        return new ListRegisteredProducts.Response(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity()
        );
    }
}

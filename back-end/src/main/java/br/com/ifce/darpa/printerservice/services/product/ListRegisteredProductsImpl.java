package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredProductsImpl implements ListRegisteredProducts {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ListRegisteredProducts.Response execute(ListRegisteredProducts.Request request) {
        var pageOfProducts = productRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(product -> new ProductDetails(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity()
                ));

        return new Response(
                pageOfProducts.getTotalElements(),
                pageOfProducts.getContent(),
                pageOfProducts.getTotalPages(),
                pageOfProducts.getNumber()
        );
    }
}

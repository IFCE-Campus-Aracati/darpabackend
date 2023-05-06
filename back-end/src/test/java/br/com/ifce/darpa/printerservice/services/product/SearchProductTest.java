package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.ProductNotFoundException;
import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.SearchProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SearchProductTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private SearchProductImpl searchProduct;

    @Test
    void givenAValidProductNameShouldReturnTheProduct() {
        String productName = "Filamento ABS";
        Product product = new Product(1L, productName, "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);

        Mockito.when(productRepository.findByName(productName)).thenReturn(Optional.of(product));

        SearchProduct.Response response = searchProduct.execute(new SearchProduct.Request(productName));

        Assertions.assertEquals(product.getId(), response.id());
        Assertions.assertEquals(product.getName(), response.name());
        Assertions.assertEquals(product.getDescription(), response.description());
        Assertions.assertEquals(product.getQuantity(), response.quantity());
    }

    @Test
    void givenAnInvalidProductNameShouldThrowProductNotFoundException() {
        String productName = "Non-existent product";

        Mockito.when(productRepository.findByName(productName)).thenReturn(Optional.empty());

        SearchProduct.Request request = new SearchProduct.Request(productName);

        Exception exception = Assertions.assertThrows(ProductNotFoundException.class, () -> searchProduct.execute(request));

        Assertions.assertEquals("product with name Non-existent product not found", exception.getMessage());
    }
}

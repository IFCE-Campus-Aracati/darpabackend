package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@ExtendWith(MockitoExtension.class)
class RegisterNewProductTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private RegisterNewProductImpl registerNewProduct;

    @Test
    void givenProductToAddShouldReturnAddedProduct() {
        var request = new RegisterNewProduct.Request("Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);
        var savedProduct = new Product(1L, "Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(savedProduct);

        var response = registerNewProduct.execute(request);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(savedProduct.getId(), response.id());
        Assertions.assertEquals(savedProduct.getName(), response.name());
        Assertions.assertEquals(savedProduct.getDescription(), response.description());
        Assertions.assertEquals(savedProduct.getQuantity(), response.quantity());
    }
}

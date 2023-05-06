package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.ProductNotFoundException;
import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.UpdateProduct;
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
class UpdateProductTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private UpdateProductImpl updateProduct;

    @Test
    void givenAValidProductIdAndDetailsShouldUpdateTheProduct() {
        Long validId = 1L;

        Product productToUpdate = new Product(validId, "Product Name", "Product Description", BigInteger.valueOf(10));

        Mockito.when(productRepository.findById(validId)).thenReturn(Optional.of(productToUpdate));
        Mockito.when(productRepository.save(productToUpdate)).thenReturn(productToUpdate);

        UpdateProduct.Request request = new UpdateProduct.Request(validId, "New Name", "New Description", BigInteger.valueOf(5));
        updateProduct.execute(request);

        Mockito.verify(productRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(productRepository, Mockito.times(1)).save(productToUpdate);

        Assertions.assertEquals("New Name", productToUpdate.getName());
        Assertions.assertEquals("New Description", productToUpdate.getDescription());
        Assertions.assertEquals(BigInteger.valueOf(5), productToUpdate.getQuantity());
    }

    @Test
    void givenAnInvalidProductIdShouldThrowProductNotFoundException() {
        Long invalidId = 1L;

        Mockito.when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        UpdateProduct.Request request = new UpdateProduct.Request(invalidId, null, null, null);

        Exception exception = Assertions.assertThrows(ProductNotFoundException.class, () -> updateProduct.execute(request));

        Assertions.assertEquals("product with id 1 not found", exception.getMessage());
    }
}

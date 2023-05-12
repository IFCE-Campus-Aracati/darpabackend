package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeleteProductTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private DeleteProductImpl deleteProduct;

    @Test
    void givenAValidProductIdShouldDeleteTheProduct() {
        Long validId = 1L;

        Mockito.when(productRepository.findById(validId)).thenReturn(Optional.of(new Product()));

        deleteProduct.execute(validId);

        Mockito.verify(productRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(productRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void givenAnInvalidProductIdShouldThrowNotFoundException() {
        Long invalidId = 1L;

        Mockito.when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> deleteProduct.execute(invalidId));

        Assertions.assertEquals("product with id 1 not found", exception.getMessage());

        Mockito.verify(productRepository, Mockito.times(1)).findById(invalidId);
        Mockito.verify(productRepository, Mockito.never()).delete(new Product());
    }
}

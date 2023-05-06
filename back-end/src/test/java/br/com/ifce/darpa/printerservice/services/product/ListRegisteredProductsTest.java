package br.com.ifce.darpa.printerservice.services.product;

import br.com.ifce.darpa.printerservice.models.Product;
import br.com.ifce.darpa.printerservice.repositories.ProductRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredProducts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ListRegisteredProductsTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private ListRegisteredProductsImpl listRegisteredProducts;

    @Test
    void givenAPageRequestShouldReturnAPageOfProducts() {
        Product product1 = new Product(1L, "Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);
        Product product2 = new Product(2L, "Filamento PLA", "Filamento PLA Standard para impressora 3D 1kg 1,75mm (Preto)", BigInteger.ONE);
        Product product3 = new Product(3L, "Filamento PETG", "Filamento PETG para impressora 3D 1kg 1,75mm (Transparente)", BigInteger.ONE);

        PageImpl<Product> page = new PageImpl<>(Arrays.asList(product1, product2, product3), PageRequest.of(0, 2), 3L);

        Mockito.when(productRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        ListRegisteredProducts.Request request = new ListRegisteredProducts.Request(0, 2);

        Page<ListRegisteredProducts.Response> resultPage = listRegisteredProducts.execute(request);

        Assertions.assertEquals(2, resultPage.getSize());
        Assertions.assertEquals(3, resultPage.getTotalElements());
        Assertions.assertEquals(2, resultPage.getTotalPages());
        Assertions.assertEquals(product1.getName(), resultPage.getContent().get(0).name());
        Assertions.assertEquals(product2.getName(), resultPage.getContent().get(1).name());
    }
}

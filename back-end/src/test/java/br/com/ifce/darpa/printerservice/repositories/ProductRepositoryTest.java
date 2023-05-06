package br.com.ifce.darpa.printerservice.repositories;


import br.com.ifce.darpa.printerservice.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void givenProductToAddShouldReturnAddedProduct() {
        var product = new Product(null, "Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);

        productRepository.save(product);

        var fetchedProduct = productRepository.findById(product.getId()).orElse(null);

        Assertions.assertNotNull(fetchedProduct);
        Assertions.assertNotNull(fetchedProduct.getId());
        Assertions.assertEquals(product.getName(), fetchedProduct.getName());
        Assertions.assertEquals(product.getDescription(), fetchedProduct.getDescription());
    }

    @Test
    void givenAllProductsShouldReturnListOfAllProducts() {
        var listOfProductsToSave = List.of(
                new Product(null, "Filamento PLA", "Filament PLA Premium para Impressora 3D 1,75mm 1kg (Preto)", BigInteger.TEN),
                new Product(null, "Placa MDF", "MDF cru 3mm (Verde)", BigInteger.valueOf(5))
        );

        productRepository.saveAll(listOfProductsToSave);

        var fetchedProductList = productRepository.findAll();

        Assertions.assertTrue(fetchedProductList.containsAll(listOfProductsToSave));
    }

    @Test
    void givenIdThenShouldReturnProductOfThatId() {
        var product = new Product(null, "Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);
        var savedProduct = productRepository.save(product);

        var fetchedProduct = productRepository.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(fetchedProduct);
        Assertions.assertEquals(savedProduct.getId(), fetchedProduct.getId());
        Assertions.assertEquals(savedProduct.getName(), fetchedProduct.getName());
        Assertions.assertEquals(savedProduct.getDescription(), fetchedProduct.getDescription());
    }

    @Test
    void givenIdToDeleteThenShouldDeleteTheProduct() {
        var product = new Product(null, "Filamento ABS", "Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)", BigInteger.ONE);
        var savedProduct = productRepository.save(product);
        productRepository.deleteById(savedProduct.getId());

        var fetchedProduct = productRepository.findById(savedProduct.getId());

        Assertions.assertEquals(Optional.empty(), fetchedProduct);
    }
}

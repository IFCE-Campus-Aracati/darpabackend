package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.ListRegisteredProducts;
import br.com.ifce.darpa.printerservice.services.RegisterNewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductResource {

    @Autowired
    private ListRegisteredProducts listRegisteredProducts;

    @Autowired
    private RegisterNewProduct registerNewProduct;

    @GetMapping
    public ResponseEntity<ListRegisteredProducts.Response> listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var products = listRegisteredProducts.execute(new ListRegisteredProducts.Request(page, size));
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<RegisterNewProduct.Response> registerProduct(@RequestBody RegisterNewProduct.Request request) {
        registerNewProduct.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

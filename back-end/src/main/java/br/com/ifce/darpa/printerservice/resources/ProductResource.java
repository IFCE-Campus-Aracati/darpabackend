package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.ListRegisteredProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductResource {

    @Autowired
    private ListRegisteredProducts listRegisteredProducts;

    @GetMapping
    public ResponseEntity<ListRegisteredProducts.Response> listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var products = listRegisteredProducts.execute(new ListRegisteredProducts.Request(page, size));
        return ResponseEntity.ok().body(products);
    }
}

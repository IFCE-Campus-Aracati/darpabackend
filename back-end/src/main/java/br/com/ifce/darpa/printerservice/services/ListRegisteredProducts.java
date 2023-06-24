package br.com.ifce.darpa.printerservice.services;

import java.math.BigInteger;
import java.util.List;

public interface ListRegisteredProducts {
    Response execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record ProductDetails(Long id, String name, String description,
                          BigInteger quantity) {
    }

    record Response(Long totalItems, List<ProductDetails> products,
                    Integer totalPages, Integer currentPage) {

    }
}

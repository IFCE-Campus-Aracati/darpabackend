package br.com.ifce.darpa.printerservice.services;

import org.springframework.data.domain.Page;

import java.math.BigInteger;

public interface ListRegisteredProducts {
    Page<Response> execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record Response(Long id, String name, String description,
                    BigInteger quantity) {

    }
}

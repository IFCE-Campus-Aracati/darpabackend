package br.com.ifce.darpa.printerservice.services;

import java.math.BigInteger;

public interface SearchProduct {
    Response execute(Request request);

    record Request(String name) {

    }

    record Response(Long id, String name, String description,
                    BigInteger quantity) {

    }
}

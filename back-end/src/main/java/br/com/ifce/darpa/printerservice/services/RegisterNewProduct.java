package br.com.ifce.darpa.printerservice.services;

import java.math.BigInteger;

public interface RegisterNewProduct {
    Response execute(Request request);

    record Request(String name, String description, BigInteger quantity) {

    }

    record Response(Long id, String name, String description,
                    BigInteger quantity) {

    }
}

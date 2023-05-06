package br.com.ifce.darpa.printerservice.services;

import java.math.BigInteger;

public interface UpdateProduct {
    void execute(Request request);

    record Request(Long id, String name, String description,
                   BigInteger quantity) {

    }
}

package br.com.ifce.darpa.printerservice.services;

public interface RegisterNewPrinter {
    Response execute(Request request);

    record Request(String name) {

    }

    record Response(Long id, String name) {

    }
}

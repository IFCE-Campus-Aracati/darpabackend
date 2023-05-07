package br.com.ifce.darpa.printerservice.services;

public interface UpdatePrinter {
    void execute(Request request);

    record Request(Long id, String name) {

    }
}

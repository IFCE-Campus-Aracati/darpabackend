package br.com.ifce.darpa.printerservice.services;

import org.springframework.data.domain.Page;

public interface ListRegisteredPrinters {
    Page<Response> execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record Response(Long id, String name) {

    }
}

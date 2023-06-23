package br.com.ifce.darpa.printerservice.services;

import java.util.List;

public interface ListRegisteredPrinters {
    Response execute(Request request);

    record Request(int pageNumber, int pageSize) {

    }

    record PrinterDetails(Long id, String name) {

    }

    record Response(Long totalItems, List<PrinterDetails> printers,
                    Integer totalPages, Integer currentPage) {

    }
}

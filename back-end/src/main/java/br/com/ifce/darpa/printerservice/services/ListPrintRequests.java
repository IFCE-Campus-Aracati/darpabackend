package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Status;

import java.time.LocalDate;
import java.util.List;

public interface ListPrintRequests {
    Response execute(Request request);

    record Request(int page, int size) {

    }

    record RequestDetails(Long id, String name, LocalDate date, String description,
                          Status status) {
    }

    record Response(Long totalItems, List<RequestDetails> requests, Integer totalPages, Integer currentPage) {

    }
}

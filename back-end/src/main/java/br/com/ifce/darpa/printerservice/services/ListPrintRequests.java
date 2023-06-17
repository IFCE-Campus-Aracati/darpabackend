package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Status;

import java.time.LocalDate;
import java.util.List;

public interface ListPrintRequests {
    Response execute(Request request);

    record Request(Long userId) {

    }

    record RequestDetails(Long id, LocalDate date, String description,
                          Status status) {
    }

    record Response(List<RequestDetails> requests) {

    }
}

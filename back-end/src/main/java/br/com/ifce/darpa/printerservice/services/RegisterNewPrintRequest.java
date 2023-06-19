package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Status;

import java.time.LocalDate;

public interface RegisterNewPrintRequest {
    Response execute(Request request);

    record Request(String name, byte[] file, String description, LocalDate date) {

    }

    record Response(Long printRequestId, Long userId, Status status) {

    }
}

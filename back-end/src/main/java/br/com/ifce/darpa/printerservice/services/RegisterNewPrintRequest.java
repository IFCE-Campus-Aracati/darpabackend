package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Status;

public interface RegisterNewPrintRequest {
    Response execute(Request request);

    record Request(Long userId, byte[] file) {

    }

    record Response(Long printRequestId, Long userId, Status status) {

    }
}

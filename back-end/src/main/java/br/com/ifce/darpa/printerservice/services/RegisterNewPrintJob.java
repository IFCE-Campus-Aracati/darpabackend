package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.Status;

public interface RegisterNewPrintJob {
    Response execute(Request request);

    record Request(PrintRequest printRequest, Status status) {

    }

    record Response(Long id, Status status) {

    }
}

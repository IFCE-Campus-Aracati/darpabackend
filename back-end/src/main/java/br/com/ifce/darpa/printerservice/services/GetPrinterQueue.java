package br.com.ifce.darpa.printerservice.services;

import br.com.ifce.darpa.printerservice.models.Role;

import java.util.List;

public interface GetPrinterQueue {
    Response execute(Request request);

    record Request(String printerName, int pageNumber, int pageSize) {

    }

    record JobDetails(Long id, String name, String owner, Role role,
                      String description) {
    }

    record Response(Long totalItems, List<JobDetails> jobs,
                    Integer totalPages, Integer currentPage) {

    }
}

package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredPrinters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredPrintersImpl implements ListRegisteredPrinters {

    @Autowired
    private PrinterRepository printerRepository;

    @Override
    public Page<ListRegisteredPrinters.Response> execute(ListRegisteredPrinters.Request request) {
        return printerRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(this::printerToResponse);
    }

    private ListRegisteredPrinters.Response printerToResponse(Printer printer) {
        return new ListRegisteredPrinters.Response(
                printer.getId(),
                printer.getName()
        );
    }
}

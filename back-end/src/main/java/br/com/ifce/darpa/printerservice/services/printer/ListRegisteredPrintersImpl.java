package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredPrinters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListRegisteredPrintersImpl implements ListRegisteredPrinters {

    @Autowired
    private PrinterRepository printerRepository;

    @Override
    public ListRegisteredPrinters.Response execute(ListRegisteredPrinters.Request request) {
        var pageOfPrinters = printerRepository
                .findAll(PageRequest.of(request.pageNumber(), request.pageSize()))
                .map(printer -> new PrinterDetails(
                        printer.getId(),
                        printer.getName()
                ));

        return new Response(
                pageOfPrinters.getTotalElements(),
                pageOfPrinters.getContent(),
                pageOfPrinters.getTotalPages(),
                pageOfPrinters.getNumber()
        );
    }
}

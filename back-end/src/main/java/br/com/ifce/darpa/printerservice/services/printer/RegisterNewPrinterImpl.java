package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewPrinterImpl implements RegisterNewPrinter {

    @Autowired
    private PrinterRepository printerRepository;

    @Override
    public RegisterNewPrinter.Response execute(RegisterNewPrinter.Request request) {
        var savedPrinter = printerRepository.save(requestToPrinter(request));
        return new RegisterNewPrinter.Response(
                savedPrinter.getId(),
                savedPrinter.getName()
        );
    }

    private Printer requestToPrinter(RegisterNewPrinter.Request request) {
        return new Printer(null, request.name());
    }
}

package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.DeletePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePrinterImpl implements DeletePrinter {

    @Autowired
    private PrinterRepository printerRepository;

    @Override
    public void execute(Long id) {
        printerRepository.findById(id)
                .ifPresentOrElse(printerRepository::delete,
                        () -> {
                            throw new NotFoundException(
                                    "printer with id %d not found".formatted(id)
                            );
                        }
                );
    }
}

package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.UpdatePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UpdatePrinterTest {

    @Mock
    private PrinterRepository printerRepository;

    @Autowired
    @InjectMocks
    private UpdatePrinterImpl updatePrinter;

    @Test
    void givenAValidPrinterIdAndDetailsShouldUpdateThePrinter() {
        Long validId = 1L;

        Printer printerToUpdate = new Printer(validId, "Impressora 1");

        Mockito.when(printerRepository.findById(validId)).thenReturn(Optional.of(printerToUpdate));
        Mockito.when(printerRepository.save(printerToUpdate)).thenReturn(printerToUpdate);

        UpdatePrinter.Request request = new UpdatePrinter.Request(validId, "New Name");
        updatePrinter.execute(request);

        Mockito.verify(printerRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(printerRepository, Mockito.times(1)).save(printerToUpdate);

        Assertions.assertEquals("New Name", printerToUpdate.getName());
    }

    @Test
    void givenAnInvalidPrinterIdShouldThrowNotFoundException() {
        Long invalidId = 1L;

        Mockito.when(printerRepository.findById(invalidId)).thenReturn(Optional.empty());

        UpdatePrinter.Request request = new UpdatePrinter.Request(invalidId, "");

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> updatePrinter.execute(request));

        Assertions.assertEquals("printer with id 1 not found", exception.getMessage());
    }
}

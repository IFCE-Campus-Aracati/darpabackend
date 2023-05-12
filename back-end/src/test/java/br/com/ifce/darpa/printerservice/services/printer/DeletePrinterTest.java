package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
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
class DeletePrinterTest {

    @Mock
    private PrinterRepository printerRepository;

    @Autowired
    @InjectMocks
    private DeletePrinterImpl deletePrinter;

    @Test
    void givenAValidPrinterIdShouldDeleteThePrinter() {
        Long validId = 1L;

        Mockito.when(printerRepository.findById(validId)).thenReturn(Optional.of(new Printer()));

        deletePrinter.execute(validId);

        Mockito.verify(printerRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(printerRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void givenAnInvalidPrinterIdShouldThrowNotFoundException() {
        Long invalidId = 1L;

        Mockito.when(printerRepository.findById(invalidId)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> deletePrinter.execute(invalidId));

        Assertions.assertEquals("printer with id 1 not found", exception.getMessage());

        Mockito.verify(printerRepository, Mockito.times(1)).findById(invalidId);
        Mockito.verify(printerRepository, Mockito.never()).delete(new Printer());
    }
}

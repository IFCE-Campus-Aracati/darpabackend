package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class RegisterNewPrinterTest {

    @Mock
    private PrinterRepository printerRepository;

    @Autowired
    @InjectMocks
    private RegisterNewPrinterImpl registerNewPrinter;

    @Test
    void givenPrinterToAddShouldReturnAddedPrinter() {
        var request = new RegisterNewPrinter.Request("Impressora 1");
        var savedPrinter = new Printer(1L, "Impressora 1");

        Mockito.when(printerRepository.save(Mockito.any())).thenReturn(savedPrinter);

        var response = registerNewPrinter.execute(request);

        Mockito.verify(printerRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(savedPrinter.getId(), response.id());
        Assertions.assertEquals(savedPrinter.getName(), response.name());
    }
}

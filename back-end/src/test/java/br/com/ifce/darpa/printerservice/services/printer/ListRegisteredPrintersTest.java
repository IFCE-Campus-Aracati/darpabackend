package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.ListRegisteredPrinters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ListRegisteredPrintersTest {

    @Mock
    private PrinterRepository printerRepository;

    @Autowired
    @InjectMocks
    private ListRegisteredPrintersImpl listRegisteredPrinters;

    @Test
    void givenAPageRequestShouldReturnAPageOfPrinters() {
        Printer printer1 = new Printer(1L, "Impressora 1");
        Printer printer2 = new Printer(1L, "Impressora 2");
        Printer printer3 = new Printer(1L, "Impressora 3");

        PageImpl<Printer> page = new PageImpl<>(Arrays.asList(printer1, printer2, printer3), PageRequest.of(0, 2), 3L);

        Mockito.when(printerRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        ListRegisteredPrinters.Request request = new ListRegisteredPrinters.Request(0, 2);

        Page<ListRegisteredPrinters.Response> resultPage = listRegisteredPrinters.execute(request);

        Assertions.assertEquals(2, resultPage.getSize());
        Assertions.assertEquals(3, resultPage.getTotalElements());
        Assertions.assertEquals(2, resultPage.getTotalPages());
        Assertions.assertEquals(printer1.getName(), resultPage.getContent().get(0).name());
        Assertions.assertEquals(printer2.getName(), resultPage.getContent().get(1).name());
        Assertions.assertEquals(printer3.getName(), resultPage.getContent().get(2).name());
    }
}

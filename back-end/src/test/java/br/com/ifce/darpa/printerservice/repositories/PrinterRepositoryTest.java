package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import br.com.ifce.darpa.printerservice.models.Printer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@DataJpaTest
@ActiveProfiles("test")
class PrinterRepositoryTest {

    @Autowired
    private PrinterRepository printerRepository;

    private Printer printer;

    @BeforeEach
    void setUp() {
        printer = new Printer(null, "Printer Test");
    }

    @Test
    void givenPrinterToAddShouldReturnAddedPrinter() {
        printerRepository.save(printer);

        var fetchedPrinter = printerRepository.findById(printer.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrinter);
        Assertions.assertNotNull(fetchedPrinter.getId());
        Assertions.assertEquals(printer.getName(), fetchedPrinter.getName());
    }

    @Test
    void givenAllPrintersShouldReturnListOfAllPrinters() {
        var listOfPrintersToSave = Arrays.asList(
                new Printer(null, "Printer 1"),
                new Printer(null, "Printer 2")
        );

        printerRepository.saveAll(listOfPrintersToSave);

        var fetchedPrinterList = printerRepository.findAll();

        Assertions.assertTrue(fetchedPrinterList.containsAll(listOfPrintersToSave));
    }

    @Test
    void givenIdThenShouldReturnPrinterOfThatId() {
        printerRepository.save(printer);

        var fetchedPrinter = printerRepository.findById(printer.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrinter);
        Assertions.assertEquals(printer.getId(), fetchedPrinter.getId());
        Assertions.assertEquals(printer.getName(), fetchedPrinter.getName());
    }

    @Test
    void givenIdToDeleteThenShouldDeleteThePrinter() {
        printerRepository.save(printer);
        printerRepository.deleteById(printer.getId());

        var fetchedPrinter = printerRepository.findById(printer.getId()).orElse(null);

        Assertions.assertNull(fetchedPrinter);
    }

    @Test
    void givenPrinterWithPrintJobsThenShouldReturnPrinterWithPrintJobs() {
        var printJob1 = new PrintJob(null, printer);
        var printJob2 = new PrintJob(null, printer);

        printer.addJobs(Arrays.asList(printJob1, printJob2));
        printerRepository.save(printer);

        var fetchedPrinter = printerRepository.findById(printer.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrinter);
        Assertions.assertEquals(printer.getId(), fetchedPrinter.getId());
        Assertions.assertEquals(printer.getName(), fetchedPrinter.getName());
        Assertions.assertEquals(printer.getPrintJobs().size(), fetchedPrinter.getPrintJobs().size());
    }
}

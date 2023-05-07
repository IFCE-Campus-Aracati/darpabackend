package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.Printer;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class PrintJobRepositoryTest {

    @Autowired
    private PrintJobRepository printJobRepository;

    @Autowired
    private PrinterRepository printerRepository;

    @BeforeEach
    void setUp() {
        var printer1 = new Printer(null, "HP");
        var printer2 = new Printer(null, "Epson");

        var printJob1 = new PrintJob(null, printer1, Status.PENDING);
        var printJob2 = new PrintJob(null, printer2, Status.PENDING);
        var printJob3 = new PrintJob(null, printer2, Status.PENDING);

        var printRequest1 = new PrintRequest(null, "file1.pdf", new User(), printJob1);
        var printRequest2 = new PrintRequest(null, "file2.doc", new User(), printJob2);
        var printRequest3 = new PrintRequest(null, "file3.png", new User(), printJob3);

        printJob1.setPrintRequest(printRequest1);
        printJob2.setPrintRequest(printRequest2);
        printJob3.setPrintRequest(printRequest3);

        printJobRepository.saveAll(List.of(printJob1, printJob2, printJob3));
    }

    @Test
    void givenPrintJobToAddShouldReturnAddedPrintJob() {
        var printer = new Printer(null, "Canon");
        var printJob = new PrintJob(null, printer, Status.PENDING);

        printJobRepository.save(printJob);

        var fetchedPrintJob = printJobRepository.findById(printJob.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrintJob);
        Assertions.assertNotNull(fetchedPrintJob.getId());
        Assertions.assertEquals(printJob.getPrinter().getName(), fetchedPrintJob.getPrinter().getName());
    }

    @Test
    void givenAllPrintJobsShouldReturnListOfAllPrintJobs() {
        var listOfPrintJobsToSave = List.of(
                new PrintJob(null, new Printer(null, "Samsung"), Status.IN_PROGRESS),
                new PrintJob(null, new Printer(null, "Brother"), Status.WAITING)
        );

        printJobRepository.saveAll(listOfPrintJobsToSave);

        var fetchedPrintJobList = printJobRepository.findAll();

        Assertions.assertTrue(fetchedPrintJobList.containsAll(listOfPrintJobsToSave));
    }

    @Test
    void givenIdThenShouldReturnPrintJobOfThatId() {
        var printer = new Printer(null, "Xerox");
        var printJob = new PrintJob(null, printer, Status.COMPLETED);
        var savedPrintJob = printJobRepository.save(printJob);

        var fetchedPrintJob = printJobRepository.findById(savedPrintJob.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrintJob);
        Assertions.assertEquals(savedPrintJob.getId(), fetchedPrintJob.getId());
        Assertions.assertEquals(savedPrintJob.getPrinter().getName(), fetchedPrintJob.getPrinter().getName());
    }

    @Test
    void givenIdToDeleteThenShouldDeleteThePrintJob() {
        var printer = new Printer(null, "HP LaserJet Pro M203dw");
        printerRepository.save(printer);

        var printJob = new PrintJob(null, printer, Status.WAITING);
        printJobRepository.save(printJob);

        printJobRepository.deleteById(printJob.getId());

        var fetchedPrintJob = printJobRepository.findById(printJob.getId());
        Assertions.assertFalse(fetchedPrintJob.isPresent());
    }
}

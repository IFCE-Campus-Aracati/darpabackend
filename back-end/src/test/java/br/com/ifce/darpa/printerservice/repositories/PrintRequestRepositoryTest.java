package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

@DataJpaTest
@ActiveProfiles("test")
class PrintRequestRepositoryTest {

    @Autowired
    private PrintRequestRepository printRequestRepository;

    @Test
    void givenPrintRequestToAddShouldReturnAddedPrintRequest() {
        var user = new User(1L, "John Doe", "john@email.com", Set.of());
        var printRequest = new PrintRequest(null, user, null);

        printRequestRepository.save(printRequest);

        var fetchedPrintRequest = printRequestRepository.findById(printRequest.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrintRequest);
        Assertions.assertNotNull(fetchedPrintRequest.getId());
        Assertions.assertEquals(printRequest.getUser(), fetchedPrintRequest.getUser());
        Assertions.assertEquals(printRequest.getPrintJob(), fetchedPrintRequest.getPrintJob());
    }

    @Test
    void givenAllPrintRequestsShouldReturnListOfAllPrintRequests() {
        var user1 = new User(1L, "John Doe", "john@email.com", Set.of());
        var user2 = new User(1L, "Jane Doe", "jane@email.com", Set.of());

        var printRequest1 = new PrintRequest(null, user1, null);
        var printRequest2 = new PrintRequest(null, user2, null);

        printRequestRepository.saveAll(List.of(printRequest1, printRequest2));

        var fetchedPrintRequestList = printRequestRepository.findAll();

        Assertions.assertTrue(fetchedPrintRequestList.containsAll(List.of(printRequest1, printRequest2)));
    }

    @Test
    void givenIdThenShouldReturnPrintRequestOfThatId() {
        var user = new User(1L, "John Doe", "john@email.com", Set.of());
        var printRequest = new PrintRequest(null, user, null);

        var savedPrintRequest = printRequestRepository.save(printRequest);

        var fetchedPrintRequest = printRequestRepository.findById(savedPrintRequest.getId()).orElse(null);

        Assertions.assertNotNull(fetchedPrintRequest);
        Assertions.assertEquals(savedPrintRequest.getId(), fetchedPrintRequest.getId());
        Assertions.assertEquals(savedPrintRequest.getUser(), fetchedPrintRequest.getUser());
        Assertions.assertEquals(savedPrintRequest.getPrintJob(), fetchedPrintRequest.getPrintJob());
    }

    @Test
    void givenIdToDeleteThenShouldDeleteThePrintRequest() {
        var user = new User(1L, "John Doe", "john@email.com", Set.of());
        var printRequest = new PrintRequest(null, user, null);

        var savedPrintRequest = printRequestRepository.save(printRequest);

        printRequestRepository.deleteById(savedPrintRequest.getId());

        var fetchedPrintRequest = printRequestRepository.findById(savedPrintRequest.getId());

        Assertions.assertFalse(fetchedPrintRequest.isPresent());
    }
}

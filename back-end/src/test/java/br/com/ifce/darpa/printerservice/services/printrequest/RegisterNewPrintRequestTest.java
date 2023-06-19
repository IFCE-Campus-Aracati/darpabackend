package br.com.ifce.darpa.printerservice.services.printrequest;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.Role;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.models.User;
import br.com.ifce.darpa.printerservice.repositories.PrintRequestRepository;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintJob;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RegisterNewPrintRequestTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PrintRequestRepository printRequestRepository;

    @Mock
    private RegisterNewPrintJob registerNewPrintJob;

    @Autowired
    @InjectMocks
    private RegisterNewPrintRequestImpl registerNewPrintRequest;

    @Test
    void givenPrintRequestToAddShouldReturnAddedPrintRequest() {
        var owner = new User(1L, "John", "Doe", "john@email.com", "123456", Role.ROLE_USER);
        var printRequest = new RegisterNewPrintRequest.Request("file name", new byte[]{}, "request description", LocalDate.now());
        var savedJobForThisPrintRequest = new RegisterNewPrintJob.Response(1L, Status.PENDING);
        var savedPrintRequest = new PrintRequest();
        savedPrintRequest.setId(1L);
        savedPrintRequest.setUser(owner);

        Mockito.when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        Mockito.when(printRequestRepository.save(Mockito.any(PrintRequest.class))).thenReturn(savedPrintRequest);
        Mockito.when(registerNewPrintJob.execute(Mockito.any(RegisterNewPrintJob.Request.class))).thenReturn(savedJobForThisPrintRequest);

        var response = registerNewPrintRequest.execute(printRequest);

        Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(printRequestRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(registerNewPrintJob, Mockito.times(1)).execute(Mockito.any());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(savedPrintRequest.getId(), response.printRequestId());
        Assertions.assertEquals(owner.getId(), response.userId());
        Assertions.assertEquals(Status.PENDING, response.status());
    }

    @Test
    void givenPrintRequestWithInvalidUserIdShouldThrowNotFoundException() {
        Long invalidId = 1L;
        var owner = new User(invalidId, "Invalid", "User", "123456", "invalid-user@email.com", Role.ROLE_USER);
        var printRequest = new RegisterNewPrintRequest.Request("file name", new byte[]{}, "request description", LocalDate.now());

        Mockito.when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> registerNewPrintRequest.execute(printRequest));

        Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(printRequestRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(registerNewPrintJob, Mockito.never()).execute(Mockito.any());

        Assertions.assertEquals("user with id 1 not found", exception.getMessage());
    }
}

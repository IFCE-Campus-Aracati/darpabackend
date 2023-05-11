package br.com.ifce.darpa.printerservice.services.printjob;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.repositories.PrintJobRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintJob;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class RegisterNewPrintJobTest {

    @Mock
    private PrintJobRepository printJobRepository;

    @Autowired
    @InjectMocks
    private RegisterNewPrintJobImpl registerNewPrintJob;

    @Test
    void givenPrintJobToAddShouldReturnAddedPrintJob() {
        var request = new RegisterNewPrintJob.Request(null, Status.PENDING);
        var savedPrintJob = new PrintJob(1L, null, Status.PENDING);

        Mockito.when(printJobRepository.save(Mockito.any())).thenReturn(savedPrintJob);

        var response = registerNewPrintJob.execute(request);

        Mockito.verify(printJobRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(savedPrintJob.getId(), response.id());
        Assertions.assertEquals(savedPrintJob.getStatus(), response.status());
    }
}

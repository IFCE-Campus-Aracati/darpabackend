package br.com.ifce.darpa.printerservice.services.printjob;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import br.com.ifce.darpa.printerservice.repositories.PrintJobRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewPrintJobImpl implements RegisterNewPrintJob {

    @Autowired
    private PrintJobRepository printJobRepository;

    @Override
    public RegisterNewPrintJob.Response execute(RegisterNewPrintJob.Request request) {
        var newJob = printJobRepository.save(requestToPrintJob(request));
        return new RegisterNewPrintJob.Response(
                newJob.getId(),
                newJob.getStatus()
        );
    }

    private PrintJob requestToPrintJob(RegisterNewPrintJob.Request request) {
        return new PrintJob(null, null, request.printRequest(), request.status());
    }
}

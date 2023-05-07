package br.com.ifce.darpa.printerservice.services.printrequest;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.repositories.PrintRequestRepository;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintJob;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewPrintRequestImpl implements RegisterNewPrintRequest {

    private final UserRepository userRepository;
    private final PrintRequestRepository printRequestRepository;
    private final RegisterNewPrintJob registerNewPrintJob;

    @Autowired
    public RegisterNewPrintRequestImpl(
            UserRepository userRepository,
            PrintRequestRepository printRequestRepository,
            RegisterNewPrintJob registerNewPrintJob
    ) {
        this.userRepository = userRepository;
        this.printRequestRepository = printRequestRepository;
        this.registerNewPrintJob = registerNewPrintJob;
    }

    @Override
    public RegisterNewPrintRequest.Response execute(RegisterNewPrintRequest.Request request) {
        var owner = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException(
                        "user with id %d not found".formatted(request.userId())
                ));

        PrintRequest newPrintRequest = new PrintRequest();
        newPrintRequest.setFile(request.file());
        newPrintRequest.setUser(owner);

        newPrintRequest = printRequestRepository.save(newPrintRequest);

        var newJobForThisPrintRequest = registerNewPrintJob.execute(
                new RegisterNewPrintJob.Request(
                        newPrintRequest,
                        Status.PENDING
                )
        );

        return new RegisterNewPrintRequest.Response(
                newPrintRequest.getId(),
                newPrintRequest.getUser().getId(),
                newJobForThisPrintRequest.status()
        );
    }
}

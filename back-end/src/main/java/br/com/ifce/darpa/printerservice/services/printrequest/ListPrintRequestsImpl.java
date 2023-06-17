package br.com.ifce.darpa.printerservice.services.printrequest;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.ListPrintRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ListPrintRequestsImpl implements ListPrintRequests {

    @Autowired
    private UserRepository userRepository;

    // TODO: implementar paginação

    @Override
    public Response execute(Request request) {
        var owner = userRepository.findByIdWithPrintRequestDetails(request.userId())
                .orElseThrow(() -> new NotFoundException("user with id %d not found".formatted(request.userId())));

        var listOfPrintRequestDetails = owner.getPrintRequests()
                .stream()
                .map(printRequest -> {
                    Long printRequestId = printRequest.getId();
                    LocalDate printRequestCreationDate = printRequest.getCreatedAt();
                    String printRequestDescription = printRequest.getDescription();
                    Status printRequestStatus = printRequest.getPrintJob().getStatus();

                    return new RequestDetails(printRequestId, printRequestCreationDate, printRequestDescription, printRequestStatus);
                }).toList();

        return new Response(listOfPrintRequestDetails);
    }
}

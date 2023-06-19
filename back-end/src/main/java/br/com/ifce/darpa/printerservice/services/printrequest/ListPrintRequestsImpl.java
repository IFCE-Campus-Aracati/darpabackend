package br.com.ifce.darpa.printerservice.services.printrequest;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.models.Status;
import br.com.ifce.darpa.printerservice.repositories.PrintRequestRepository;
import br.com.ifce.darpa.printerservice.repositories.UserRepository;
import br.com.ifce.darpa.printerservice.services.ListPrintRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ListPrintRequestsImpl implements ListPrintRequests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrintRequestRepository printRequestRepository;

    @Override
    public Response execute(Request request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        var owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user not found"));

        var pageOfPrintRequest = printRequestRepository.findByUser(owner, Pageable.ofSize(request.size()).withPage(request.page()))
                .map(printRequest -> {
                    Long printRequestId = printRequest.getId();
                    String name = printRequest.getName();
                    LocalDate printRequestCreationDate = printRequest.getCreatedAt();
                    String printRequestDescription = printRequest.getDescription();
                    Status printRequestStatus = printRequest.getPrintJob().getStatus();

                    return new RequestDetails(printRequestId, name, printRequestCreationDate, printRequestDescription, printRequestStatus);
                });

        return new Response(
                pageOfPrintRequest.getTotalElements(),
                pageOfPrintRequest.getContent(),
                pageOfPrintRequest.getTotalPages(),
                pageOfPrintRequest.getNumber()
        );
    }
}

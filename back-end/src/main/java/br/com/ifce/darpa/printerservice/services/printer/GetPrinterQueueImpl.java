package br.com.ifce.darpa.printerservice.services.printer;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.PrintJobRepository;
import br.com.ifce.darpa.printerservice.repositories.PrinterRepository;
import br.com.ifce.darpa.printerservice.services.GetPrinterQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetPrinterQueueImpl implements GetPrinterQueue {

    @Autowired
    private PrinterRepository printerRepository;

    @Autowired
    private PrintJobRepository printJobRepository;

    @Override
    public Response execute(Request request) {
        var printer = printerRepository.findByName(request.printerName())
                .orElseThrow(() -> new NotFoundException("printer %s not found".formatted(request.printerName())));

        var pageOfPrintJobs = printJobRepository.findByPrinter(printer, Pageable.ofSize(request.pageSize()).withPage(request.pageNumber()))
                .map(printJob -> new JobDetails(
                        printJob.getId(),
                        printJob.getPrintRequest().getName(),
                        printJob.getPrintRequest().getUser().getFirstName() + " " + printJob.getPrintRequest().getUser().getLastName(),
                        printJob.getPrintRequest().getUser().getRole(),
                        printJob.getPrintRequest().getDescription()
                ));

        return new Response(
                pageOfPrintJobs.getTotalElements(),
                pageOfPrintJobs.getContent(),
                pageOfPrintJobs.getTotalPages(),
                pageOfPrintJobs.getNumber()
        );
    }
}

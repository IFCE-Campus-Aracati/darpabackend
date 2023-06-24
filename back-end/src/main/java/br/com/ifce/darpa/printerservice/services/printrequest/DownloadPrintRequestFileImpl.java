package br.com.ifce.darpa.printerservice.services.printrequest;

import br.com.ifce.darpa.printerservice.exceptions.NotFoundException;
import br.com.ifce.darpa.printerservice.repositories.PrintRequestRepository;
import br.com.ifce.darpa.printerservice.services.DownloadPrintRequestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadPrintRequestFileImpl implements DownloadPrintRequestFile {

    @Autowired
    private PrintRequestRepository printRequestRepository;

    @Override
    public Response execute(Long printRequestId) {
        var printRequest = printRequestRepository.findById(printRequestId)
                .orElseThrow(() -> new NotFoundException("request with id %d not found".formatted(printRequestId)));

        return new Response(
                printRequest.getName(),
                printRequest.getFile()
        );
    }
}

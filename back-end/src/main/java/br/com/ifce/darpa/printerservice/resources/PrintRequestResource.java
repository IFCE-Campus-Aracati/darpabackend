package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.ListPrintRequests;
import br.com.ifce.darpa.printerservice.services.RegisterNewPrintRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/v1/requests")
public class PrintRequestResource {

    @Autowired
    private RegisterNewPrintRequest registerNewPrintRequest;

    @Autowired
    private ListPrintRequests listPrintRequests;

    @PostMapping
    public ResponseEntity<RegisterNewPrintRequest.Response> newPrintRequest(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description
    ) {
        var response = registerNewPrintRequest.execute(
                new RegisterNewPrintRequest.Request(
                        name,
                        extractBytesFromMultipartFile(file),
                        description
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private byte[] extractBytesFromMultipartFile(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            // TODO: fix validation
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<ListPrintRequests.Response> listRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var response = listPrintRequests.execute(new ListPrintRequests.Request(page, size));
        return ResponseEntity.ok().body(response);
    }
}

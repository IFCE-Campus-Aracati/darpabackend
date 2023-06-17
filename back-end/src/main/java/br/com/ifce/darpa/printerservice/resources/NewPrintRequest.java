package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.RegisterNewPrintRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/requests")
public class NewPrintRequest {

    @Autowired
    private RegisterNewPrintRequest registerNewPrintRequest;

    @PostMapping
    public ResponseEntity<RegisterNewPrintRequest.Response> newPrintRequest(
            @RequestParam("userId") Long userId,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description
    ) {
        return ResponseEntity.ok().body(
                registerNewPrintRequest.execute(
                        new RegisterNewPrintRequest.Request(
                                userId,
                                name,
                                extractBytesFromMultipartFile(file),
                                description
                        )
                )
        );
    }

    private byte[] extractBytesFromMultipartFile(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            // TODO: fix validation
            throw new RuntimeException(e);
        }
    }
}

package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.DownloadPrintRequestFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requests")
public class DownloadFile {

    @Autowired
    private DownloadPrintRequestFile downloadPrintRequestFile;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        var file = downloadPrintRequestFile.execute(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename() + ".stl" + "\"")
                .body(file.fileContent());
    }
}

package br.com.ifce.darpa.printerservice.resources;


import br.com.ifce.darpa.printerservice.services.ListPrintRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requests")
public class ListRequests {

    @Autowired
    private ListPrintRequests listPrintRequests;

    @GetMapping
    public ResponseEntity<ListPrintRequests.Response> listRequests(@RequestBody ListPrintRequests.Request request) {
        return ResponseEntity.ok().body(
                listPrintRequests.execute(request)
        );
    }
}

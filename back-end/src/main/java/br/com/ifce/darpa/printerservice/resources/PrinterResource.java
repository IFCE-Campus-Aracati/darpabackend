package br.com.ifce.darpa.printerservice.resources;

import br.com.ifce.darpa.printerservice.services.GetPrinterQueue;
import br.com.ifce.darpa.printerservice.services.ListRegisteredPrinters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/printers")
public class PrinterResource {

    @Autowired
    private ListRegisteredPrinters listRegisteredPrinters;

    @Autowired
    private GetPrinterQueue getPrinterQueue;

    @GetMapping
    public ResponseEntity<ListRegisteredPrinters.Response> listPrinters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var printers = listRegisteredPrinters.execute(new ListRegisteredPrinters.Request(page, size));
        return ResponseEntity.ok().body(printers);
    }

    @GetMapping("/queue")
    public ResponseEntity<GetPrinterQueue.Response> getPrinterQueue(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var queue = getPrinterQueue.execute(new GetPrinterQueue.Request(name, page, size));
        return ResponseEntity.ok().body(queue);
    }
}

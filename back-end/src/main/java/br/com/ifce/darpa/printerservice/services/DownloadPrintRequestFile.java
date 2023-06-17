package br.com.ifce.darpa.printerservice.services;

public interface DownloadPrintRequestFile {
    Response execute(Long printRequestId);

    record Response(String filename, byte[] fileContent) {

    }
}

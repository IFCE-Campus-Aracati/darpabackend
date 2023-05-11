package br.com.ifce.darpa.printerservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_print_job")
public class PrintJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "printer_id")
    private Printer printer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "print_request_id")
    private PrintRequest printRequest;

    @Enumerated(EnumType.STRING)
    private Status status;

    public PrintJob() {}

    public PrintJob(Long id, PrintRequest printRequest, Status status) {
        this.id = id;
        this.printRequest = printRequest;
        this.status = status;
    }

    public PrintJob(Long id, Printer printer, PrintRequest printRequest, Status status) {
        this.id = id;
        this.printer = printer;
        this.printRequest = printRequest;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public PrintRequest getPrintRequest() {
        return printRequest;
    }

    public void setPrintRequest(PrintRequest printRequest) {
        this.printRequest = printRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintJob printJob = (PrintJob) o;
        return Objects.equals(id, printJob.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

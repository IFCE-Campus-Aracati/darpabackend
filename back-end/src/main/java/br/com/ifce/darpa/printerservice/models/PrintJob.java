package br.com.ifce.darpa.printerservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "printJob", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<PrintRequest> printRequests = new ArrayList<>();

    public PrintJob() {}

    public PrintJob(Long id, Printer printer) {
        this.id = id;
        this.printer = printer;
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

    public List<PrintRequest> getPrintRequests() {
        return printRequests;
    }

    public void addRequest(PrintRequest request) {
        this.printRequests.add(request);
    }

    public void addRequests(List<PrintRequest> requests) {
        this.printRequests.addAll(requests);
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

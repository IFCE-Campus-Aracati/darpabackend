package br.com.ifce.darpa.printerservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_print_job")
public class PrintJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Printer printer;

    @OneToMany(mappedBy = "printJob", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrintRequest> printRequests;

    @Enumerated(EnumType.STRING)
    private PrintStatus status;

    public PrintJob() {}

    public PrintJob(Long id, Printer printer, List<PrintRequest> printRequests, PrintStatus status) {
        this.id = id;
        this.printer = printer;
        this.printRequests = printRequests;
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

    public List<PrintRequest> getPrintRequests() {
        return printRequests;
    }

    public void setPrintRequests(List<PrintRequest> printRequests) {
        this.printRequests = printRequests;
    }

    public PrintStatus getStatus() {
        return status;
    }

    public void setStatus(PrintStatus status) {
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

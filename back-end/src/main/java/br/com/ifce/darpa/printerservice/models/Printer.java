package br.com.ifce.darpa.printerservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_printer")
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "printer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<PrintJob> printJobs = new ArrayList<>();

    public Printer() {}

    public Printer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrintJob> getPrintJobs() {
        return printJobs;
    }

    public void addJob(PrintJob job) {
        this.printJobs.add(job);
    }

    public void addJobs(List<PrintJob> jobs) {
        this.printJobs.addAll(jobs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Printer printer = (Printer) o;
        return Objects.equals(id, printer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

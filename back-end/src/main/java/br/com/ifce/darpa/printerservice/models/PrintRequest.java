package br.com.ifce.darpa.printerservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Entity
@Table(name = "tb_print_request")
public class PrintRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    @Lob
    private byte[] file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "printRequest", fetch = FetchType.LAZY)
    private PrintJob printJob;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    Instant createdAt;

    LocalDate scheduledDate;

    String description;

    public PrintRequest() {}

    public PrintRequest(Long id, User user, PrintJob printJob) {
        this.id = id;
        this.user = user;
        this.printJob = printJob;
    }

    public PrintRequest(Long id, User user, PrintJob printJob, String description) {
        this.id = id;
        this.user = user;
        this.printJob = printJob;
        this.description = description;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrintJob getPrintJob() {
        return printJob;
    }

    public void setPrintJob(PrintJob printJob) {
        this.printJob = printJob;
    }

    public LocalDate getCreatedAt() {
        return LocalDate.ofInstant(this.createdAt, ZoneId.systemDefault());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    public void setRequestCreation() {
        this.createdAt = Instant.now();
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintRequest that = (PrintRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

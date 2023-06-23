package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import br.com.ifce.darpa.printerservice.models.Printer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {

    @EntityGraph(attributePaths = {"printRequest", "printRequest.user"})
    Page<PrintJob> findByPrinter(Printer printer, Pageable pageable);
}

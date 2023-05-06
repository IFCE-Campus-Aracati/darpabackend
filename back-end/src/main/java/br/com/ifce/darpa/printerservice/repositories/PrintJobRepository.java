package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {

}

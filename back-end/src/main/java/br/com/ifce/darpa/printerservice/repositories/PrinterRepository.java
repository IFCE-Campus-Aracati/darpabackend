package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {

}

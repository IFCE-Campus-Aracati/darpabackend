package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintRequestRepository extends JpaRepository<PrintRequest, Long> {

}

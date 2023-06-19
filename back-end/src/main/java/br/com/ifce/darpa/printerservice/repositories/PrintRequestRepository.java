package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.PrintRequest;
import br.com.ifce.darpa.printerservice.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintRequestRepository extends JpaRepository<PrintRequest, Long> {

    Page<PrintRequest> findByUser(User user, Pageable pageable);
}

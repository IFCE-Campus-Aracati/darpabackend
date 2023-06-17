package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.printRequests WHERE u.id = :id")
    Optional<User> findByIdWithPrintRequestDetails(Long id);
}

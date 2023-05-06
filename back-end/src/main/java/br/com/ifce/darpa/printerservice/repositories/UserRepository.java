package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

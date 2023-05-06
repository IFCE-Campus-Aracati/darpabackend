package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

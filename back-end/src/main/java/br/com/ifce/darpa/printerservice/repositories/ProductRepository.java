package br.com.ifce.darpa.printerservice.repositories;

import br.com.ifce.darpa.printerservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

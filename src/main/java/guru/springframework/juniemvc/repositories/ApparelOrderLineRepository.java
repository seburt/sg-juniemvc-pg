package guru.springframework.juniemvc.repositories;

import guru.springframework.juniemvc.entities.ApparelOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ApparelOrderLine entity
 */
public interface ApparelOrderLineRepository extends JpaRepository<ApparelOrderLine, Integer> {
    // Spring Data JPA will implement basic CRUD operations
    // Custom query methods can be added here if needed
}
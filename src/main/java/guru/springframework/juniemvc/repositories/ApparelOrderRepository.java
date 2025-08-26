package guru.springframework.juniemvc.repositories;

import guru.springframework.juniemvc.entities.ApparelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for ApparelOrder entity
 */
public interface ApparelOrderRepository extends JpaRepository<ApparelOrder, Integer> {
    // Spring Data JPA will implement basic CRUD operations
    // Custom query methods can be added here if needed
}
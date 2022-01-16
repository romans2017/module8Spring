package ua.goit.module8Spring.wms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.module8Spring.wms.models.Producer;

import java.util.UUID;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID> {

    boolean existsByNameIgnoreCase(String name);

}
package ua.goit.module8Spring.wms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.module8Spring.wms.models.Producer;

import java.util.UUID;

public interface ProducerRepository extends JpaRepository<Producer, UUID> {
}
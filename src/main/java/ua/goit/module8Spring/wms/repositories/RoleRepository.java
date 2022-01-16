package ua.goit.module8Spring.wms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.module8Spring.wms.models.Role;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    List<Role> findByNameAllIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
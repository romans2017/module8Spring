package ua.goit.module8Spring.wms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.module8Spring.wms.models.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByEmailAllIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

}
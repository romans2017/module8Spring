package ua.goit.module8Spring.wms.services.modelServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.module8Spring.wms.models.Role;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.UserRepository;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ConnectionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName("first");
        user.setSecondName("second");
        user.setEmail("q@q.eu");
        user.setPassword("pass");
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("user");
        user.setRoles(Set.of(role));
        entityManager.persist(user);
        entityManager.flush();

        // when
        User found = userRepository.findById(user.getId()).get();

        // then
        assertThat(found.getEmail())
                .isEqualTo(user.getEmail());
    }
}

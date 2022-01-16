package ua.goit.module8Spring.wms.services.modelServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.models.Role;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.UserRepository;
import ua.goit.module8Spring.wms.services.RoleService;
import ua.goit.module8Spring.wms.services.UserService;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan("ua.goit.module8Spring.wms")
@DataJpaTest
public class UserModelTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void insertUser() {

        User user = new User();
        user.setFirstName("first");
        user.setSecondName("second");
        user.setEmail("q@q.eu");
        user.setPassword("pass");
        Role role = new Role();
        role.setName("user");
        user.setRoles(Set.of(role));
        entityManager.persist(user);
        entityManager.persist(role);
        entityManager.flush();

        User found = userRepository.findById(user.getId()).get();
        assertThat(found).isEqualTo(user);
    }

    @Test
    public void crudUserDto() {

        RoleDto roleDto = new RoleDto();
        roleDto.setName("user");
        RoleDto savedRole = roleService.create(roleDto);

        UserDto userDto = new UserDto();
        userDto.setFirstName("first");
        userDto.setSecondName("second");
        userDto.setEmail("q@q.eu");
        userDto.setPassword("pass");
        userDto.setRoles(Set.of(savedRole));

        UserDto saved = userService.create(userDto);
        UserDto found = userService.get(saved.getId());
        assertThat(found).isEqualTo(saved);
    }

    @Test
    public void getPredefinedUsersRolrs() {
        List<RoleDto> roleDtoUser = roleService.getByName("role_user");
        assertThat(roleDtoUser.get(0).getName()).isEqualTo("ROLE_USER");
    }
}

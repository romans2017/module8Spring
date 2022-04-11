package ua.goit.module8Spring.wms.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.RoleRepository;
import ua.goit.module8Spring.wms.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserService extends AbstractModelService<User, UserDto> {

    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto dto) {
        User model = modelMapper.map(dto, User.class);
        model.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getRoles().isEmpty()) {
            model.setRoles(Set.of(roleRepository.findByNameAllIgnoreCase("ROLE_USER")));
        }
        return modelMapper.map(repository.save(model), UserDto.class);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(UUID id, UserDto dto) {
        repository.findById(id)
                .map(user -> {
                    user.setRoles(new HashSet<>());
                    modelMapper.map(dto, user);
                    user.setPassword(passwordEncoder.encode(dto.getPassword()));
                    return user;
                }).ifPresent(user -> repository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto get(UUID id) {
        return super.get(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(UUID id) {
        super.delete(id);
    }

    public UserDto getByEmail(String email) {
        return modelMapper.map(((UserRepository) repository).findByEmailAllIgnoreCase(email), UserDto.class);
    }

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return ((UserRepository) repository).existsByEmailIgnoreCase(((UserDto) dto).getEmail());
        } else {
            return ((UserRepository) repository).existsByEmailIgnoreCaseAndIdIsNot(((UserDto) dto).getEmail(), dto.getId());
        }
    }
}
package ua.goit.module8Spring.wms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.RoleRepository;
import ua.goit.module8Spring.wms.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService extends AbstractModelService<User, UserDto> {

    @Autowired
    protected UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto dto) {
        User model = modelMapper.map(dto, User.class);
        model.setPassword(passwordEncoder.encode(dto.getPassword()));
        model.setRoles(Set.of(roleRepository.findByNameAllIgnoreCase("ROLE_USER")));
        return modelMapper.map(repository.save(model), UserDto.class);
    }

    @Override
    public void update(UUID id, UserDto dto) {
        repository.findById(id)
                .map(user -> {
                    user.setRoles(new HashSet<>());
                    modelMapper.map(dto, user);
                    user.setPassword(passwordEncoder.encode(dto.getPassword()));
                    return user;
                }).ifPresent(user -> {
                    repository.save(user);
                });
    }

    public UserDto getByEmail(String email) {
        return modelMapper.map(repository.findByEmailAllIgnoreCase(email), UserDto.class);
    }

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return repository.existsByEmailIgnoreCase(((UserDto) dto).getEmail());
        } else {
            return repository.existsByEmailIgnoreCaseAndIdIsNot(((UserDto) dto).getEmail(), dto.getId());
        }
    }
}

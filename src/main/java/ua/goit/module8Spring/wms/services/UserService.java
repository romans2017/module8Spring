package ua.goit.module8Spring.wms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends AbstractModelService<User, UserDto> {

    @Autowired
    protected UserRepository repository;

    public List<UserDto> getByEmail(String email) {
        List<UserDto> dtoList = new ArrayList<>();
        repository.findByEmailAllIgnoreCase(email).forEach(item -> dtoList.add(modelMapper.map(item, UserDto.class)));
        return dtoList;
    }

    @Override
    public boolean isExist(String name) {
        return repository.existsByEmailIgnoreCase(name);
    }
}

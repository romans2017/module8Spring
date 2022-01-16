package ua.goit.module8Spring.wms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.models.Role;
import ua.goit.module8Spring.wms.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends AbstractModelService<Role, RoleDto> {

    @Autowired
    protected RoleRepository repository;

    public List<RoleDto> getByName(String name) {
        List<RoleDto> dtoList = new ArrayList<>();
        repository.findByNameAllIgnoreCase(name).forEach(item -> dtoList.add(modelMapper.map(item, RoleDto.class)));
        return dtoList;
    }

    @Override
    public boolean isExist(String name) {
        return repository.existsByNameIgnoreCase(name);
    }
}

package ua.goit.module8Spring.wms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.models.Role;
import ua.goit.module8Spring.wms.repositories.RoleRepository;

@Service
public class RoleService extends AbstractModelService<Role, RoleDto> {

    @Autowired
    protected RoleRepository repository;

    public RoleDto getByName(String name) {
        return modelMapper.map(repository.findByNameAllIgnoreCase(name), RoleDto.class);
    }

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return repository.existsByNameIgnoreCase(((RoleDto) dto).getName());
        } else {
            return repository.existsByNameIgnoreCaseAndIdIsNot(((RoleDto) dto).getName(), dto.getId());
        }
    }
}

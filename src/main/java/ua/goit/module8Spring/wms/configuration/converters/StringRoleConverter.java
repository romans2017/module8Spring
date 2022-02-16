package ua.goit.module8Spring.wms.configuration.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.services.RoleService;

import java.util.UUID;

@Component
public class StringRoleConverter implements Converter<String, RoleDto> {

    @Autowired
    private RoleService roleService;

    @Override
    public RoleDto convert(String source) {
        return roleService.get(UUID.fromString(source));
    }
}

package ua.goit.module8Spring.wms.configuration.converters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.services.RoleService;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
public class StringRoleConverter implements Converter<String, RoleDto> {

    RoleService roleService;

    @Override
    public RoleDto convert(String source) {
        return roleService.get(UUID.fromString(source));
    }
}

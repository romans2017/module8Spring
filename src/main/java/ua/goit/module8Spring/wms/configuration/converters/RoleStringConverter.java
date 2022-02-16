package ua.goit.module8Spring.wms.configuration.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.RoleDto;

@Component
public class RoleStringConverter implements Converter<RoleDto, String> {

    @Override
    public String convert(RoleDto source) {
        return source.getId().toString();
    }
}

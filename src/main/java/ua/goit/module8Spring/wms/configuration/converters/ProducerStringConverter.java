package ua.goit.module8Spring.wms.configuration.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.ProducerDto;

@Component
public class ProducerStringConverter implements Converter<ProducerDto, String> {

    @Override
    public String convert(ProducerDto source) {
        return source.getId().toString();
    }
}

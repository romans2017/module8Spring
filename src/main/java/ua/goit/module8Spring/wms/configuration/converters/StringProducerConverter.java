package ua.goit.module8Spring.wms.configuration.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.ProducerDto;
import ua.goit.module8Spring.wms.services.ProducerService;

import java.util.UUID;

@Component
public class StringProducerConverter implements Converter<String, ProducerDto> {

    @Autowired
    private ProducerService producerService;

    @Override
    public ProducerDto convert(String source) {
        return producerService.get(UUID.fromString(source));
    }
}

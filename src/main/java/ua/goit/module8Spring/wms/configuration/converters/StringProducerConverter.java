package ua.goit.module8Spring.wms.configuration.converters;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.goit.module8Spring.wms.dto.ProducerDto;
import ua.goit.module8Spring.wms.services.ProducerService;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
public class StringProducerConverter implements Converter<String, ProducerDto> {

    ProducerService producerService;

    @Override
    public ProducerDto convert(String source) {
        return producerService.get(UUID.fromString(source));
    }
}

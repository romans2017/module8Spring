package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.ProducerService;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@UniqueValidation(classService = ProducerService.class)
public class ProducerDto implements Dto {

    private UUID id;

    @NotBlank
    @Size(min=2)
    private String name;
}

package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.ProducerService;
import ua.goit.module8Spring.wms.validation.OnCreate;
import ua.goit.module8Spring.wms.validation.OnUpdate;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class ProducerDto implements Dto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private UUID id;

    @NotBlank
    @Size(min=2)
    @UniqueValidation(classService = ProducerService.class)
    private String name;
}

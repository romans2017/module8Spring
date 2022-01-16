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
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class ProductDto implements Dto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private UUID id;

    @NotBlank
    @UniqueValidation(classService = ProducerService.class)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private ProducerDto producer;
}

package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.ProductService;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@UniqueValidation(classService = ProductService.class)
public class ProductDto implements Dto {

    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private ProducerDto producer;
}

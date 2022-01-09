package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class ProductDto implements Dto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private ProducerDto producer;
}

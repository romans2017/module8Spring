package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class RoleDto implements Dto{

    @NotNull
    private UUID id;

    @NotBlank
    @Size(min=2)
    private String name;

}

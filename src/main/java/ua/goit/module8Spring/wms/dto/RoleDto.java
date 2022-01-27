package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.RoleService;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@UniqueValidation(classService = RoleService.class)
public class RoleDto implements Dto {

    private UUID id;

    @NotBlank
    @Size(min=2)
    //@Pattern(regexp = "^ROLE_[a-zA-Z]+")
    private String name;
}

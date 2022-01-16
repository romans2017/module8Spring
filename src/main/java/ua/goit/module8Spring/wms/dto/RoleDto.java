package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ua.goit.module8Spring.wms.services.RoleService;
import ua.goit.module8Spring.wms.validation.OnCreate;
import ua.goit.module8Spring.wms.validation.OnUpdate;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class RoleDto implements Dto, GrantedAuthority {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private UUID id;

    @NotBlank
    @Size(min=2)
    @UniqueValidation(classService = RoleService.class)
    //@Pattern(regexp = "^ROLE_[a-zA-Z]+")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}

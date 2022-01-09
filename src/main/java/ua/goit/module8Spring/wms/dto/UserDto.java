package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor(force = true)
public class UserDto implements Dto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=2)
    private String firstName;

    private String secondName;

    @NotBlank
    private String password;

    private Set<RoleDto> roles;
}

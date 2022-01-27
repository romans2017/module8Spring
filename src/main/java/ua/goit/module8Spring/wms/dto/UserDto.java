package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.UserService;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@UniqueValidation(classService = UserService.class)
public class UserDto implements Dto {

    private UUID id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=1)
    private String firstName;

    private String secondName;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}$",
            message = """
                    Invalid password. It should be:
                    min length = 8,
                    at least one digit,
                    at least one symbol of !@#$%^&*,
                    at least one uppercase letter,
                    at least one lowercase letter""")
    private String password;

    private Set<RoleDto> roles;
}

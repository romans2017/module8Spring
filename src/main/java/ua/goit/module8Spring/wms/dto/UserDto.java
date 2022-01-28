package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.module8Spring.wms.services.UserService;
import ua.goit.module8Spring.wms.validation.OnCreate;
import ua.goit.module8Spring.wms.validation.OnUpdate;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@UniqueValidation(classService = UserService.class, groups = {OnCreate.class, OnUpdate.class})
public class UserDto implements Dto {

    private UUID id;

    @Email(groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 1, groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    private String secondName;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}$",
            message = """
                    Invalid password. It must be:
                    min length = 8,
                    at least one digit,
                    at least one symbol of !@#$%^&*,
                    at least one uppercase letter,
                    at least one lowercase letter""",
            groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @NotEmpty(groups = {OnUpdate.class})
    private Set<RoleDto> roles = new HashSet<>();
}

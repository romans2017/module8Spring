package ua.goit.module8Spring.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.goit.module8Spring.wms.services.UserService;
import ua.goit.module8Spring.wms.validation.OnCreate;
import ua.goit.module8Spring.wms.validation.OnUpdate;
import ua.goit.module8Spring.wms.validation.UniqueValidation;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class UserDto implements Dto, UserDetails {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private UUID id;

    @Email
    @NotBlank
    @UniqueValidation(classService = UserService.class)
    private String email;

    @NotBlank
    @Size(min=1)
    private String firstName;

    private String secondName;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}$")
    //min length = 8, at least one digit, at least one symbol of !@#$%^&*, at least one uppercase letter, at least one lowercase letter
    private String password;

    @NotEmpty
    private Set<RoleDto> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

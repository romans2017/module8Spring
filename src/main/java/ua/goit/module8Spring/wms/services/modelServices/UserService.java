package ua.goit.module8Spring.wms.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.models.User;
import ua.goit.module8Spring.wms.repositories.UserRepository;

public class UserService extends AbstractModelService<User, UserDto> {

    @Autowired
    protected UserRepository repository;
}

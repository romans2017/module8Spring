package ua.goit.module8Spring.wms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.module8Spring.wms.dto.RoleDto;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.services.RoleService;
import ua.goit.module8Spring.wms.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Validated
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/newForm")
    public String newUser(Model model) {
        List<RoleDto> roles = roleService.getAll();
        Map<RoleDto, Boolean> mapRoles = new HashMap<>();
        roles.forEach(item-> {
            mapRoles.put(item, item.getName().equals("ROLE_USER"));
        });

        model.addAttribute("userForm", new UserDto());
        model.addAttribute("allRoles", roles);
        return "user";
    }

    @PostMapping("/editForm")
    public String editUser(HttpServletRequest httpServletRequest, Model model) {
        UserDto user = userService.get(UUID.fromString(httpServletRequest.getParameter("id")));
        List<RoleDto> roles = roleService.getAll();
        List<RoleDto> userRoles = roleService.getAll();

        Map<RoleDto, Boolean> mapRoles = new HashMap<>();
        roles.forEach(item-> {
            mapRoles.put(item, user.getRoles().contains(item));
        });

        model.addAttribute("userForm", user);
        model.addAttribute("allRoles", roles);
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userForm") @Validated UserDto userForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        if (userForm.getId() == null) {
            userService.create(userForm);
        } else {
            userService.update(userForm.getId(), userForm);
        }
        return "redirect:/users";
    }

    @PostMapping("/remove")
    public String deleteUser(HttpServletRequest httpServletRequest) {
        userService.delete(UUID.fromString(httpServletRequest.getParameter("id")));
        return "redirect:/users";
    }
}

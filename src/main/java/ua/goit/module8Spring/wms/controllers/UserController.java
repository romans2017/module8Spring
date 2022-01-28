package ua.goit.module8Spring.wms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.services.RoleService;
import ua.goit.module8Spring.wms.services.UserService;
import ua.goit.module8Spring.wms.validation.OnCreate;
import ua.goit.module8Spring.wms.validation.OnUpdate;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@PreAuthorize("hasRole('ROLE_ADMIN')")
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
        UserDto userDto = new UserDto();
        model.addAttribute("userForm", userDto);
        model.addAttribute("isNew", true);
        model.addAttribute("allRoles", roleService.getAll());
        return "user";
    }

    @PostMapping("/editForm")
    public String editUser(HttpServletRequest httpServletRequest, Model model) {
        UserDto user = userService.get(UUID.fromString(httpServletRequest.getParameter("id")));
        model.addAttribute("userForm", user);
        model.addAttribute("isNew", false);
        model.addAttribute("allRoles", roleService.getAll());
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userForm") @Validated(OnUpdate.class) UserDto userForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isNew", false);
            model.addAttribute("allRoles", roleService.getAll());
            return "user";
        }
        userService.update(userForm.getId(), userForm);
        return "redirect:/users";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("userForm") @Validated(OnCreate.class) UserDto userForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isNew", true);
            model.addAttribute("allRoles", roleService.getAll());
            return "user";
        }
        userService.create(userForm);
        return "redirect:/users";
    }

    @PostMapping("/remove")
    public String deleteUser(HttpServletRequest httpServletRequest) {
        userService.delete(UUID.fromString(httpServletRequest.getParameter("id")));
        return "redirect:/users";
    }
}

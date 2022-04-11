package ua.goit.module8Spring.wms.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.goit.module8Spring.wms.dto.UserDto;
import ua.goit.module8Spring.wms.services.UserService;
import ua.goit.module8Spring.wms.validation.OnCreate;

import javax.servlet.http.HttpServletRequest;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
@Controller
public class RegistrationController {

    UserService userService;

    @GetMapping("/registration")
    public String registration(HttpServletRequest httpServletRequest,
                               Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userForm", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated({OnCreate.class}) UserDto userForm,
                          BindingResult bindingResult,
                          HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userService.create(userForm) == null) {
            return "registration";
        }
        return "redirect:/";
    }
}
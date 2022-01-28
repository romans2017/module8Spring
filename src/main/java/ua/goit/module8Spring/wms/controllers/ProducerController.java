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
import ua.goit.module8Spring.wms.dto.ProducerDto;
import ua.goit.module8Spring.wms.services.ProducerService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Validated
@Controller
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String getProducers(Model model) {
        model.addAttribute("producers", producerService.getAll());
        return "producers";
    }

    @PostMapping("/newForm")
    public String newProducer(Model model) {
        ProducerDto producerDto = new ProducerDto();
        model.addAttribute("producerForm", producerDto);
        return "producer";
    }

    @PostMapping("/editForm")
    public String editProducer(HttpServletRequest httpServletRequest, Model model) {
        ProducerDto producer = producerService.get(UUID.fromString(httpServletRequest.getParameter("id")));
        model.addAttribute("producerForm", producer);
        return "producer";
    }

    @PostMapping("/update")
    public String updateProducer(@ModelAttribute("producerForm") @Validated ProducerDto producerForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "producer";
        }
        if (producerForm.getId() == null) {
            producerService.create(producerForm);
        } else {
            producerService.update(producerForm.getId(), producerForm);
        }
        return "redirect:/producers";
    }

    @PostMapping("/remove")
    public String deleteProducer(HttpServletRequest httpServletRequest) {
        producerService.delete(UUID.fromString(httpServletRequest.getParameter("id")));
        return "redirect:/producers";
    }
}

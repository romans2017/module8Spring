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
import ua.goit.module8Spring.wms.dto.ProductDto;
import ua.goit.module8Spring.wms.services.ProducerService;
import ua.goit.module8Spring.wms.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Validated
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @PostMapping("/newForm")
    public String newProduct(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productForm", productDto);
        model.addAttribute("allProducers", producerService.getAll());
        return "product";
    }

    @PostMapping("/editForm")
    public String editProduct(HttpServletRequest httpServletRequest, Model model) {
        ProductDto product = productService.get(UUID.fromString(httpServletRequest.getParameter("id")));
        model.addAttribute("productForm", product);
        model.addAttribute("allProducers", producerService.getAll());
        return "product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("productForm") @Validated ProductDto productForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allProducers", producerService.getAll());
            return "product";
        }
        if (productForm.getId() == null) {
            productService.create(productForm);
        } else {
            productService.update(productForm.getId(), productForm);
        }
        return "redirect:/products";
    }

    @PostMapping("/remove")
    public String deleteProduct(HttpServletRequest httpServletRequest) {
        productService.delete(UUID.fromString(httpServletRequest.getParameter("id")));
        return "redirect:/products";
    }
}

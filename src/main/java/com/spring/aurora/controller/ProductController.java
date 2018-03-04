package com.spring.aurora.controller;

import com.spring.aurora.model.Product;
import com.spring.aurora.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list-products";
    }

    @RequestMapping(value = "/list/json",
            method = RequestMethod.GET,
            produces = "application/json",
            consumes = "application/json")
    public @ResponseBody List<Product> listAllJSON(){
        return productService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new-product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveNewProductForm(@ModelAttribute("product") Product product,
                                     BindingResult result, Model model,
                                     final RedirectAttributes redirect){
        if (result.hasErrors()) {
            return "new-product";
        } else {
            redirect.addFlashAttribute("msg", "Payment logged successfully!");
        }
        productService.saveOrUpdate(product);
        return "redirect:/products/list";
    }
}

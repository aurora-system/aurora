package com.spring.aurora.controller;

import com.spring.aurora.model.Product;
import com.spring.aurora.service.ProductService;
import com.spring.aurora.util.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ProductFormValidator productFormValidator;

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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new-product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewProductForm(@ModelAttribute("product") Product product,
                                     BindingResult result, Model model,
                                     final RedirectAttributes redirect){
        productFormValidator.validate(product, result);
        if (result.hasErrors()) {
            return "new-product";
        } else {
            redirect.addFlashAttribute("msg", "Product added successfully!");
        }
        product.setInitialPrice(product.getSellingPrice());
        product.setUpdatedAt(Date.valueOf(LocalDate.now()));
        if (StringUtils.isEmpty(product.getProductId())
                || !StringUtils.hasText(product.getProductId())) {
            product.setCreatedAt(Date.valueOf(LocalDate.now()));
            product.setProductId(null);
        }
        productService.save(product);
        return "redirect:/products/list";
    }

    @RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
    public String editProductForm(@PathVariable String productId,Model model){
        Product product = productService.findByProductId(productId);
        model.addAttribute("product", product);
        return "new-product";
    }
}

package com.geekbrains.controllers;

import com.geekbrains.repositories.Product;
import com.geekbrains.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @RequestMapping("/showAll")
    public String showAll(Model model) {
        model.addAttribute("products", productsService.getAllProducts());
        return "products";
    }

    @RequestMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @RequestMapping("/processForm")
    public String treatForm(Model model, @ModelAttribute(name = "product") Product product) {
        productsService.addProduct(product);
        model.addAttribute("product", product);
        return "product-form-info";
    }

    @RequestMapping("/id/{id}")
    public String showById(Model model, @PathVariable() int id) {
        model.addAttribute("product", productsService.getProductById(id));
        return "product-form-info";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Collection<Product> getAll() {
        return productsService.getAllProducts();
    }

    @RequestMapping("/getId")
    @ResponseBody
    public Product getById(@RequestParam int id) {
        return productsService.getProductById(id);
    }
}

package ru.example.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.project.entities.Product;
import ru.example.project.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products/filter/cost")
public class ProductFilterCostController {
    private static final String COMMON_PATH = "/products/filter/cost";
    private static final int PAGE_SIZE = 5;
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/lower", method = RequestMethod.GET)
    public String showLowerThan(Model model, @RequestParam Optional<Integer> value, @RequestParam Optional<Integer> page) {
        int filterValue = value.orElse(Integer.MIN_VALUE);
        int currentPage = page.orElse(1);

        Page<Product> productsPage = productService.getWithLowerCost(filterValue, PageRequest.of(currentPage - 1, PAGE_SIZE));

        String pathAndCondition = COMMON_PATH + "/lower?value=" + filterValue;
        setModelAttributes(model, productsPage, pathAndCondition);

        return "productsList";
    }

    @RequestMapping(value = "/greater", method = RequestMethod.GET)
    public String showGreaterThan(Model model, @RequestParam Optional<Integer> value, @RequestParam Optional<Integer> page) {
        int filterValue = value.orElse(Integer.MAX_VALUE);
        int currentPage = page.orElse(1);

        Page<Product> productsPage = productService.getWithGreaterCost(filterValue, PageRequest.of(currentPage - 1, PAGE_SIZE));

        String pathAndCondition = COMMON_PATH + "/greater?value=" + filterValue;
        setModelAttributes(model, productsPage, pathAndCondition);

        return "productsList";
    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public String showBetweenOf(Model model, @RequestParam Optional<Integer> minValue,
                                @RequestParam Optional<Integer> maxValue,
                                @RequestParam Optional<Integer> page) {
        int minVal = minValue.orElse(Integer.MIN_VALUE);
        int maxVal = maxValue.orElse(Integer.MAX_VALUE);
        int currentPage = page.orElse(1);

        Page<Product> productsPage = productService.getWithIncludedCost(minVal, maxVal,
                PageRequest.of(currentPage - 1, PAGE_SIZE));

        String pathAndCondition = COMMON_PATH + "/between?minvalue=" + minVal + "&maxValue=" + maxVal;
        setModelAttributes(model, productsPage, pathAndCondition);

        return "productsList";
    }

    private void setModelAttributes(Model model, Page<Product> productsPage, String pathAndCondition) {
        model.addAttribute("pathAndCondition", pathAndCondition);
        model.addAttribute("productsPage", productsPage);

        int totalPages = productsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}

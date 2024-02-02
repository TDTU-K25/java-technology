package com.example.springcommerce.controllers;

import com.example.springcommerce.models.Product;
import com.example.springcommerce.services.BrandService;
import com.example.springcommerce.services.CategoryService;
import com.example.springcommerce.services.ColorService;
import com.example.springcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ColorService colorService;

    @GetMapping("/filter")
    public String filterProducts(Model model, @RequestParam(required = false) String brandName, @RequestParam(required = false) String categoryName, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String colorName) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("filterProducts", productService.filter(brandName, categoryName, minPrice, maxPrice, colorName));

        model.addAttribute("brandName", brandName);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("colorName", colorName);
        return "user/filter";
    }

    @GetMapping("/{id}")
    public String getProductDetail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", product);
        model.addAttribute("productsRelated", productService.getAllProductsByBrand(product.getBrand()));
        return "user/product-detail";
    }
}

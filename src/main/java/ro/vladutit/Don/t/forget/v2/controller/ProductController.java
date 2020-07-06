package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.vladutit.Don.t.forget.v2.model.Product;
import ro.vladutit.Don.t.forget.v2.service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    //display list of products
    @RequestMapping("/products")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "products";
    }

//    @RequestMapping("/products")
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    //add a new product
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addTopic(@RequestBody Product product) {
        productService.addProduct(product);
    }
}


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

    //add a new product with Postman
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addTopic(@RequestBody Product product) {
        productService.addProduct(product);
    }

    //TOdo: to delete
    //add new product with a form
    @RequestMapping("/addNewProductForm")
    public String addNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    //add new product with a form
    @RequestMapping("/addNewItemForm")
    public String addNewItemForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_item";
    }

    @PostMapping("/addNewItem")
    public String addNewItem(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/dashboard/all";
    }

    //TOdo: to delete
    @PostMapping("/addNewProduct")
    public String addNewProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    //display dashboard
    @RequestMapping("/dashboard/dashboard")
    public String viewDashboard() {
        return "/dashboard/dashboard";
    }

    //display all items from dashboard
    @RequestMapping("/dashboard/all")
    public String viewDashboardAll(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "/dashboard/all";
    }
}


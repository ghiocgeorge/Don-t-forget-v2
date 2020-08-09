package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Add new category
    @RequestMapping("/category/add")
    public String addNewCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "add_category";
    }

    @PostMapping("/category/save")
    public String addnewCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/all";
    }

    // Update an category by name
    @GetMapping("/category/{id}")
    public String updateItemForm(@PathVariable(value = "id") String name, Model model) {
        // Get category from the service
        Category category = categoryService.getCateboryByName(name);
        System.out.println("Categorie id: " + name);

        // Set category as a model attribute to pre-populate the form
        model.addAttribute("category", category);
        return "update_category";
    }

}

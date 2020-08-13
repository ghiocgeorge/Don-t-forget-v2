package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;

import javax.validation.Valid;

@Controller
public class CategoryController implements WebMvcConfigurer {

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
    public String addnewCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_category";
        }
        categoryService.addCategory(category);
        return "redirect:/dashboard";
    }

    // Update a category by name
    @GetMapping("/category/{id}")
    public String updateItemForm(@PathVariable(value = "id") Long id, Model model) {
        // Get category from the service
        Category category = categoryService.getCategoryById(id);

        // Set category as a model attribute to pre-populate the form
        model.addAttribute("category", category);
        return "update_category";
    }

    // Delete a category by id
    @RequestMapping("/categoryd/{id}")
    public String deleteItem(@PathVariable (value = "id") Long id) {
        this.categoryService.deleteCategoryById(id);
        return "redirect:/dashboard";
    }

}

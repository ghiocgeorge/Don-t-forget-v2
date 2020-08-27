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

    // Save category from html page form
    @PostMapping("/category/save")
    public String addnewCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update_category";
        }
        categoryService.addCategory(category);
        return "redirect:/dashboard";
    }

    // Save category from modal form
    @PostMapping("/category/modal/save")
    public String addNew(Category category) {
        categoryService.addCategory(category);
        return "back";
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
    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItem(@PathVariable (value = "id") Long id) {
        this.categoryService.deleteCategoryById(id);
    }

    // Check if the name already exist in DB and return 0 or 1
    @GetMapping("/categories/{name}")
    @ResponseBody
    public boolean checkCategoryName(@PathVariable(value = "name") String name) {
        return categoryService.getCategoryByName(name);
    }
}

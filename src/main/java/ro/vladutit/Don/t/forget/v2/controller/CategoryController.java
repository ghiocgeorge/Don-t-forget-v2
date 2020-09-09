package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Icon;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;

@Controller
public class CategoryController implements WebMvcConfigurer {

    @Autowired
    private CategoryService categoryService;

    // Save category
    @PostMapping("/category/save")
    public String addNew(Category category) {
        categoryService.addCategory(category);
        return "back";
    }

    // Get a specific category information by id
    @GetMapping("/category/{id}")
    @ResponseBody
    public Category getCategory(@PathVariable(value = "id") Long id) {
        return categoryService.getCategoryById(id);
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

    @GetMapping("/icons/{icon}")
    @ResponseBody
    public String getIconName(@PathVariable(value = "icon") Icon icon) {
        return icon.getIconName();
    }
}

package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.CustomUserDetails;
import ro.vladutit.Don.t.forget.v2.model.Icon;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Controller
public class CategoryController implements WebMvcConfigurer {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    // Add new category
    @PostMapping("/category/new")
    public String addNewCategory(
            Category category,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        category.setUser(user);
        categoryService.addNewCategory(category);
        return "back";
    }

    // Update category
    @PostMapping("/category/update")
    public String updateCategory(
            Category category,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        category.setUser(user);
        categoryService.updateCategory(category);
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

    // Check if the name already exist in DB for the user logged and return 0 or 1
    @GetMapping("/categories/{name}")
    @ResponseBody
    public boolean checkCategoryNameByUser(
            @PathVariable(value = "name") String name,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        return categoryService.getCategoryByNameAndUserId(name, user.getId());
    }

    @GetMapping("/icons/{icon}")
    @ResponseBody
    public String getIconName(@PathVariable(value = "icon") Icon icon) {
        return icon.getIconName();
    }
}

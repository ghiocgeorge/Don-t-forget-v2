package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add new category with a form
    @RequestMapping("/category/add")
    public String addNewCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "add_category";
    }

    //action from add form
    @PostMapping("/category/save")
    public String addnewCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/all";
    }

}

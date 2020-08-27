package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

@Controller
public class DashboardController {
    @Autowired
    public ItemService itemService;
    @Autowired
    public CategoryService categoryService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String viewDashboard(
            Model category,
            Model model) {
        model.addAttribute("category", new Category());
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/dashboard";
    }

    @RequestMapping("/all")
    public String viewDashboardAll(
            Model item,
            Model category,
            Model model) {
        model.addAttribute("category", new Category());
        item.addAttribute("listItems", itemService.getAllItems());
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/all";
    }

    @RequestMapping("/items/{categoryId}")
    public String viewItemsByCategory(
            @PathVariable(value = "categoryId") Long categoryId,
            Model item,
            Model category,
            Model categoryTitle,
            Model model) {
        model.addAttribute("category", new Category());
        item.addAttribute("listItems", itemService.getByCategoryId(categoryId));
        category.addAttribute("listCategories", categoryService.getAllCategories());
        Category title = categoryService.getCategoryById(categoryId);
        categoryTitle.addAttribute("title", title);
        return "dashboard/items";
    }
}

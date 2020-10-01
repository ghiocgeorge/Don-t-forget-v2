package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

@Controller
public class DashboardController {
    @Autowired
    public ItemService itemService;
    @Autowired
    public CategoryService categoryService;

    @RequestMapping("/dashboard")
    public String viewDashboard(
            Model item,
            Model category,
            Model categoryList,
            Model itemList) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        itemList.addAttribute("listItems", itemService.getAllItems());
        categoryList.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/dashboard";
    }

    @RequestMapping("/all")
    public String viewDashboardAll(
            Model category,
            Model categoryList,
            Model item,
            Model itemList) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        itemList.addAttribute("listItems", itemService.getAllItems());
        categoryList.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/all";
    }

    @RequestMapping("/items/{categoryId}")
    public String viewItemsByCategory(
            @PathVariable(value = "categoryId") Long categoryId,
            Model item,
            Model itemList,
            Model category,
            Model categoryList,
            Model categoryTitle) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        itemList.addAttribute("listItems", itemService.getByCategoryId(categoryId));
        categoryList.addAttribute("listCategories", categoryService.getAllCategories());
        categoryTitle.addAttribute("title", categoryService.getCategoryById(categoryId));
        return "dashboard/items";
    }
}

package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

@Controller
public class DashboardController {
    @Autowired
    public ItemService itemService;
    @Autowired
    public CategoryService categoryService;

    @RequestMapping("/dashboard")
    public String viewDashboard(Model category) {
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/dashboard";
    }

    @RequestMapping("/all")
    public String viewDashboardAll(Model item, Model category) {
        item.addAttribute("listItems", itemService.getAllItems());
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "dashboard/all";
    }
}

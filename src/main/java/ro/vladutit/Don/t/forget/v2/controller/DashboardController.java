package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.CustomUserDetails;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Controller
public class DashboardController {
    @Autowired
    public ItemService itemService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UserService userService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/categories")
    public String viewDashboard(
            Model item,
            Model category,
            Model categoryList,
            Model itemList,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        itemList.addAttribute("listItems", itemService.getByUserId(user.getId()));
        categoryList.addAttribute("listCategories", categoryService.getByUserId(user.getId()));
        return "dashboard/categories";
    }

    @RequestMapping("/all_items")
    public String viewDashboardAll(
            Model category,
            Model categoryList,
            Model item,
            Model itemList,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        itemList.addAttribute("listItems", itemService.getByUserId(user.getId()));
        categoryList.addAttribute("listCategories", categoryService.getByUserId(user.getId()));
        return "dashboard/all_items";
    }

    @RequestMapping("/items/{categoryId}")
    public String viewItemsByCategory(
            @PathVariable(value = "categoryId") Long categoryId,
            Model item,
            Model itemList,
            Model category,
            Model categoryList,
            Model categoryTitle,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        item.addAttribute("item", new Item());
        category.addAttribute("category", new Category());
        itemList.addAttribute("listItems", itemService.getByCategoryId(categoryId));
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        categoryList.addAttribute("listCategories", categoryService.getByUserId(user.getId()));
        categoryTitle.addAttribute("title", categoryService.getCategoryById(categoryId));
        return "dashboard/items";
    }
}

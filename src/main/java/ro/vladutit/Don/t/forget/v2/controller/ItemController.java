package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //add new product with a form
    @RequestMapping("/addNewItemForm")
    public String addNewItemForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "add_item";
    }

    @PostMapping("/addNewItem")
    public String addNewItem(@ModelAttribute("item") Item item) {
        itemService.addItem(item);
        return "redirect:/all";
    }

    //display dashboard
    @RequestMapping("/dashboard")
    public String viewDashboard() {
        return "dashboard/dashboard";
    }

    //display all items from dashboard
    @RequestMapping("/all")
    public String viewDashboardAll(Model model) {
        model.addAttribute("listItems", itemService.getAllItems());
        return "dashboard/all";
    }

}


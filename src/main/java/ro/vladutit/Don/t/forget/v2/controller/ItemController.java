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

    @PostMapping("/addNewItem")
    public String addNewItem(@ModelAttribute("item") Item item) {
        itemService.addItem(item);
        return "redirect:/all";
    }

    //comment for pull request test

    //add new product with a form
    @RequestMapping("/addNewItemForm")
    public String addNewItemForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "add_item";
    }

    //display dashboard page
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

    //update an item by id
    @RequestMapping("/updateItemForm/{id}")
    public String updateItemForm(@PathVariable (value = "id") Long id, Model model) {
        //get item from the service
        Item item = itemService.getItemById(id);

        //set item as a model attribute to pre-populate the form
        model.addAttribute("item", item);
        return "update_item";
    }

    //display an item by id
    @RequestMapping("/viewItemForm/{id}")
    public String viewItemForm(@PathVariable (value = "id") Long id, Model model) {
        //get item from the service
        Item item = itemService.getItemById(id);

        //set item as a model attribute for view form
        model.addAttribute("item", item);
        return "view_item";
    }

    //delete an item by id
    @RequestMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable (value = "id") Long id) {
        //call delete item method
        this.itemService.deleteItemById(id);
        return "redirect:/all";
    }

}


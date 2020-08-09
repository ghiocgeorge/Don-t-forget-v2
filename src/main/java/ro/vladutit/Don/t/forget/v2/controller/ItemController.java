package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

import javax.servlet.http.HttpServletRequest;

@Controller ("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // Add new item
    @PostMapping("/save")
    public String addNewItem(@ModelAttribute("item") Item item) {
        itemService.addItem(item);
        return "redirect:/all";
    }

    @RequestMapping("/add")
    public String addNewItemForm(Model model, Model category) {
        Item item = new Item();
        model.addAttribute("item", item);
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "add_item";
    }

    // Update an item by id
    @GetMapping("/edit/{id}")
    public String updateItemForm(@PathVariable (value = "id") Long id, Model model, Model category) {
        // Get item from the service
        Item item = itemService.getItemById(id);

        // Set item as a model attribute to pre-populate the form
        model.addAttribute("item", item);
        category.addAttribute("listCategories", categoryService.getAllCategories());
        return "update_item";
    }

    // Display an item by id
    @GetMapping("/view/{id}")
    public String viewItemForm(@PathVariable (value = "id") Long id, Model model) {
        // Get item from the service
        Item item = itemService.getItemById(id);

        // Set item as a model attribute for view form
        model.addAttribute("item", item);
        return "view_item";
    }

    // Delete an item by id
    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable (value = "id") Long id) {
        this.itemService.deleteItemById(id);
        return "redirect:/all";
    }

}


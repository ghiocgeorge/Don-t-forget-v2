package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;

import javax.validation.Valid;


@Controller ("/item")
public class ItemController implements WebMvcConfigurer {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // Save item from html page form
    @PostMapping("/save")
    public String addNewItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model category) {
        if (bindingResult.hasErrors()) {
            category.addAttribute("listCategories", categoryService.getAllCategories());
            return "add_item";
        }
        itemService.addItem(item);
        return "redirect:/all";
    }

    // Save item from modal form
    @PostMapping("/modal/save")
    public String addNew(Item item) {
        itemService.addItem(item);
        return "back";
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItem(@PathVariable (value = "id") Long id) {
        this.itemService.deleteItemById(id);
    }

}


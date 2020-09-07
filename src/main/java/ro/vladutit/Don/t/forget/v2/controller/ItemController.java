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

    // Save item
    @PostMapping("/item/save")
    public String addNew(Item item) {
        itemService.addItem(item);
        return "back";
    }

    // Delete an item by id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteItem(@PathVariable (value = "id") Long id) {
        this.itemService.deleteItemById(id);
    }

    // Get a specific item information by id
    @GetMapping("/item/{id}")
    @ResponseBody
    public Item getItem(@PathVariable(value = "id") Long id) {
        return itemService.getItemById(id);
    }

}


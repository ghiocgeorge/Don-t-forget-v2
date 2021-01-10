package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.vladutit.Don.t.forget.v2.model.CustomUserDetails;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.service.CategoryService;
import ro.vladutit.Don.t.forget.v2.service.ItemService;
import ro.vladutit.Don.t.forget.v2.service.UserService;


@Controller ("/item")
public class ItemController implements WebMvcConfigurer {
    @Autowired
    private ItemService itemService;

    @Autowired
    public UserService userService;

    // Save item
    @PostMapping("/item/save")
    public String addNew(Item item, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        item.setUser(user);
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


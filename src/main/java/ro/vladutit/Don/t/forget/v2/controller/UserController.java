package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Save user
    @PostMapping("/user/save")
    public String addNew(User user) {
        userService.addUser(user);
        return "back";
    }
}

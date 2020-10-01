package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.model.UserData;
import ro.vladutit.Don.t.forget.v2.service.UserAlreadyExistException;
import ro.vladutit.Don.t.forget.v2.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model userData) {
        userData.addAttribute("userData", new UserData());
        return "account/register";
    }

    // JSON format - view all the users added
    @RequestMapping("/users/list")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public String userRegistration(
            @Valid UserData userData,
            BindingResult bindingResult,
            Model form) {
        if(bindingResult.hasErrors()) {
            form.addAttribute("registrationForm", userData);
            return "account/register";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue(
                    "email",
                    "userData.email",
                    "An account already exists for this email.");
            form.addAttribute("registrationForm", userData);
            return "account/register";
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

}

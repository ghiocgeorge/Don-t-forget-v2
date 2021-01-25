package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vladutit.Don.t.forget.v2.model.CustomUserDetails;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Controller
public class ProfileController {

    @Autowired
    public UserService userService;

    @RequestMapping("/profile")
    public String view_user(
            Model profile,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        profile.addAttribute("user", userService.getByEmail(customUserDetails.getUsername()));
        return "account/profile";
    }
}

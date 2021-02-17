package ro.vladutit.Don.t.forget.v2.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.vladutit.Don.t.forget.v2.model.CustomUserDetails;
import ro.vladutit.Don.t.forget.v2.model.PasswordDto;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.model.UserData;
import ro.vladutit.Don.t.forget.v2.service.SmsService;
import ro.vladutit.Don.t.forget.v2.service.UserAlreadyExistException;
import ro.vladutit.Don.t.forget.v2.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return "redirect:/index";
    }

    @RequestMapping("/profile")
    public String view_user(
            Model profile,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        profile.addAttribute("user", userService.getByEmail(customUserDetails.getUsername()));
        return "account/profile";
    }

    @GetMapping("/profile/edit")
    public String edit_user(
            Model profile,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String userEmail = customUserDetails.getUsername();
        profile.addAttribute("user", userService.getByEmail(userEmail));
        return "account/edit_profile";
    }

    @PostMapping("/profile/save")
    public String save_user(
            @Valid User user,
            BindingResult bindingResult)
            throws UnsupportedEncodingException, MessagingException {
        if(bindingResult.hasErrors()) {
            return "account/edit_profile";
        }
        userService.save(user);

        String contentEmail = "<p>Dear " + user.getFullname() + ", <br><br>You just updated your information! Please "
                + "contact us if you didn't made this operation!<br><br>Thank you!<br>The Don't forget app team.</p>";
//        String contentSMS = "You just updated your information!";

        userService.sendNotificationEmail(user.getEmail(), contentEmail, "Update info");
//        smsService.sendNotificationSms(user, contentSMS);
        return "redirect:/profile";
    }

    @GetMapping("/change_password")
    public String change_password(
            PasswordDto password,
            Model model) {
        model.addAttribute("password", password);
        return "account/change_password";
    }

    @PostMapping("/change_password/save")
    public String save_password(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @ModelAttribute("password") @Valid PasswordDto password,
            BindingResult bindingResult) {
        String userEmail = customUserDetails.getUsername();
        User user = userService.getByEmail(userEmail);
        if(passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            if(bindingResult.hasErrors()) {
                return "account/change_password";
            }
            userService.changeUserPassword(user, password.getNewPassword());
        } else {
            bindingResult.rejectValue(
                    "oldPassword",
                    "password.oldPassword",
                    "The Old Password is incorrect.");
            return "account/change_password";
        }
        return "redirect:/profile";
    }
}

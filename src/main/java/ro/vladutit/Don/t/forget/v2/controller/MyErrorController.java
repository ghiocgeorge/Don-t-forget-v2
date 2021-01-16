package ro.vladutit.Don.t.forget.v2.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        System.out.println("getErrorPath");
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(
            HttpServletRequest request,
            Model errorMsg,
            Model errorSts) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        errorSts.addAttribute("errorStatus", status);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg.addAttribute(
                        "errorMessage",
                        "We didn't find what you were looking for.");
                return "error";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMsg.addAttribute(
                        "errorMessage",
                        "There seems to be an internal server problem. Please try again.");
                return "error";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorMsg.addAttribute(
                        "errorMessage",
                        "You don't have permission in this side.");
                return "error";
            }
        }
        errorMsg.addAttribute("errorMessage", "Something went wrong.");
        return "error";
    }
}

package com.sberbank.credit.controller;

import com.sberbank.credit.model.dto.User;
import com.sberbank.credit.model.dto.converters.Converter;
import com.sberbank.credit.model.entity.UserEntity;
import com.sberbank.credit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthController {

    private static final String SIGN_UP_ENDPOINT = "/sign_up";

    private static final String SIGN_UP_VIEW = "/auth/sign_up";

    private static final String LOGIN_ENDPOINT = "/login";

    private static final String LOGIN_VIEW = "/auth/sign_in";


    @Autowired
    private UserService userService;

    @Autowired
    private Converter<UserEntity, User> userConverter;

    private static User currentUser;
    private static Long currentOrderId;

    @GetMapping(SIGN_UP_ENDPOINT)
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_ENDPOINT)
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return SIGN_UP_VIEW;
        }
        userService.addUser(userConverter.convertToEntity(user));
        return "redirect:" + LOGIN_ENDPOINT;
    }

    @GetMapping(LOGIN_ENDPOINT)
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        @RequestParam(name = "orderId", required = false) Long orderId, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        if (orderId != null) {
            model.addAttribute("orderId", orderId);
        }
        if (currentOrderId != null) {
            model.addAttribute("currentOrderId", currentOrderId);
        }
        return LOGIN_VIEW;
    }

    @PostMapping(LOGIN_ENDPOINT)
    public String loginProcess(@RequestParam(name = "login", required = false) String login,
                               @RequestParam(name = "password", required = false) String password,
                               @RequestParam(name = "orderId", required = false) Long orderId) {

        UserEntity user = null;
        try {
            user = userService.authenticate(login, password);
            currentUser = userConverter.convertToDto(user);
            return "redirect:/orders?orderId=" + orderId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login?error=true&orderId=" + orderId;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentOrderId(Long currentOrderId) {
        AuthController.currentOrderId = currentOrderId;
    }

    public static Long getCurrentOrderId() {
        return currentOrderId;
    }
}

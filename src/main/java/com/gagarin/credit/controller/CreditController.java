package com.gagarin.credit.controller;

import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.service.CreditRequestService;
import com.gagarin.credit.service.OrderService;
import com.gagarin.credit.service.ProductService;
import com.gagarin.credit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreditController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditRequestService creditRequestService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home/{name}")
    public String home(@PathVariable("name") String name, Model model) {
        model.addAttribute("msg", "Привет " + name);
        return "index";
    }

    @GetMapping("/users/new")
    public String signUp(){
        return "sign_up";
    }

    @GetMapping("/orders")
    public String getOrders(@RequestParam(value = "orderId", required = false) Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return "order";
    }

    @PostMapping("/request/new")
    public String createRequest(@RequestParam("orderId") Long orderId ,Model model) {
        //createRequest

        return String.format("redirect:/request?reqId=%d",1);
    }

    @GetMapping("/request")
    public String getRequest(@RequestParam("reqId") Long reqId, Model model) {
        model.addAttribute("request", creditRequestService.findById(reqId));
        return "request";
    }
}


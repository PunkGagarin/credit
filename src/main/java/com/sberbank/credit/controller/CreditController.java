package com.sberbank.credit.controller;

import com.sberbank.credit.model.CreditRequestEntity;
import com.sberbank.credit.model.OrderEntity;
import com.sberbank.credit.model.ProductEntity;
import com.sberbank.credit.service.CreditRequestService;
import com.sberbank.credit.service.OrderService;
import com.sberbank.credit.service.ProductService;
import com.sberbank.credit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/")
    public String homePage() {
        return "redirect:/make_order";
    }

    @GetMapping("/make_order")
    public String getNewOrder(Model model) {
        model.addAttribute("order", new OrderEntity());
        return "make_order";
    }

    @PostMapping("/make_order")
    public String makeOrder(@ModelAttribute @Valid OrderEntity order) {
        //TODO: валидация
        OrderEntity orderEntity = orderService.createOrder(order);
        return String.format("redirect:/login?orderId=%d", orderEntity.getId());
    }

    @GetMapping("/orders")
    public String getOrders(@RequestParam(value = "orderId", required = false) Long orderId, Model model) {
        if (orderId != null)
            model.addAttribute("order", orderService.getOrder(orderId));
        return "show_order";
    }

    @PostMapping("/request/new")
    public String createRequest(@RequestParam("orderId") Long orderId, Model model) {
        //saveRequest
        //TODO: переносить в сервис или оставить здесь, т.к. абстракция выше сервисной?
        CreditRequestEntity request = creditRequestService.saveRequest(createRequestByOrderId(orderId));
        return String.format("redirect:/request?reqId=%d", request.getId());
    }

    private CreditRequestEntity createRequestByOrderId(Long orderId) {
        OrderEntity order = orderService.getOrder(orderId);
        ProductEntity product = productService.findBySum(order.getSum());
        return creditRequestService.createRequestByOrderAndProduct(order, product);
    }

    @GetMapping("/request")
    public String getRequest(@RequestParam("reqId") Long reqId, Model model) {
        try {
            model.addAttribute("creditInfo", creditRequestService.getCreditInfo(reqId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "request";
    }
}


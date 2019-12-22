package com.sberbank.credit.controller;

import com.sberbank.credit.model.dto.CreditInfo;
import com.sberbank.credit.model.dto.Order;
import com.sberbank.credit.model.dto.converters.Converter;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import com.sberbank.credit.model.entity.OrderEntity;
import com.sberbank.credit.model.entity.ProductEntity;
import com.sberbank.credit.service.credit_request.CreditRequestService;
import com.sberbank.credit.service.order.OrderService;
import com.sberbank.credit.service.product.ProductService;
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
public class CreditController {

    private static final String MAKE_ORDER_ENDPOINT = "/make_order";

    private static final String ORDERS_ENDPOINT = "/orders";

    private static final String REQUEST_NEW_ENDPOINT = "/request/new";

    private static final String REQUEST_ENDPOINT = "/request";

    private static final String MAKE_ORDER_VIEW = "make_order";

    private static final String SHOW_ORDER_VIEW = "show_order";

    private static final String REQUEST_VIEW = "request";

    private static final String ORDER_ID = "orderId";

    private static final String ORDER = "order";


    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditRequestService creditRequestService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Converter<OrderEntity, Order> orderConverter;


    @GetMapping("/")
    public String homePage() {
        return "redirect:" + MAKE_ORDER_ENDPOINT;
    }

    @GetMapping(MAKE_ORDER_ENDPOINT)
    public String getNewOrder(Model model) {
        model.addAttribute(ORDER, new Order());
        return MAKE_ORDER_VIEW;
    }

    @PostMapping(MAKE_ORDER_ENDPOINT)
    public String makeOrder(@ModelAttribute @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return MAKE_ORDER_ENDPOINT;
        }
        AuthController.setCurrentOrderId(order.getId());
        orderService.createOrder(orderConverter.convertToEntity(order));
        return String.format("redirect:/login?%s=%d", ORDER_ID, order.getId());
    }

    @GetMapping(ORDERS_ENDPOINT)
    public String getOrders(@RequestParam(value = ORDER_ID, required = false) Long orderId, Model model) {
        if (orderId != null) {
            Order order = orderConverter.convertToDto(orderService.getOrder(orderId));
            model.addAttribute(ORDER, order);
            model.addAttribute("orderId", order.getId().toString());
        }
        if (AuthController.getCurrentOrderId() != null) {
            model.addAttribute("currentOrderId", AuthController.getCurrentOrderId().toString());
        }
        return SHOW_ORDER_VIEW;
    }

    @PostMapping(REQUEST_NEW_ENDPOINT)
    public String createRequest(@RequestParam(ORDER_ID) Long orderId, Model model) {
        CreditRequestEntity request = createRequestByOrderId(orderId);

        String login = AuthController.getCurrentUser().getLogin();
        if (login != null) {
            request.setUserLogin(login);
        }
        creditRequestService.saveRequest(request);
        return String.format("redirect:%s?reqId=%d", REQUEST_ENDPOINT, request.getId());
    }

    private CreditRequestEntity createRequestByOrderId(Long orderId) {
        OrderEntity order = orderService.getOrder(orderId);
        ProductEntity product = productService.findBySum(order.getSum());
        return creditRequestService.createRequestByOrderAndProduct(order, product);
    }

    @GetMapping(REQUEST_ENDPOINT)
    public String getRequest(@RequestParam("reqId") Long reqId, Model model) {
        CreditInfo creditInfo = null;
        try {
            creditInfo = creditRequestService.getCreditInfo(reqId);
            model.addAttribute("creditInfo", creditInfo);
            model.addAttribute("payPlan", creditInfo.getNextMonths());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REQUEST_VIEW;
    }
}


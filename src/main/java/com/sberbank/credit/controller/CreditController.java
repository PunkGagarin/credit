package com.sberbank.credit.controller;

import com.sberbank.credit.model.dtos.Order;
import com.sberbank.credit.model.dtos.converters.Converter;
import com.sberbank.credit.model.entities.CreditRequestEntity;
import com.sberbank.credit.model.entities.OrderEntity;
import com.sberbank.credit.model.entities.ProductEntity;
import com.sberbank.credit.service.credit_request.CreditRequestService;
import com.sberbank.credit.service.order.OrderService;
import com.sberbank.credit.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CreditController {

    public static final String MAKE_ORDER_ENDPOINT = "/make_order";

    public static final String ORDERS_ENDPOINT = "/orders";

    public static final String REQUEST_NEW_ENDPOINT = "/request/new";

    public static final String REQUEST_ENDPOINT = "/request";

    public static final String MAKE_ORDER_VIEW = "make_order";

    public static final String SHOW_ORDER_VIEW = "show_order";

    public static final String REQUEST_VIEW = "request";

    public static final String ORDER_ID = "orderId";

    public static final String ORDER = "order";


    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditRequestService creditRequestService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Converter<OrderEntity,Order> orderConverter;




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
    public String makeOrder(@ModelAttribute @Valid Order order) {
        //TODO: валидация
        orderService.createOrder(orderConverter.convertToEntity(order));
        return String.format("redirect:/login?%s=%d", ORDER_ID, order.getId());
    }

    @GetMapping(ORDERS_ENDPOINT)
    public String getOrders(@RequestParam(value = ORDER_ID, required = false) Long orderId, Model model) {
        if (orderId != null) {
            model.addAttribute(ORDER, orderService.getOrder(orderId));
            model.addAttribute(ORDER_ID, orderId);
        }
        return SHOW_ORDER_VIEW;
    }

    @PostMapping(REQUEST_NEW_ENDPOINT)
    public String createRequest(@RequestParam(ORDER_ID) Long orderId, Model model) {
        CreditRequestEntity request = createRequestByOrderId(orderId);

        if (AuthController.getCurrentUser().getLogin() != null)
            request.setUserLogin(AuthController.getCurrentUser().getLogin());

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
        try {
            model.addAttribute("creditInfo", creditRequestService.getCreditInfo(reqId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REQUEST_VIEW;
    }
}


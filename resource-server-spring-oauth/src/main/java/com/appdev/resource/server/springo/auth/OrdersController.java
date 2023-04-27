package com.appdev.resource.server.springo.auth;


import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @GetMapping("orders")
    public List<String> returnOrders(){
        return List.of("first", "second", "third");
    }

}

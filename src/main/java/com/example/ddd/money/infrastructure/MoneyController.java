package com.example.ddd.money.infrastructure;

import com.example.ddd.money.application.MoneyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//TODO implement jax-rs in the future

@RestController
@RequestMapping("/money")
public class MoneyController {

    private final MoneyFacade moneyFacade;

    @Autowired
    public MoneyController(MoneyFacade moneyFacade) {
        this.moneyFacade = moneyFacade;
    }

    //TODO return an response object
    @GetMapping("/balance")
    public Map<String, Double> balance(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return moneyFacade.userBalance(token);
    }
}

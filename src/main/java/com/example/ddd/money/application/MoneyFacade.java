package com.example.ddd.money.application;

import java.util.Map;

public interface MoneyFacade {

    Map<String, Double> userBalance(String token);
}

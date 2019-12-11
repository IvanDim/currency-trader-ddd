package com.example.ddd.money.application.impl;

import com.example.ddd.money.application.MoneyFacade;
import com.example.ddd.money.domain.exception.UserNotFoundException;
import com.example.ddd.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final UserRepository userRepository;

    @Autowired
    public MoneyFacadeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Double> userBalance(String token) {
        return userRepository.findByUsername(token)
                .orElseThrow(UserNotFoundException::new)
                .getBalance();
    }
}

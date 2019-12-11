package com.example.ddd.money.application.impl;

import com.example.ddd.money.application.MoneyFacade;
import com.example.ddd.money.domain.exception.UserNotFoundException;
import com.example.ddd.money.domain.service.UserAuthenticationService;
import com.example.ddd.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoneyFacadeImpl implements MoneyFacade {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public MoneyFacadeImpl(UserRepository userRepository, UserAuthenticationService userAuthenticationService) {
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public Map<String, Double> userBalance(String token) {
        final var username = userAuthenticationService.validateTokenAndGetUsername(token);
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new)
                .getBalance();
    }
}

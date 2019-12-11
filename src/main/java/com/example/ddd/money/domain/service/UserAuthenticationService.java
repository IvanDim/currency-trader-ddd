package com.example.ddd.money.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class UserAuthenticationService {

    private final String authenticationEndpoint;

    public UserAuthenticationService(@Value("${auth.validation.endpoint") String authenticationEndpoint) {
        this.authenticationEndpoint = authenticationEndpoint;
    }

    public String validateTokenAndGetUsername(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(authenticationEndpoint, HttpMethod.GET, httpEntity, String.class);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity.getBody();
        }

        throw new RuntimeException("user is not valid");
    }
}

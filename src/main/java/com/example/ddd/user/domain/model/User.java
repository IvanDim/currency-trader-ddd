package com.example.ddd.user.domain.model;

import com.example.ddd.money.domain.model.Balance;
import com.example.ddd.money.domain.model.Money;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User() {
    }

    public User(String username, Balance balance) {
        this.username = username;
        this.balance = balance;
    }

    public Map<String, Double> getBalance() {
        return balance.getAllMoney().stream()
                .collect(Collectors.toMap(money -> money.getCurrency().getName(), Money::getAmount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}

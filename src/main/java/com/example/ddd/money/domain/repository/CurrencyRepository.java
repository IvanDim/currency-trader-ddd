package com.example.ddd.money.domain.repository;

import com.example.ddd.money.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}

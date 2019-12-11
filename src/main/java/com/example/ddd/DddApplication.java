package com.example.ddd;

import com.example.ddd.money.domain.model.Balance;
import com.example.ddd.money.domain.model.Currency;
import com.example.ddd.money.domain.model.Money;
import com.example.ddd.money.domain.repository.CurrencyRepository;
import com.example.ddd.user.domain.model.User;
import com.example.ddd.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class DddApplication implements CommandLineRunner {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DddApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		final var usd = new Currency("USD", BigDecimal.ONE);
		final var eur = new Currency("EUR", BigDecimal.valueOf(1.5));
		final var cad = new Currency("CAD", BigDecimal.valueOf(0.8));
		currencyRepository.saveAll(List.of(usd, eur, cad));

		final var ivansBalance = new Balance();
		ivansBalance.addMoney(new Money(usd, 20));
		ivansBalance.addMoney(new Money(eur, 20));

		final var kamensBalance = new Balance();
		kamensBalance.addMoney(new Money(usd, 100));
		kamensBalance.addMoney(new Money(eur, 100));

		userRepository.saveAll(List.of(
				new User("Ivan", ivansBalance),
				new User("Kamen", kamensBalance)));
    }
}

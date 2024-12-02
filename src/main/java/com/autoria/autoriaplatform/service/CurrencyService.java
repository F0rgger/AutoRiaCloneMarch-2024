package com.autoria.autoriaplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getExchangeRates() {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.privatbank.ua/p24api/pubinfo")
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .toUriString();

        ExchangeRate[] rates = restTemplate.getForObject(url, ExchangeRate[].class);
        return Map.of(
                "USD", findRate(rates, "USD"),
                "EUR", findRate(rates, "EUR"),
                "UAH", 1.0
        );
    }

    private double findRate(ExchangeRate[] rates, String currency) {
        for (ExchangeRate rate : rates) {
            if (rate.getCcy().equals(currency)) {
                return rate.getBuy();
            }
        }
        throw new RuntimeException("Currency not found: " + currency);
    }

    private static class ExchangeRate {
        private String ccy;
        private double buy;

        public String getCcy() {
            return ccy;
        }

        public void setCcy(String ccy) {
            this.ccy = ccy;
        }

        public double getBuy() {
            return buy;
        }

        public void setBuy(double buy) {
            this.buy = buy;
        }
    }
}
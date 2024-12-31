package com.rjaco.dto;

public class CurrencyDTO {

    private int currencyId;
    private String currency;
    private int userId;
    private String username;
    private String email;

    public CurrencyDTO() {
    }

    public CurrencyDTO(int currencyId, String currency, int userId, String username, String email) {
        this.currencyId = currencyId;
        this.currency = currency;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

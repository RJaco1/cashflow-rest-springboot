package com.rjaco.dto;

import com.rjaco.model.Category;
import com.rjaco.model.CategoryType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private int transactionId;
    private double amount;
    private LocalDateTime date;

    private int categoryId;
    private String categoryName;
    private CategoryType categorytype;

    private int currencyId;
    private String currency;

    private int accountId;
    private String accountName;

    private int userId;
    private String username;
    private String email;

    public TransactionDTO() {
    }

    public TransactionDTO(int transactionId, double amount, LocalDateTime date,
                          int categoryId, String categoryName, CategoryType categorytype, int currencyId,
                          String currency, int accountId, String accountName, int userId,
                          String username, String email) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorytype = categorytype;
        this.currencyId = currencyId;
        this.currency = currency;
        this.accountId = accountId;
        this.accountName = accountName;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryType getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(CategoryType categorytype) {
        this.categorytype = categorytype;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

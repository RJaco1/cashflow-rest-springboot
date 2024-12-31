package com.rjaco.dto;

public class AccountDTO {

    private int accountId;
    private String accountName;
    private int userId;
    private String username;
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(int accountId, String accountName, int userId, String username, String email) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.userId = userId;
        this.username = username;
        this.email = email;
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

package com.shr.backend.entity;

public class PurchaseStatics {
    private Integer userId;
    private String userName;
    private Long amount;
    private Long spend;

    public PurchaseStatics(int userId, String userName, Long amount,
                            Long spend) {
        this.userId = userId;
        this.userName = userName;
        this.amount = amount;
        this.spend = spend;
    }

    public Integer getUserId() { return this.userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public Long getSpend() { return spend; }
    public void setSpend(Long spend) { this.spend = spend; }
}

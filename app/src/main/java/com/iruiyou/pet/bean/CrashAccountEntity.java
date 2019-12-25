package com.iruiyou.pet.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CrashAccountEntity")
public class CrashAccountEntity {
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 关联用户id
     */
    @DatabaseField(columnName = "userId")
    private String userId;

    /**
     * 关联账号
     */
    @DatabaseField(columnName = "account")
    private String account;

    /**
     * 关联名称
     */
    @DatabaseField(columnName = "accountName")
    private String accountName;

    /**
     * 账号类型 1-支付宝 2-币全账号
     */
    @DatabaseField(columnName = "accountType")
    private int accountType;

    /**
     * 国家编码
     */
    @DatabaseField(columnName = "countryCode")
    private String countryCode;

    @DatabaseField(columnName = "bankName")
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

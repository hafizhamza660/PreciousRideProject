package com.example.task.AccountDetailsFiles;

public class RequestAccountDetails {
  public String billing_type;
  public String vat_liability_status;
  public String vat_number;
  public String holder_name;
  public String Nuban_account_number;
  public String bank_name;

    public String getBilling_type() {
        return billing_type;
    }

    public void setBilling_type(String billing_type) {
        this.billing_type = billing_type;
    }

    public String getVat_liability_status() {
        return vat_liability_status;
    }

    public void setVat_liability_status(String vat_liability_status) {
        this.vat_liability_status = vat_liability_status;
    }

    public String getVat_number() {
        return vat_number;
    }

    public void setVat_number(String vat_number) {
        this.vat_number = vat_number;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getNuban_account_number() {
        return Nuban_account_number;
    }

    public void setNuban_account_number(String nuban_account_number) {
        Nuban_account_number = nuban_account_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}

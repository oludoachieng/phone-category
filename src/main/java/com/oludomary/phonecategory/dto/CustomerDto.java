package com.oludomary.phonecategory.dto;

import com.oludomary.phonecategory.entity.Customer;

public class CustomerDto {

  private Integer id;
  private String name;
  private String phone;
  private boolean isValid;
  private String countryCode;

  public CustomerDto(Customer customer){
    this.id = customer.getId();
    this.name = customer.getName();
    this.phone = customer.getPhone();
    this.isValid = customer.isValid();
    this.countryCode = customer.getCountryCode();

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isValid() {
    return isValid;
  }

  public void setValid(boolean valid) {
    isValid = valid;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}

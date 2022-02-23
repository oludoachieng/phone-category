package com.oludomary.phonecategory.model;

import java.util.List;

public enum Country {

  Morocco("(212)"),
  Mozambique("(258)"),
  Uganda("(256)"),
  Ethiopia("(251)"),
  Cameroon("(237)");

  private final String code;

  Country(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static List<String> getCountries() {
    return List
        .of(Morocco.name(), Mozambique.name(), Uganda.name(), Ethiopia.name(), Cameroon.name());
  }

  @Override
  public String toString() {
    return name();
  }
}

package com.oludomary.phonecategory.model;

import java.util.List;

public enum State {
  Valid,
  Invalid;

  public static List<String> getStates() {
    return List
        .of(Valid.name(), Invalid.name());
  }

  @Override
  public String toString() {
    return name();
  }
}

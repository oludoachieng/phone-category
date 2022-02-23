package com.oludomary.phonecategory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "phone")
  private String phone;


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

  /**
   * checks validity of current phone number
   * @return true if valid, false if invalid
   */
  public boolean isValid() {
    String phone = this.phone;
    String cameroonRegex = "\\(237\\)\\ ?[2368]\\d{7,8}$";
    String ethiopiaRegex = "\\(251\\)\\ ?[1-59]\\d{8}$";
    String moroccoRegex = "\\(212\\)\\ ?[5-9]\\d{8}$";
    String mozambiqueRegex = "\\(258\\)\\ ?[28]\\d{7,8}$";
    String ugandaRegex = "\\(256\\)\\ ?\\d{9}$";
    return phone.matches(cameroonRegex) || phone.matches(ethiopiaRegex) || phone
        .matches(moroccoRegex) || phone.matches(mozambiqueRegex) || phone.matches(ugandaRegex);
  }

  /**
   * extracts code from current phone number
   * @return true prefix
   */
  public String getCountryCode(){

    return this.phone.split(" ")[0];
  }

}


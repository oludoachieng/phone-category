package com.oludomary.phonecategory.service;

import com.oludomary.phonecategory.dto.CustomerDto;
import java.util.List;

public interface CustomerService {

  List<CustomerDto> findResult(String country, String state);
}

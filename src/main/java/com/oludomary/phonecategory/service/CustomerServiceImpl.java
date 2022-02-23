package com.oludomary.phonecategory.service;

import com.oludomary.phonecategory.dto.CustomerDto;
import com.oludomary.phonecategory.model.Country;
import com.oludomary.phonecategory.entity.Customer;
import com.oludomary.phonecategory.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(
      CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<CustomerDto> findResult(String country, String state) {

    List<Customer> customers = findByQueryParam(country, state);

    List<CustomerDto> customerDtoList = mapToDto(customers);

    return customerDtoList;
  }


  /**
   * This method searches for customers based on query parameters
   * @param country
   * @param state
   * @return list of customers
   */
  private List<Customer> findByQueryParam(String country, String state) {

    List<Customer> customers = null;


    if (state == null && !(country == null)) {
      //given the country but state is null
      customers = findByCountry(Country.valueOf(country).getCode());

    } else if (country == null && !(state == null)) {
      //given the state but country is null
      customers = findAll();
      customers = filterByState(customers, state);

    } else if (!(country == null) && !(state == null)) {
      //given both the state and country
      customers = findByCountry(Country.valueOf(country).getCode());
      customers = filterByState(customers, state);

    } else {
      customers = findAll();

    }
    return customers;
  }


  private List<Customer> filterByState(List<Customer> customers, String state) {

    if (state.equals("Valid")) {
      customers = customers.stream().filter(s -> s.isValid())
          .collect(Collectors.toList());

    } else {
      customers = customers.stream().filter(s -> !s.isValid())
          .collect(Collectors.toList());
    }
    return customers;
  }

  private List<Customer> findByCountry(String countryCode) {
    return customerRepository.findByPhoneNumber(countryCode);
  }

  private List<Customer> findAll() {
    return customerRepository.findAllCustomers();
  }


  private List<CustomerDto> mapToDto(List<Customer> customers) {
    return customers.stream().
        map(s -> {
          return new CustomerDto(s);
        }).collect(Collectors.toList());
  }



}

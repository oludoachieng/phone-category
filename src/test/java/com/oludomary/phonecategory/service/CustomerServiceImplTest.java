package com.oludomary.phonecategory.service;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.oludomary.phonecategory.entity.Customer;
import com.oludomary.phonecategory.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerServiceImplTest {

  private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

  private CustomerServiceImpl customerService;

  private List<Customer> customerList;

  @BeforeEach
  void initUseCase() {
    customerService = new CustomerServiceImpl(customerRepository);

    //Initialise customers
    customerList = addCustomers();

  }


  @Test
  @DisplayName("Country and state is null")
  void findAllResults() {

    when(customerRepository.findAllCustomers()).thenReturn(customerList);

    assertThat(customerService.findResult(null, null)).hasSize(2);

    assertThat(customerService.findResult(null, null))
        .extracting("name")
        .contains("Yosaf Karrouch", "ARREYMANYOR ROLAND TABOT");

  }

  @Test
  @DisplayName("Country is given but state is null")
  void findCountryResults() {

    //Mock result set
    when(customerRepository.findByPhoneNumber("(212)"))
        .thenReturn(customerList.stream().filter(c -> c.getPhone().contains("(212)")).collect(
            Collectors.toList()));

    assertThat(customerService.findResult("Morocco", null)).hasSize(1);

    assertThat(customerService.findResult("Morocco", null))
        .extracting("name")
        .contains("Yosaf Karrouch");

  }

  @Test
  @DisplayName("State is given but country is null")
  void findStateResults() {

    //Mock result set
    when(customerRepository.findAllCustomers()).thenReturn(customerList);

    assertThat(customerService.findResult(null, "Valid")).hasSize(1);

    assertThat(customerService.findResult(null, "Invalid")).hasSize(1);


    assertThat(customerService.findResult(null, "Invalid"))
        .extracting("name")
        .contains("ARREYMANYOR ROLAND TABOT");

  }

  @Test
  @DisplayName("Both country and state are given")
  void findCountryAndStateResults() {

    //Mock result set
    when(customerRepository.findAllCustomers()).thenReturn(customerList);

    when(customerRepository.findByPhoneNumber("(212)"))
        .thenReturn(customerList.stream().filter(c -> c.getPhone().contains("(212)")).collect(
            Collectors.toList()));

    when(customerRepository.findByPhoneNumber("(237)"))
        .thenReturn(customerList.stream().filter(c -> c.getPhone().contains("(237)")).collect(
            Collectors.toList()));

    assertThat(customerService.findResult("Cameroon", "Invalid"))
        .extracting("name")
        .contains("ARREYMANYOR ROLAND TABOT");

    assertThat(customerService.findResult("Cameroon", "Valid"))
        .extracting("name")
        .isEmpty();

    assertThat(customerService.findResult("Morocco", "Valid"))
        .extracting("name")
        .contains("Yosaf Karrouch");

    assertThat(customerService.findResult("Morocco", "Invalid"))
        .extracting("name")
        .isEmpty();


  }

  private List<Customer> addCustomers() {

    Customer moroccoValid = new Customer();
    moroccoValid.setId(1);
    moroccoValid.setName("Yosaf Karrouch");
    moroccoValid.setPhone("(212) 698054317");

    Customer cameroonInvalid = new Customer();
    cameroonInvalid.setId(33);
    cameroonInvalid.setName("ARREYMANYOR ROLAND TABOT");
    cameroonInvalid.setPhone("(237) 6A0311634");

    List<Customer> customers = new ArrayList<>();
    customers.add(moroccoValid);
    customers.add(cameroonInvalid);

    return customers;
  }

}
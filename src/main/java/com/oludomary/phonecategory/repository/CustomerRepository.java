package com.oludomary.phonecategory.repository;

import com.oludomary.phonecategory.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("SELECT c FROM Customer c WHERE c.phone LIKE %?1% order by id asc")
  List<Customer> findByPhoneNumber(String code);

  @Query("SELECT c FROM Customer c order by id asc")
  List<Customer> findAllCustomers();

}

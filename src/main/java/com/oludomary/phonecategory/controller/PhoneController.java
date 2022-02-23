package com.oludomary.phonecategory.controller;

import com.oludomary.phonecategory.dto.CustomerDto;
import com.oludomary.phonecategory.model.Country;
import com.oludomary.phonecategory.model.State;
import com.oludomary.phonecategory.service.CustomerService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PhoneController {

  private final CustomerService customerService;

  public PhoneController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("customer")
  public String showCustomerList(Model model, @RequestParam(required = false) String country,
      @RequestParam(required = false) String state,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(10);

    //fetch results based on the query params
    List<CustomerDto> customerDtoList = customerService.findResult(country, state);

    //paginate the result set
    Page<CustomerDto> customerPage = paginateResult(PageRequest.of(currentPage - 1, pageSize),
        customerDtoList);

    //add attributes to be used in the view
    model.addAttribute("customerPage", customerPage);
    model.addAttribute("countries", Country.getCountries());
    model.addAttribute("states", State.getStates());

    int totalPages = customerPage.getTotalPages();

    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
          .boxed().collect(Collectors.toList());

      model.addAttribute("pageNumbers", pageNumbers);
    }
    return "customer";
  }

  /**
   * This method paginates the results from the service classes
   * @param pageable for the pages to be printed
   * @param dtoList  list of dto to be paginated
   * @return sublist of items to be displayed
   */
  private <T> Page<T> paginateResult(Pageable pageable, List<T> dtoList) {
    List<T> list;

    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;

    if (dtoList.size() < startItem) {
      list = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, dtoList.size());
      list = dtoList.subList(startItem, toIndex);
    }

    Page<T> customerPage = new PageImpl<T>(list,
        PageRequest.of(currentPage, pageSize), dtoList.size());

    return customerPage;
  }
}

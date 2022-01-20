package com.openworld.mvp.api.customer;

import com.openworld.mvp.bm.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerResource {

    private final CustomerTransformer transformer;
    private final CustomerService service;


    @GetMapping("/")
    public List<CustomerDTO> getCustomers() {
        return transformer.mapDTOList(service.findAll());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable final Long id) {
        return transformer.mapDTO(service.findById(id));
    }

}

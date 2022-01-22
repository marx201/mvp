package com.openworld.mvp.api.customer;

import com.openworld.mvp.bm.customer.CustomerBE;
import com.openworld.mvp.bm.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public CustomerDTO register(@RequestParam("walletAddress") final String walletAddress) {
        return transformer.mapDTO(service.save(walletAddress));
    }



}

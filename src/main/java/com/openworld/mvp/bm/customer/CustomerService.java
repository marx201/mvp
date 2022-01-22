package com.openworld.mvp.bm.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerBE findById(final Long id) {
        return CustomerBE.builder().id(id).walletAddress("dummyWalletAddress").build();
    }

    public Optional<CustomerBE> findBySecret(final String secret){
        return customerRepository.findBySecret(secret);
    }

    public List<CustomerBE> findAll() {
        return List.of(CustomerBE.builder().id(1L).walletAddress("dummyWalletAddress").build());
    }

    public CustomerBE save(final String walletAddress) {
        CustomerBE customerBE = new CustomerBE();
        customerBE.setWalletAddress(walletAddress);
        return customerRepository.save(customerBE);
    }


}

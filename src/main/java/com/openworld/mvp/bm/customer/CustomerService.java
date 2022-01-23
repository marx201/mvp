package com.openworld.mvp.bm.customer;

import com.openworld.mvp.exception.SecretInvalidException;
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

    public CustomerBE findBySecret(final String secret) {
        Optional<CustomerBE> customer = customerRepository.findBySecret(secret);

        if (customer.isEmpty()) {
            throw new SecretInvalidException("Secret" + secret + " is invalid.");
        }
        return customer.get();
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

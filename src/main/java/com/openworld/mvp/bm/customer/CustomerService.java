package com.openworld.mvp.bm.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public CustomerBE findById(final Long id) {
        return CustomerBE.builder().id(id).walletAddress("dummyWalletAddress").build();
    }

    public List<CustomerBE> findAll() {
        return List.of(CustomerBE.builder().id(1L).walletAddress("dummyWalletAddress").build());
    }

}

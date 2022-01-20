package com.openworld.mvp.api.customer;

import com.openworld.mvp.bm.customer.CustomerBE;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerTransformer {

    public List<CustomerDTO> mapDTOList(List<CustomerBE> customerBEList) {
        return customerBEList.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    public CustomerDTO mapDTO(final CustomerBE customerBE) {
        return CustomerDTO
                .builder()
                .walletAddress(customerBE.getWalletAddress())
                .id(customerBE.getId()).build();
    }

}

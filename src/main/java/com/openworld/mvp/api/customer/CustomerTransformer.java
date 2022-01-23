package com.openworld.mvp.api.customer;

import com.openworld.mvp.api.router.RouterTransformer;
import com.openworld.mvp.api.token.TokenTransformer;
import com.openworld.mvp.bm.customer.CustomerBE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerTransformer {

    private RouterTransformer routerTransformer;
    private TokenTransformer tokenTransformer;

    @Autowired
    public CustomerTransformer(RouterTransformer routerTransformer, TokenTransformer tokenTransformer) {
        this.routerTransformer = routerTransformer;
        this.tokenTransformer = tokenTransformer;
    }

    public List<CustomerDTO> mapDTOList(List<CustomerBE> customerBEList) {
        return customerBEList.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    public CustomerDTO mapDTO(final CustomerBE customerBE) {
        return CustomerDTO
                .builder()
                .walletAddress(customerBE.getWalletAddress())
                .secret(customerBE.getSecretKey())
                .routers(routerTransformer.mapDTOList(customerBE.getRouters()))
                .tokens(tokenTransformer.mapDTOList(customerBE.getTokens()))
                .id(customerBE.getId()).build();
    }

}

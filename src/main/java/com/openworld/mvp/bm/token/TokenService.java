package com.openworld.mvp.bm.token;

import com.openworld.mvp.bm.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class TokenService {

    private final CustomerService customerService;

    public TokenBE findByCustomerId(Long id) {
        return TokenBE.builder().customerBE(customerService.findById(id)).amountSent(100000L).build();
    }

    public List<TokenBE> findAll() {
        return List.of(TokenBE.builder().customerBE(customerService.findAll().get(0)).amountSent(100000L).build());
    }

}

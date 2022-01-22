package com.openworld.mvp.bm.token;

import com.openworld.mvp.integration.ganache.TransactionESI;
import com.openworld.mvp.bm.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@Service
public class TokenService {

    private final CustomerService customerService;
    private final TransactionESI transactionESI;

    @Autowired
    public TokenService(CustomerService customerService, TransactionESI transactionESI) {
        this.customerService = customerService;
        this.transactionESI = transactionESI;
    }

    public TokenBE findByCustomerId(Long id) {
        return TokenBE.builder().customer(customerService.findById(id)).amount(100000L).build();
    }

    public List<TokenBE> findAll() {
        return List.of(TokenBE.builder().customer(customerService.findAll().get(0)).amount(100000L).build());
    }

    public TransactionReceipt transferToken(final String walletAddress, final Long amount) throws Exception {
        return transactionESI.transferToken(walletAddress, amount);
    }

}

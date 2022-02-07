package com.openworld.mvp.api.token;

import com.openworld.mvp.bm.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/token")
public class TokenResource {

    private final TokenTransformer transformer;
    private final TokenService service;

    @PostMapping("/")
    public TransactionReceipt transferTokens(@RequestParam("amount") final Long amount,
                                             @RequestParam("walletAddress") String walletAddress) throws Exception {
        return service.transferToken(walletAddress, amount);
    }

    @GetMapping("/")
    public List<TokenDTO> getAllTokens() {
        return transformer.mapDTOList(service.findAll());
    }

}

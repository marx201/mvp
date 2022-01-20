package com.openworld.mvp.api.token;

import com.openworld.mvp.bm.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/token")
public class TokenResource {

    private final TokenTransformer transformer;
    private final TokenService service;

    @PostMapping("/")
    public String transferTokens(@RequestParam("amount") final Long amount,
                                 @RequestParam("walletAddress") String walletAddress) {
        return String.format("Successfully transferred amount: %s to wallet: %s", amount, walletAddress);
    }

    @GetMapping("/")
    public List<TokenDTO> getAllTokens() {
        return transformer.mapDTOList(service.findAll());
    }
}

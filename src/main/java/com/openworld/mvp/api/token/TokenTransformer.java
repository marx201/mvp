package com.openworld.mvp.api.token;

import com.openworld.mvp.bm.token.TokenBE;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenTransformer {

    // TODO map list from customerdto
    public TokenDTO mapDTO(TokenBE tokenBE) {
        return TokenDTO.builder().amountSent(tokenBE.getAmount()).id(tokenBE.getId()).build();
    }

    public List<TokenDTO> mapDTOList(List<TokenBE> tokenBEList) {
        return tokenBEList.stream().map(this::mapDTO).collect(Collectors.toList());
    }
}

package com.openworld.mvp.api.token;

import com.openworld.mvp.api.customer.CustomerDTO;
import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class TokenDTO {
    private Long id;
    private Long amountSent;
    private CustomerDTO customerDTO;
}

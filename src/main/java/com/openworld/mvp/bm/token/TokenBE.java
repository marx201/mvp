package com.openworld.mvp.bm.token;

import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TokenBE {
    private Long id;
    private Long amountSent;
    private CustomerBE customerBE;
}

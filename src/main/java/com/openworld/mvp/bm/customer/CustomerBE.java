package com.openworld.mvp.bm.customer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerBE {
    private Long id;
    private String walletAddress;
}

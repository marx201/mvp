package com.openworld.mvp.api.localwallet;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class LocalWalletDTO {
    private Long id;
    private double balance;
}

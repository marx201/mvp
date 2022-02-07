package com.openworld.mvp.api.localwallet;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class LocalWalletDTO {
    private Long id;
    private double balance;
}

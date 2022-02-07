package com.openworld.mvp.api.localtransaction;

import com.openworld.mvp.api.localwallet.LocalWalletDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class LocalTransactionDTO {
    private Long id;
    private Long amount;
    private Date creation;
    private LocalWalletDTO LocalWalletDTO;
}

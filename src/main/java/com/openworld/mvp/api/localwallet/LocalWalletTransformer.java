package com.openworld.mvp.api.localwallet;

import com.openworld.mvp.bm.localwallet.LocalWalletBE;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalWalletTransformer {

    public LocalWalletDTO mapDTO(final LocalWalletBE localWalletBE) {

        return LocalWalletDTO
                .builder()
                .balance(localWalletBE.getBalance())
                .id(localWalletBE.getId())
                .build();
    }
}

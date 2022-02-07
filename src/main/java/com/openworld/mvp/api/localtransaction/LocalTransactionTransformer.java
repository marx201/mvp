package com.openworld.mvp.api.localtransaction;

import com.openworld.mvp.bm.localtransactions.LocalTransactionBE;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalTransactionTransformer {
    public LocalTransactionDTO mapDTO(final LocalTransactionBE localTransactionBE) {
        return LocalTransactionDTO
                .builder()
                .build();
    }
}

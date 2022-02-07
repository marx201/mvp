package com.openworld.mvp.api.localtransaction;


import com.openworld.mvp.bm.localtransactions.LocalTransactionService;
import com.openworld.mvp.bm.localtransactions.LocalTransactionType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/local-transaction")
public class LocalTransactionResource {

    private final LocalTransactionTransformer transformer;
    private final LocalTransactionService service;

    @PostMapping("/")
    public LocalTransactionDTO createTransaction(@RequestParam("amount") final Long amount, @RequestParam("type") final LocalTransactionType type, @RequestParam("local-wallet-id") final Long localWalletId) {
        // return transformer.mapDTO(service.);
        return null;
    }

}

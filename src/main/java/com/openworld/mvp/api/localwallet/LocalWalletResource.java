package com.openworld.mvp.api.localwallet;

import com.openworld.mvp.bm.localwallet.LocalWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/local-wallet")
public class LocalWalletResource {


    private final LocalWalletTransformer transformer;
    private final LocalWalletService service;

    @Autowired
    public LocalWalletResource(LocalWalletTransformer transformer, LocalWalletService service) {
        this.transformer = transformer;
        this.service = service;
    }

    @GetMapping("/router/{id}")
    public LocalWalletDTO getLocalWallet(@PathVariable final Long routerId) {
        return transformer.mapDTO(service.findByRouterId(routerId));
    }
}

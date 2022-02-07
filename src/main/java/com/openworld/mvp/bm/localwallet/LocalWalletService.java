package com.openworld.mvp.bm.localwallet;

import com.openworld.mvp.bm.router.RouterService;
import com.openworld.mvp.exception.LocalWalletNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class LocalWalletService {

    private RouterService routerService;
    private LocalWalletRepository localWalletRepository;

    public LocalWalletBE findByRouterId(Long routerId) {
        Optional<LocalWalletBE> localWalletBE = localWalletRepository.findByRouterId(routerId);

        if (localWalletBE.isEmpty()) {
            throw new LocalWalletNotExistsException("Local wallet with router id " + routerId + " not exists.");
        }

        return localWalletBE.get();
    }
}

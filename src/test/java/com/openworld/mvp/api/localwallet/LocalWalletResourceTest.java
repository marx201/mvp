package com.openworld.mvp.api.localwallet;

import com.openworld.mvp.bm.localwallet.LocalWalletBE;
import com.openworld.mvp.bm.localwallet.LocalWalletService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalWalletResourceTest {

    @Mock
    private LocalWalletTransformer transformer;
    @Mock
    private LocalWalletService service;

    @InjectMocks
    private LocalWalletResource localWalletResource;

    @Test
    void getLocalWallet() {

        final Long routerId = 1L;

        LocalWalletBE localWalletBE = new LocalWalletBE();
        localWalletBE.setId(1L);

        LocalWalletDTO localWalletDTO = new LocalWalletDTO();
        localWalletDTO.setId(1L);

        when(service.findByRouterId(routerId)).thenReturn(localWalletBE);
        when(transformer.mapDTO(localWalletBE)).thenReturn(localWalletDTO);

        LocalWalletDTO actualWallet = localWalletResource.getLocalWallet(routerId);
        Assertions.assertThat(actualWallet.getId()).isEqualTo(1L);

    }
}
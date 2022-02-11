package com.openworld.mvp.integration;

import com.openworld.mvp.api.customer.CustomerDTO;
import com.openworld.mvp.api.router.RouterDTO;
import com.openworld.mvp.integration.utils.CustomerUtil;
import com.openworld.mvp.integration.utils.RouterUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@AutoConfigureMockMvc
// Makes sure all tests which opens transcation get rolled back after run
@Transactional
@SpringBootTest
public class UseCaseIT {

    @Autowired
    private CustomerUtil customerUtil;

    @Autowired
    private RouterUtil routerUtil;


    @Test
    public void mvpUseCase() throws Exception {
        // Given
        final String walletAddress = "integrationTestWalletAddress";
        CustomerDTO customerDTO = customerUtil.registerCustomer(walletAddress);
        final String customerSecret = customerDTO.getSecret();
        final String macAddress = "00:00:00:00:00:00";
        routerUtil.mapRouterToCustomer(macAddress, customerSecret);
        RouterDTO routerDTO = routerUtil.activateRouter(macAddress, customerSecret);
        Assertions.assertThat(routerDTO.getMacAddress()).isEqualTo(macAddress);
    }

}

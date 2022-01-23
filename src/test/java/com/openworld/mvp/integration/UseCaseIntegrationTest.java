package com.openworld.mvp.integration;

import com.openworld.mvp.api.router.RouterDTO;
import com.openworld.mvp.integration.utils.CustomerUtil;
import com.openworld.mvp.integration.utils.RouterUtil;
import com.openworld.mvp.api.customer.CustomerDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@ActiveProfiles("test")
@AutoConfigureMockMvc
// Makes sure all tests which opens transcation get rolled back after run
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UseCaseIntegrationTest {

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
        routerUtil.mapRouterToCustomer(walletAddress, customerSecret);
        routerUtil.activateRouter(walletAddress, customerSecret);
    }

}

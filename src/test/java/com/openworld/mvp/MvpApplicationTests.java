package com.openworld.mvp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class MvpApplicationTests {

    @Test
    void getWalletBalance() throws ExecutionException, InterruptedException, TimeoutException {
        final String ganachePort = "7545";
        final String localEtherAPI = "http://localhost:" + ganachePort;
        final String localEtherWalletAddress = "0x9c98b4c8090d27eFB4125596A982f35D5d50F717";
        final Web3j client = Web3j.build(new HttpService(localEtherAPI));
        final EthGetBalance clientResponse =
                client
                        .ethGetBalance(localEtherWalletAddress, DefaultBlockParameter.valueOf("latest"))
                        .sendAsync()
                        .get(5, TimeUnit.SECONDS);

        final BigDecimal conversionValue = new BigDecimal(1000000000000000000L);

        final BigInteger unscaledBalance = clientResponse.getBalance();
        final BigDecimal scaledBalance = new BigDecimal(unscaledBalance).divide(conversionValue, 2, RoundingMode.HALF_UP);
        System.out.println("Scaled local wallet balance " + scaledBalance + "ETH");
        Assertions.assertThat(scaledBalance).isEqualTo(new BigDecimal("100.00"));
    }

}

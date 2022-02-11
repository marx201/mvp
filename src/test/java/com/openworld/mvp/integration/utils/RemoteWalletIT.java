package com.openworld.mvp.integration.utils;

import com.openworld.mvp.integration.ganache.TransactionESI;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class RemoteWalletIT {

    @Test
    void getWalletBalance() throws ExecutionException, InterruptedException, TimeoutException {
        final String ganachePort = "8545";
        final String localEtherAPI = "http://127.0.0.1:" + ganachePort;
        final String localEtherWalletAddress = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";
        final Web3j client = Web3j.build(new HttpService(localEtherAPI));
        final EthGetBalance clientResponse = client.ethGetBalance(localEtherWalletAddress, DefaultBlockParameter.valueOf("latest")).sendAsync().get(5, TimeUnit.SECONDS);

        final BigDecimal conversionValue = new BigDecimal(1000000000000000000L);

        final BigInteger unscaledBalance = clientResponse.getBalance();
        final BigDecimal scaledBalance = new BigDecimal(unscaledBalance).divide(conversionValue, 2, RoundingMode.HALF_UP);

        System.out.println("Unscaled local wallet balance " + unscaledBalance + "ETH");
        System.out.println("Scaled local wallet balance " + scaledBalance + "ETH");
        Assertions.assertThat(scaledBalance).isEqualTo(new BigDecimal("100.00"));
    }

    @Test
    public void getInfoAndTransferToken() throws Exception {

        final String ganachePort = "8545";
        final String localEtherAPI = "http://127.0.0.1:" + ganachePort;

        final String tokenHolderWallet = "0xFFcf8FDEE72ac11b5c542428B35EEF5769C409f0";
        final String privateKeyTokenHolderWallet = "0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1";

        final String targetWallet = "0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1";

        final Web3j client = Web3j.build(new HttpService(localEtherAPI));

        Credentials credentials = Credentials.create(privateKeyTokenHolderWallet);

        final String contractAddress = "0xD17e1233A03aFFB9092D5109179B43d6A8828607";
        ERC20 javaToken = ERC20.load(contractAddress, client, credentials, new DefaultGasProvider());

        String symbol = javaToken.symbol().send();
        String name = javaToken.name().send();
        BigInteger decimal = javaToken.decimals().send();

        System.out.println("symbol: " + symbol);
        System.out.println("name: " + name);
        System.out.println("decimal: " + decimal.intValueExact());

        BigInteger holderBalance = javaToken.balanceOf(tokenHolderWallet).send();
        System.out.println("Tokenholder holderBalance: " + holderBalance.toString());

        BigInteger targetWalletBalance = javaToken.balanceOf(targetWallet).send();
        System.out.println("secondWalletBalance holderBalance: " + targetWalletBalance.toString());

        TransactionReceipt receipt = javaToken.transfer(targetWallet, new BigInteger("1")).send();
        System.out.println("Transaction hash: " + receipt.getTransactionHash());

        BigInteger holderBalance1 = javaToken.balanceOf(tokenHolderWallet).send();
        System.out.println("Tokenholder holderBalance: " + holderBalance1.toString());

        BigInteger targetWalletBalance1 = javaToken.balanceOf(targetWallet).send();
        System.out.println("secondWalletBalance holderBalance: " + targetWalletBalance1.toString());

        Assertions.assertThat(symbol).isEqualTo("XNXX");
        Assertions.assertThat(name).isEqualTo("Max Coin");
        Assertions.assertThat(decimal).isEqualTo("18");
        Assertions.assertThat(targetWalletBalance1).isGreaterThan(targetWalletBalance);

    }

    @Test
    public void test() throws Exception {
        TransactionESI esi = new TransactionESI();
        TransactionReceipt transactionReceipt = esi.transferToken("0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1", 10L);
        System.out.println(transactionReceipt);
    }

}

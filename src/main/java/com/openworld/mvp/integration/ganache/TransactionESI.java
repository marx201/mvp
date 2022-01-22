package com.openworld.mvp.integration.ganache;

import org.springframework.stereotype.Component;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Component
public class TransactionESI {

    private static final String ganachePort = "8545";
    private static final String localEtherAPI = "http://127.0.0.1:" + ganachePort;
    private static final String tokenHolderWallet = "0xFFcf8FDEE72ac11b5c542428B35EEF5769C409f0";
    private static final String privateKeyTokenHolderWallet = "0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1";
    private static final Credentials credentials = Credentials.create(privateKeyTokenHolderWallet);
    private static final String contractAddress = "0xD17e1233A03aFFB9092D5109179B43d6A8828607";
    final Web3j client = Web3j.build(new HttpService(localEtherAPI));
    private final ERC20 javaToken = ERC20.load(contractAddress, client, credentials, new DefaultGasProvider());

    public TransactionReceipt transferToken(final String targetWallet, final Long amount) throws Exception {

        BigInteger holderBalance = javaToken.balanceOf(tokenHolderWallet).send();
        System.out.println("Tokenholder holderBalance: " + holderBalance.toString());

        BigInteger initialTargetWalletBalance = javaToken.balanceOf(targetWallet).send();

        TransactionReceipt receipt = javaToken.transfer(targetWallet, BigInteger.valueOf(amount)).send();

        BigInteger targetWalletBalance = javaToken.balanceOf(targetWallet).send();
        System.out.println(String.format("Wallet %s changed from %s to %s", targetWallet, initialTargetWalletBalance, targetWalletBalance));
        // return
        return receipt;
    }


}

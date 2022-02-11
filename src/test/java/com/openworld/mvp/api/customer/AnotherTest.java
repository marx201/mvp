package com.openworld.mvp.api.customer;

import com.openworld.mvp.api.router.RouterTransformer;
import com.openworld.mvp.api.token.TokenTransformer;
import com.openworld.mvp.bm.customer.CustomerBE;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AnotherTest {
    @InjectMocks
    private CustomerTransformer transformer;

    @Mock
    private TokenTransformer tokenTransformer;

    @Mock
    private RouterTransformer routerTransformer;


    @Test
    void mapDTOList() {
        final CustomerBE customerBE = new CustomerBE();
        customerBE.setId(1L);
        customerBE.setWalletAddress("theWalletAddressDummyValuexYZ!");
        final List<CustomerBE> customerBEList = List.of(customerBE);
        List<CustomerDTO> customerDTOList = transformer.mapDTOList(customerBEList);
        assertThat(customerDTOList.size()).isEqualTo(customerBEList.size());
        assertThat(customerDTOList.get(0).getId()).isEqualTo(customerBEList.get(0).getId());
        assertThat(customerDTOList.get(0).getWalletAddress()).isEqualTo(customerBEList.get(0).getWalletAddress());
    }

    @Test
    void mapDTO() {
        final CustomerBE customerBE = new CustomerBE();
        customerBE.setId(1L);
        customerBE.setWalletAddress("theWalletAddressDummyValuexYZ!");
        CustomerDTO actualCustomerDTO = transformer.mapDTO(customerBE);
        assertThat(actualCustomerDTO.getId()).isEqualTo(1L);
        assertThat(actualCustomerDTO.getWalletAddress()).isEqualTo("theWalletAddressDummyValuexYZ!");
    }
}

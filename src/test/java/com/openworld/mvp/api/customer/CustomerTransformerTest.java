package com.openworld.mvp.api.customer;

import com.openworld.mvp.api.router.RouterTransformer;
import com.openworld.mvp.api.token.TokenTransformer;
import com.openworld.mvp.bm.customer.CustomerBE;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
public class CustomerTransformerTest {

    @InjectMocks
    private CustomerTransformer transformer;


    @BeforeEach
    void init() {
        /*transformer = new CustomerTransformer();*/
    }

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
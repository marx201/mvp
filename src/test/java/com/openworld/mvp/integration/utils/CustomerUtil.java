package com.openworld.mvp.integration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openworld.mvp.api.customer.CustomerDTO;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class CustomerUtil {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    public CustomerDTO registerCustomer(final String walletAddress) throws Exception {
        final String query = "?walletAddress=" + walletAddress;
        final MvcResult createRegisterCustomerResult = mockMvc
                .perform(post("/api/v1/customer/" + query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        return objectMapper.readValue(createRegisterCustomerResult.getResponse().getContentAsString(), CustomerDTO.class);
    }
}

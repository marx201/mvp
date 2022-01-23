package com.openworld.mvp.integration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openworld.mvp.api.customer.CustomerDTO;
import com.openworld.mvp.api.router.RouterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class RouterUtil {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    public RouterDTO mapRouterToCustomer(final String macAddress, final String secret) throws Exception {
        final String query = "?macAddress=" + macAddress + "&secret=" + secret;
        final MvcResult createMapRouterToCustomerResult = mockMvc
                .perform(post("/api/v1/router/" + query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        return objectMapper.readValue(createMapRouterToCustomerResult.getResponse().getContentAsString(), RouterDTO.class);
    }

    public RouterDTO activateRouter(final String macAddress, final String secret) throws Exception {
        final String query = "?macAddress=" + macAddress + "&secret=" + secret;
        final MvcResult createActivateRouterResult = mockMvc
                .perform(post("/api/v1/router/activate" + query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        return objectMapper.readValue(createActivateRouterResult.getResponse().getContentAsString(), RouterDTO.class);
    }

    public RouterDTO deactivateRouter(final String walletAddress, final String secret) throws Exception {
        final String query = "?walletAddress=" + walletAddress + "&secret=" + secret;
        final MvcResult createDeActivateRouterResult = mockMvc
                .perform(post("/api/v1/router/deactivate" + query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        return objectMapper.readValue(createDeActivateRouterResult.getResponse().getContentAsString(), RouterDTO.class);
    }

}

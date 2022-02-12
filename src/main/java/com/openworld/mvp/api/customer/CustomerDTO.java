package com.openworld.mvp.api.customer;

import com.openworld.mvp.api.router.RouterDTO;
import com.openworld.mvp.api.token.TokenDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "Customer API Object", value = "Customer")
public class CustomerDTO {
    private Long id;
    private String walletAddress;
    private String secret;
    private List<RouterDTO> routers;
    private List<TokenDTO> tokens;
}

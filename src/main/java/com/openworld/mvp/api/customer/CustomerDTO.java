package com.openworld.mvp.api.customer;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "Customer API Object", value = "Customer")
public class CustomerDTO {
    private Long id;
    private String walletAddress;
}

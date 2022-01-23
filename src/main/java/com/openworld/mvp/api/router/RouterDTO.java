package com.openworld.mvp.api.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openworld.mvp.api.customer.CustomerDTO;
import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class RouterDTO {
    private Long id;
    @JsonIgnore
    private CustomerDTO customer;

    private String macAddress;

    private boolean isAlive;
}

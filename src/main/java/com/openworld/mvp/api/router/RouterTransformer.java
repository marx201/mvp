package com.openworld.mvp.api.router;

import com.openworld.mvp.api.customer.CustomerTransformer;
import com.openworld.mvp.bm.router.RouterBE;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RouterTransformer {

    private CustomerTransformer customerTransformer;

    public List<RouterDTO> mapDTOList(List<RouterBE> routerBEList) {
        return routerBEList.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    public RouterDTO mapDTO(final RouterBE routerBE) {
        return RouterDTO
                .builder()
                .id(routerBE.getId())
                .customer(customerTransformer.mapDTO(routerBE.getCustomer()))
                .build();
    }



}

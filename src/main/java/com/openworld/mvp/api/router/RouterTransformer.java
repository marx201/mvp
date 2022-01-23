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

    public List<RouterDTO> mapDTOList(List<RouterBE> routerBEList) {
        return routerBEList.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    public RouterDTO mapDTO(final RouterBE routerBE) {
        return RouterDTO
                .builder()
                .macAddress(routerBE.getMacAddress())
                .isAlive(routerBE.isAlive())
                .id(routerBE.getId())
                .build();
    }


}

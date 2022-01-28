package com.openworld.mvp.api.router;

import com.openworld.mvp.api.customer.CustomerTransformer;
import com.openworld.mvp.bm.router.RouterBE;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RouterTransformer {

    public List<RouterDTO> mapDTOList(List<RouterBE> routerBEList) {
        if (routerBEList != null) {
            return routerBEList.stream().map(this::mapDTO).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
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

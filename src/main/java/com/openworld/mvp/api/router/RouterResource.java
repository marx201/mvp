package com.openworld.mvp.api.router;

import com.openworld.mvp.bm.router.RouterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/router")
public class RouterResource {

    private RouterTransformer transformer;
    private RouterService service;

    @PostMapping("/")
    public RouterDTO mapRouter(@RequestParam("secret") final String secret, @RequestParam("macAddress") final String macAddress) {
        return transformer.mapDTO(service.mapCustomer(secret, macAddress));
    }

    @PostMapping("/activate")
    public RouterDTO activate(@RequestParam("secret") final String secret, @RequestParam("macAddress") final String macAddress) {
        return transformer.mapDTO(service.activateRouter(secret, macAddress));
    }

    @PostMapping("/deactivate")
    public RouterDTO deactivate(@RequestParam("secret") final String secret, @RequestParam("macAddress") final String macAddress) {
        return transformer.mapDTO(service.deactivateRouter(secret, macAddress));
    }

}

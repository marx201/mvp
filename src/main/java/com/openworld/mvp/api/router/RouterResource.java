package com.openworld.mvp.api.router;

import com.openworld.mvp.bm.router.RouterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status")
    public RouterDTO status(@RequestParam("secret") final String secret, @RequestParam("macAddress") final String macAddress) {
        return transformer.mapDTO(service.stillAlive(secret, macAddress));
    }

}

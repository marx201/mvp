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
    public RouterDTO mapRouter(@RequestParam("secret") final String secret) {
        return transformer.mapDTO(service.save(secret));
    }

    @PostMapping("/")
    public RouterDTO activate(@RequestParam("secret") final String secret) {
        return transformer.mapDTO(service.save(secret));
    }

}

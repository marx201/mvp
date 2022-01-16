package com.openworld.mvp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusResource {

    @GetMapping("")
    public String getStatus() {
        return "Open World API, Up and running.";
    }
}

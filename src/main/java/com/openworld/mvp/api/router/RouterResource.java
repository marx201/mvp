package com.openworld.mvp.api.router;

import com.openworld.mvp.bm.router.RouterImportCsvHelper;
import com.openworld.mvp.bm.router.RouterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/router")
public class RouterResource {

    private RouterTransformer transformer;
    private RouterService service;

    @GetMapping("/")
    public List<RouterDTO> getRouters() {
        return transformer.mapDTOList(service.findAll());
    }

    @PostMapping("/map")
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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadRouters(@RequestParam("file") MultipartFile file) {
        String message = "";
        System.out.println("Recieved file upload request");

        try {
            service.saveAll(file);
            message = "Uploaded the router file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }

}

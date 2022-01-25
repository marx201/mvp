package com.openworld.mvp.bm.router;

import com.openworld.mvp.bm.customer.CustomerBE;
import com.openworld.mvp.bm.customer.CustomerService;
import com.openworld.mvp.exception.RouterInvalidStateException;
import com.openworld.mvp.exception.RouterNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RouterService {

    private CustomerService customerService;
    private RouterRepository routerRepository;
    private AliveTimeStampRepository aliveTimeStampRepository;

    public RouterBE mapCustomer(final String secret, final String macAddress) {
        CustomerBE customer = customerService.findBySecret(secret);
        RouterBE router = findByMacAddress(macAddress);
        router.setCustomer(customer);
        return routerRepository.save(router);
    }

    public RouterBE activateRouter(final String secret, final String macAddress) {
        // Customer is only there for validation (TODO)
        CustomerBE customer = customerService.findBySecret(secret);
        RouterBE router = findByMacAddress(macAddress);
        if (router.isAlive()) {
            throw new RouterInvalidStateException("Router with MAC " + macAddress + " is running.");
        }
        router.setAlive(true);
        return routerRepository.save(router);
    }

    public RouterBE deactivateRouter(final String secret, final String macAddress) {
        // Customer is only there for validation (TODO)
        CustomerBE customer = customerService.findBySecret(secret);
        RouterBE router = findByMacAddress(macAddress);
        if (!router.isAlive()) {
            throw new RouterInvalidStateException("Router with MAC " + macAddress + " is not running.");
        }
        router.setAlive(false);

        return routerRepository.save(router);
    }

    public RouterBE findByMacAddress(final String macAddress) {
        Optional<RouterBE> router = routerRepository.findByMacAddress(macAddress);
        if (router.isEmpty()) {
            throw new RouterNotExistsException("Router with MAC " + macAddress + " does not exist.");
        }
        return router.get();
    }

    public RouterBE stillAlive(final String secret, final String macAddress) {
        CustomerBE customer = customerService.findBySecret(secret);
        RouterBE router = findByMacAddress(macAddress);
        if (!router.isAlive()){
            throw new RouterInvalidStateException("Router is currently not running. Aliveness probe failed.");
        }
        final LocalDateTime now = LocalDateTime.now();
        AliveTimeStampBE aliveTimeStampBE = new AliveTimeStampBE();
        aliveTimeStampBE.setTimeStamp(now);
        aliveTimeStampBE.setRouter(router);
        aliveTimeStampRepository.save(aliveTimeStampBE);
        return router;
    }
}

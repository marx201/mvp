package com.openworld.mvp.bm.router;

import com.openworld.mvp.bm.customer.CustomerBE;
import com.openworld.mvp.bm.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RouterService {

    private CustomerService customerService;
    private RouterRepository routerRepository;

    public RouterBE save(final String secret) {
        Optional<CustomerBE> customer = customerService.findBySecret(secret);
        if (customer.isPresent()) {
            RouterBE routerBE = new RouterBE();
            routerBE.setCustomer(customer.get());
            return routerRepository.save(routerBE);
        }
        return null;

    }

    public RouterBE activate(final String secret) {
        Optional<CustomerBE> customer = customerService.findBySecret(secret);
        if (customer.isPresent()) {
            RouterBE routerBE = new RouterBE();
            routerBE.setCustomer(customer.get());
            return routerRepository.save(routerBE);
        }
        return null;

    }
}

package com.openworld.mvp.bm.router;

import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "router")
public class RouterBE {
    @SequenceGenerator(
            name = "router_sequence",
            sequenceName = "router_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "router_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "customer_id")
    private CustomerBE customer;

    private String macAddress;

    private boolean isAlive;

    // todo pre persist last updated, created
}

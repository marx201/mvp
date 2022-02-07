package com.openworld.mvp.bm.router;

import com.openworld.mvp.bm.customer.CustomerBE;
import com.openworld.mvp.bm.localwallet.LocalWalletBE;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String serialNumber;

    private boolean isAlive;

    @OneToMany(mappedBy = "router", cascade = CascadeType.ALL)
    private List<AliveTimeStampBE> aliveTimeStamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localwallet_id", referencedColumnName = "id")
    private LocalWalletBE localWalletBe;
    // todo pre persist last updated, created
}

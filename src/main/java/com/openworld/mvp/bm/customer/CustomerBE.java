package com.openworld.mvp.bm.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openworld.mvp.bm.router.RouterBE;
import com.openworld.mvp.bm.token.TokenBE;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerBE {
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String walletAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<TokenBE> tokens;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<RouterBE> routers;

    private String secretKey = RandomStringUtils.randomAlphanumeric(4);

}

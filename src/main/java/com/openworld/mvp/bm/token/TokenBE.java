package com.openworld.mvp.bm.token;


import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class TokenBE {
    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_sequence"
    )
    private Long id;
    private Long amount;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false)
    private CustomerBE customer;
}

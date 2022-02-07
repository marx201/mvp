package com.openworld.mvp.bm.localwallet;

import com.openworld.mvp.bm.localtransactions.LocalTransactionBE;
import com.openworld.mvp.bm.router.RouterBE;
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
@Table(name = "local_wallet")
public class LocalWalletBE {
    @SequenceGenerator(
            name = "local_wallet_sequence",
            sequenceName = "local_wallet_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "local_wallet_sequence"
    )
    private Long id;
    private double balance;

    @OneToOne(mappedBy = "localWalletBe", cascade = CascadeType.ALL)
    private RouterBE routerBE;

    @OneToMany(mappedBy = "localWallet", cascade = CascadeType.ALL)
    private List<LocalTransactionBE> routers = new ArrayList<>();
}

package com.openworld.mvp.bm.localtransactions;

import com.openworld.mvp.bm.customer.CustomerBE;
import com.openworld.mvp.bm.localwallet.LocalWalletBE;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "local_transaction")
public class LocalTransactionBE {
    @SequenceGenerator(
            name = "local_transaction_sequence",
            sequenceName = "local_transaction_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "local_transaction_sequence"
    )
    private Long id;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private LocalTransactionType type;

    private Date creation;

    @ManyToOne
    @JoinColumn(
            name = "local_wallet_id")
    private LocalWalletBE localWallet;
}

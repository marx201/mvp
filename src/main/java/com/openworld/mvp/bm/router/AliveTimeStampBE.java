package com.openworld.mvp.bm.router;

import com.openworld.mvp.bm.customer.CustomerBE;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "alive_timestamp")
public class AliveTimeStampBE {

    @SequenceGenerator(
            name = "alive_timestamp_sequence",
            sequenceName = "alive_timestamp_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "alive_timestamp_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "router_id")
    private RouterBE router;

    private LocalDateTime timeStamp;

}

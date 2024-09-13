package me.yukitale.cryptoexchange.panel.worker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.yukitale.cryptoexchange.exchange.model.Coin;

import jakarta.persistence.*;

@Entity
@Table(name = "stable_pumps")
@Getter
@Setter
@NoArgsConstructor
public class StablePump {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_symbol")
    private Coin coin;

    private double percent;

    public StablePump(Coin coin, double percent) {
        this.coin = coin;
        this.percent = percent;
    }
}

package com.hyemink.probability_simulator.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CoinTossGenerator {
    public TossResult toss(int count) {

        List<Coin> coins = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            coins.add(Coin.toss());
        }

        return new TossResult(coins);
    }
}

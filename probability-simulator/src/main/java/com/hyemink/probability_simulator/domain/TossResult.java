package com.hyemink.probability_simulator.domain;
import java.util.List;

import java.util.ArrayList;
public class TossResult {
    private final List<Coin> coins;

    public TossResult(List<Coin> coins) {
        this.coins = coins;
    }

    public int countHeads() {
        return (int) coins.stream()
                .filter(coin -> coin == Coin.HEAD)
                .count();
    }


    public Coin getCoin(int position) {
        return coins.get(position);
    }

    public List<Integer> getHeadPositions() {

        List<Integer> positions = new ArrayList<>();

        for(int i = 0; i < coins.size(); i++) {
            if(coins.get(i) == Coin.HEAD) {
                positions.add(i);
            }
        }

        return positions;
    }
    public List<Integer> getAllPositions() {

        List<Integer> positions = new ArrayList<>();

        for(int i = 0; i < coins.size(); i++) {
            positions.add(i);
        }

        return positions;
    }


    public int size() {
        return coins.size();
    }


    public boolean isAccepted(int minimumHeads) {
        return countHeads() >= minimumHeads;
    }
}

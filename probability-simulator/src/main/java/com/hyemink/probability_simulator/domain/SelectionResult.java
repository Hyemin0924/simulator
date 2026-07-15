package com.hyemink.probability_simulator.domain;

public class SelectionResult {
    private final int position;
    private final Coin coin;

    public SelectionResult(int position, Coin coin) {
        this.position = position;
        this.coin = coin;
    }


    public boolean isTail() {
        return coin == Coin.TAIL;
    }


    public int getPosition() {
        return position;
    }


    public Coin getCoin() {
        return coin;
    }
}

package com.hyemink.probability_simulator.domain;

public class StrategyComparisonResult {

    private final Coin stayCoin;
    private final Coin switchCoin;
    private final boolean hostAwareOfChoice;


    public StrategyComparisonResult(
            Coin stayCoin,
            Coin switchCoin,
            boolean hostAwareOfChoice
    ) {
        this.stayCoin = stayCoin;
        this.switchCoin = switchCoin;
        this.hostAwareOfChoice = hostAwareOfChoice;
    }


    public boolean isStayTail() {
        return stayCoin == Coin.TAIL;
    }


    public boolean isSwitchTail() {
        return switchCoin == Coin.TAIL;
    }


    public boolean isHostAwareOfChoice() {
        return hostAwareOfChoice;
    }
}
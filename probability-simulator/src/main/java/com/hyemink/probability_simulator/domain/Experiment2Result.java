package com.hyemink.probability_simulator.domain;

public class Experiment2Result {

    private final boolean hostAwareOfChoice;
    private final ExperimentResult stayResult;
    private final ExperimentResult switchResult;


    public Experiment2Result(
            boolean hostAwareOfChoice,
            ExperimentResult stayResult,
            ExperimentResult switchResult
    ) {
        this.hostAwareOfChoice = hostAwareOfChoice;
        this.stayResult = stayResult;
        this.switchResult = switchResult;
    }


    public boolean isHostAwareOfChoice() {
        return hostAwareOfChoice;
    }


    public ExperimentResult getStayResult() {
        return stayResult;
    }


    public ExperimentResult getSwitchResult() {
        return switchResult;
    }


    public double getProbabilityDifference() {
        return switchResult.getTailProbability()
                - stayResult.getTailProbability();
    }
}
package com.hyemink.probability_simulator.domain;

public class ExperimentResult {

    private final int totalTrials;
    private final int acceptedTrials;
    private final int tailSelections;


    public ExperimentResult(
            int totalTrials,
            int acceptedTrials,
            int tailSelections
    ) {
        this.totalTrials = totalTrials;
        this.acceptedTrials = acceptedTrials;
        this.tailSelections = tailSelections;
    }


    public double getTailProbability() {

        if (acceptedTrials == 0) {
            return 0;
        }

        return (double) tailSelections / acceptedTrials;
    }


    public int getTotalTrials() {
        return totalTrials;
    }


    public int getAcceptedTrials() {
        return acceptedTrials;
    }


    public int getTailSelections() {
        return tailSelections;
    }
}
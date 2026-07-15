package com.hyemink.probability_simulator.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PositionSet {

    private final Set<Integer> positions;

    private final Set<Integer> disclosedPositions;


    public PositionSet(List<Integer> positions) {
        this.positions = new HashSet<>(positions);
        this.disclosedPositions = new HashSet<>();
    }


    public void disclose(List<Integer> positions) {
        disclosedPositions.addAll(positions);
    }


    public List<Integer> getHiddenPositions() {

        return positions.stream()
                .filter(position -> !disclosedPositions.contains(position))
                .toList();
    }


    public boolean isDisclosed(int position) {
        return disclosedPositions.contains(position);
    }
}
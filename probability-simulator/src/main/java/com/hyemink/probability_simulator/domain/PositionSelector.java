package com.hyemink.probability_simulator.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PositionSelector {


    public List<Integer> selectRandom(
            List<Integer> positions,
            int count
    ) {

        Collections.shuffle(positions);

        return positions.subList(0, count);
    }
}
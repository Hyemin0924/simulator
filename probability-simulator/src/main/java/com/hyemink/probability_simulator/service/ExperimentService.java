package com.hyemink.probability_simulator.service;

import com.hyemink.probability_simulator.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExperimentService {

    private static final int TOTAL_COIN_COUNT = 1000;
    private static final int MINIMUM_HEAD_COUNT = 500;
    private static final int DISCLOSED_COUNT = 500;


    private final CoinTossGenerator coinTossGenerator;
    private final PositionSelector positionSelector;

    private final Random random = new Random();


    public ExperimentService(
            CoinTossGenerator coinTossGenerator,
            PositionSelector positionSelector
    ) {
        this.coinTossGenerator = coinTossGenerator;
        this.positionSelector = positionSelector;
    }


    public SelectionResult runSingleExperiment() {

        // 1. 동전 1000개 생성
        TossResult tossResult =
                coinTossGenerator.toss(TOTAL_COIN_COUNT);


        // 2. 앞면 500개 이상인지 확인
        if (!tossResult.isAccepted(MINIMUM_HEAD_COUNT)) {
            return null;
        }


        // 3. 전체 위치 생성
        PositionSet positionSet =
                new PositionSet(
                        tossResult.getAllPositions()
                );


        // 4. 앞면 위치 중 동생이 공개
        List<Integer> disclosedPositions =
                positionSelector.selectRandom(
                        tossResult.getHeadPositions(),
                        DISCLOSED_COUNT
                );


        positionSet.disclose(disclosedPositions);


        // 5. 공개되지 않은 위치 가져오기
        List<Integer> hiddenPositions =
                positionSet.getHiddenPositions();


        // 6. 내가 하나 선택
        int selectedPosition =
                hiddenPositions.get(
                        random.nextInt(hiddenPositions.size())
                );


        // 7. 결과 반환
        return new SelectionResult(
                selectedPosition,
                tossResult.getCoin(selectedPosition)
        );
    }
    public ExperimentResult runExperiment(int count) {

        int acceptedTrials = 0;
        int tailSelections = 0;


        for(int i = 0; i < count; i++) {

            SelectionResult result;

            do {
                result = runSingleExperiment();
            }
            while(result == null);


            acceptedTrials++;


            if(result.isTail()) {
                tailSelections++;
            }
        }


        return new ExperimentResult(
                count,
                acceptedTrials,
                tailSelections
        );
    }
}
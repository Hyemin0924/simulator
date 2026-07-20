package com.hyemink.probability_simulator.service;

import com.hyemink.probability_simulator.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Experiment2Service {

    private static final int TOTAL_COIN_COUNT = 1000;
    private static final int MINIMUM_HEAD_COUNT = 501;
    private static final int DISCLOSED_COUNT = 500;


    private final CoinTossGenerator coinTossGenerator;
    private final PositionSelector positionSelector;

    private final Random random = new Random();


    public Experiment2Service(
            CoinTossGenerator coinTossGenerator,
            PositionSelector positionSelector
    ) {
        this.coinTossGenerator = coinTossGenerator;
        this.positionSelector = positionSelector;
    }


    /**
     * @param isHostAwareOfChoice
     *   true  : 동생이 내가 고른 위치를 알고 있어서, 그 위치를 의도적으로 배제하고 공개
     *   false : 동생이 내가 고른 위치를 모른 채, 앞면 전체 중 무작위로 공개
     *           (이 경우 내 선택이 우연히 공개되면 이 시행은 폐기되고 재시도됨)
     */
    public StrategyComparisonResult runSingleTrial(boolean isHostAwareOfChoice) {

        // 1. 동전 1000개 생성
        TossResult tossResult =
                coinTossGenerator.toss(TOTAL_COIN_COUNT);


        // 2. 앞면 501개 이상인지 확인
        if (!tossResult.isAccepted(MINIMUM_HEAD_COUNT)) {
            return null;
        }


        // 3. 내가 먼저 하나 선택 (아무 정보 없는 상태)
        List<Integer> allPositions = tossResult.getAllPositions();

        int selectedPosition =
                allPositions.get(
                        random.nextInt(allPositions.size())
                );


        // 4. 공개 후보 결정 (핵심 분기: 동생이 내 선택을 아는가)
        List<Integer> headPositions = tossResult.getHeadPositions();

        List<Integer> disclosureCandidates;

        if (isHostAwareOfChoice) {
            // 의도적 배제: 내 선택은 절대 공개 후보에 들어가지 않음
            disclosureCandidates = headPositions.stream()
                    .filter(position -> position != selectedPosition)
                    .toList();
        } else {
            // 무작위 배제: 내 선택도 공개 후보에 포함될 수 있음
            disclosureCandidates = headPositions;
        }


        // 5. 공개할 500개 무작위 선정
        List<Integer> disclosedPositions =
                positionSelector.selectRandom(
                        new ArrayList<>(disclosureCandidates),
                        DISCLOSED_COUNT
                );


        // 6. 동생이 모르는 상태에서 우연히 내 선택을 공개해버린 경우
        //    -> 이 시행은 유효하지 않으므로 폐기하고 재시도
        if (!isHostAwareOfChoice && disclosedPositions.contains(selectedPosition)) {
            return null;
        }


        // 7. 공개 반영
        PositionSet positionSet = new PositionSet(allPositions);
        positionSet.disclose(disclosedPositions);


        // 8. 공개되지 않은 위치 중, 내 선택을 제외한 나머지에서 "바꿈" 후보 선정
        List<Integer> hiddenPositions = positionSet.getHiddenPositions();

        List<Integer> switchCandidates = hiddenPositions.stream()
                .filter(position -> position != selectedPosition)
                .toList();

        int switchPosition =
                switchCandidates.get(
                        random.nextInt(switchCandidates.size())
                );


        // 9. 같은 시행 안에서 "유지"와 "바꿈" 결과를 동시에 반환
        return new StrategyComparisonResult(
                tossResult.getCoin(selectedPosition),
                tossResult.getCoin(switchPosition),
                isHostAwareOfChoice
        );
    }


    public Experiment2Result runExperiment(int count, boolean isHostAwareOfChoice) {

        int acceptedTrials = 0;
        int stayTailCount = 0;
        int switchTailCount = 0;

        for (int i = 0; i < count; i++) {

            StrategyComparisonResult result;

            do {
                result = runSingleTrial(isHostAwareOfChoice);
            } while (result == null);

            acceptedTrials++;

            if (result.isStayTail()) {
                stayTailCount++;
            }
            if (result.isSwitchTail()) {
                switchTailCount++;
            }
        }

        ExperimentResult stayResult =
                new ExperimentResult(count, acceptedTrials, stayTailCount);

        ExperimentResult switchResult =
                new ExperimentResult(count, acceptedTrials, switchTailCount);

        return new Experiment2Result(isHostAwareOfChoice, stayResult, switchResult);
    }
}
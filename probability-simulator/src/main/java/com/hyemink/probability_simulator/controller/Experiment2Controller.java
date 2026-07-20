package com.hyemink.probability_simulator.controller;

import com.hyemink.probability_simulator.domain.Experiment2Result;
import com.hyemink.probability_simulator.service.Experiment2Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiment2")
public class Experiment2Controller {

    private final Experiment2Service experiment2Service;

    public Experiment2Controller(Experiment2Service experiment2Service) {
        this.experiment2Service = experiment2Service;
    }

    @GetMapping
    public Experiment2Result run(
            @RequestParam(defaultValue = "100000") int count,
            @RequestParam(defaultValue = "true") boolean hostAware
    ) {
        return experiment2Service.runExperiment(count, hostAware);
    }
}
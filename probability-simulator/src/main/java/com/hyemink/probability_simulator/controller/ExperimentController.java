package com.hyemink.probability_simulator.controller;

import com.hyemink.probability_simulator.domain.ExperimentResult;
import com.hyemink.probability_simulator.service.ExperimentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;@RestController
@RequestMapping("/experiment")
public class ExperimentController {


    private final ExperimentService experimentService;


    public ExperimentController(
            ExperimentService experimentService
    ) {
        this.experimentService = experimentService;
    }


    @GetMapping
    public ExperimentResult run(
            @RequestParam(defaultValue = "100000") int count
    ) {

        return experimentService.runExperiment(count);
    }

}
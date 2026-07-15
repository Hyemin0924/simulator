package com.hyemink.probability_simulator.controller;

import com.hyemink.probability_simulator.domain.ExperimentResult;
import com.hyemink.probability_simulator.service.ExperimentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experiment")
public class ExperimentViewController {

    private final ExperimentService experimentService;

    public ExperimentViewController(
            ExperimentService experimentService
    ) {
        this.experimentService = experimentService;
    }


    @GetMapping("/view")
    public String view(
            @RequestParam(defaultValue = "100000") int count,
            Model model
    ) {

        ExperimentResult result =
                experimentService.runExperiment(count);

        model.addAttribute("result", result);

        return "experiment";
    }
}
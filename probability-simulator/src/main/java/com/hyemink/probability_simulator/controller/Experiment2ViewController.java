package com.hyemink.probability_simulator.controller;

import com.hyemink.probability_simulator.domain.Experiment2Result;
import com.hyemink.probability_simulator.service.Experiment2Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experiment2")
public class Experiment2ViewController {

    private final Experiment2Service experiment2Service;

    public Experiment2ViewController(Experiment2Service experiment2Service) {
        this.experiment2Service = experiment2Service;
    }

    @GetMapping("/view")
    public String view(
            @RequestParam(defaultValue = "100000") int count,
            @RequestParam(defaultValue = "true") boolean hostAware,
            Model model
    ) {
        Experiment2Result result =
                experiment2Service.runExperiment(count, hostAware);

        model.addAttribute("result", result);

        return "experiment2";
    }
}
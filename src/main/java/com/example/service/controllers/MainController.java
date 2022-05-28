package com.example.service.controllers;

import com.example.service.Config;
import com.example.service.asyncCounter.AsyncCounter;
import com.example.service.cache.Cache;
import com.example.service.process.Solution;
import com.example.service.process.ProcessedParams;
import com.example.service.responses.ExceptionResponse;
import com.example.service.stats.Stats;
import com.example.service.stats.StatsProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    public static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(Config.class);

    Solution solution;
    @Autowired
    public void setSolution(Solution solution) {
        this.solution = solution;
    }


    private StatsProvider statsProvider;
    @Autowired
    public void setStatsProvider(StatsProvider statsProvider) {
        this.statsProvider = statsProvider;
    }

    @GetMapping("/home")
    public ResponseEntity<ProcessedParams> GetAnswer(
            @RequestParam(value="year") Integer year) {

        AsyncCounter.increase();

        var params = solution.calculateOutputParams(year);
        return new ResponseEntity<>(params, HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticCache(), HttpStatus.OK);
    }

    @PostMapping("/solve_json")
    public ResponseEntity<ProcessedParams> solveSingleJson(
            @RequestBody Integer params
    ) {
        var parameters = solution.calculateOutputParams(params);
        return new ResponseEntity<>(parameters, HttpStatus.OK);
    }

    @PostMapping("/solve_bulk")
    public ResponseEntity<List<ProcessedParams>> solveBulkJson(
            @RequestBody @NotNull List<Integer> params
    ) {
        var list = params
                .stream()
                .map(solution::calculateOutputParams)
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/stats")
    public Stats getStats() {
        return statsProvider.calculate();
    }

}
package com.example.service.stats;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
public class StatsProvider {

    private static List<Integer> yearsList = new ArrayList<>();

    private static boolean shouldBeRecalculated = true;

    private Long totalRequests = 0L;
    private Long wrongRequests = 0L;

    private Integer min = 0;

    private Integer max = 0;

    private Integer mostCommon = 0;


    public void increaseTotalRequests() {
        totalRequests++;
    }

    public void increaseWrongRequests() {
        wrongRequests++;
    }

    public Stats calculate() {
        System.out.println(yearsList);

        if (!shouldBeRecalculated) {
            return null;
        }

        try {
            mostCommon = yearsList
                    .stream()
                    .reduce(
                            BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(yearsList, o)))
                    ).orElse(0);

            yearsList = yearsList
                    .stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            min = yearsList
                    .stream()
                    .min(Comparator.comparing(Long::valueOf))
                    .orElse(0);

            max = yearsList
                    .stream()
                    .max(Comparator.comparing(Long::valueOf))
                    .orElse(0);

            shouldBeRecalculated = false;
        } catch (NullPointerException exception) {
            throw new RuntimeException(exception);
        }
        return new Stats(totalRequests,wrongRequests,min,max,mostCommon);
    }

    public void addRoot(@NotNull Integer year) {
        yearsList.add(year);
        shouldBeRecalculated = true;
    }
}
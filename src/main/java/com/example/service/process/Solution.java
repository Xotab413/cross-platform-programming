package com.example.service.process;

import com.example.service.Config;
import com.example.service.cache.Cache;
import com.example.service.controllers.MainController;
import com.example.service.stats.StatsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Solution {

    Logger logger = LoggerFactory.getLogger(Solution.class);

    Cache cache;
    @Autowired
    public void setCache(Cache cache) {
        this.cache = cache;
    }


    private StatsProvider statsProvider;

    @Autowired
    public void setStatsProvider(StatsProvider statsProvider) {
        this.statsProvider = statsProvider;
    }

    public boolean checkYear(Integer year) {
        return year != null && year >= 0;
    }

    public ProcessedParams calculateOutputParams(Integer year) {
        statsProvider.increaseTotalRequests();
        if (!checkYear(year)) {
            statsProvider.increaseWrongRequests();
            throw new RuntimeException("Incorrect year!");
        }

        int days;
        ProcessedParams temp = cache.find(year);

        // not found
        if (temp == null) {
            logger.warn("Calculating output parameters");

            days = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) ? 366 : 365;
            boolean isLeapYear = days == 366;

            temp = new ProcessedParams(days, isLeapYear);
            cache.add(year, temp);
            statsProvider.addRoot(year);
            return temp;
        }

        return temp;
    }

}

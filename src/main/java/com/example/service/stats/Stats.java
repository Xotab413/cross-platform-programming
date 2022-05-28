package com.example.service.stats;

public class Stats {

    public Long getTotalRequests() {
        return totalRequests;
    }

    public Long getWrongRequests() {
        return wrongRequests;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMostCommon() {
        return mostCommon;
    }

    private Long totalRequests = 0L;
    private Long wrongRequests = 0L;

    private Integer min = 0;

    private Integer max = 0;

    private Integer mostCommon = 0;
    public Stats(Long total,Long wrong,Integer min,Integer max,Integer common){
        totalRequests = total;
        wrongRequests = wrong;
        this.min = min;
        this.max = max;
        mostCommon = common;
    }
}


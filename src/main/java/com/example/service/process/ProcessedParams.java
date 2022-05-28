package com.example.service.process;

public class ProcessedParams {

    int days;
    Boolean isLeapYear;

    public ProcessedParams(int _days, boolean _isLeapYear) {
        days = _days;
        isLeapYear = _isLeapYear;
    }

    public int getDays() {
        return days;
    }
}

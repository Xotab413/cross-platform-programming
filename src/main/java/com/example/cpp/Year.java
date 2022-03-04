package com.example.cpp;

public class Year {
    int number;
    String isLeap;

    public Year(int _number) {
        number = _number;
        isLeap();
    }

    int getNumber() {
        return number;
    }

    private void isLeap() {
        if (((number % 4 == 0) && (number % 100 != 0)) || (number % 400 == 0)) {
            isLeap = "Leap";
        } else {
            isLeap = "Not leap";
        }
    }
}

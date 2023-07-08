package com.example.javacalculator.ReversePolishNotation;

public interface IsOperator {
    static boolean isOperator(char mathOperator) {
        if (("+-/*^()".indexOf(mathOperator) != -1)) {
            return true;
        }
        return false;
    }
}

package com.example.javacalculator;

// Reverse Polish Notation
public class RPN {
    public static double calculate(String expression) {

    }

    private static String getExpression(String expression) {

    }

    private static double count(String expression) {

    }

    private static boolean isOperator(char c) {
        if (("+-/*()".indexOf(c) != -1)) {
            return true;
        }
        return false;
    }
}

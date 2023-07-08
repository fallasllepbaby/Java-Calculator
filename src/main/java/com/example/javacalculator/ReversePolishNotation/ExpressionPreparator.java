package com.example.javacalculator.ReversePolishNotation;

public class ExpressionPreparator {
    public static String prepareExpression(String expression) {
        String preparedExpression = "";
        for (int token = 0; token < expression.length(); token++) {
            char symbol = expression.charAt(token);
            if (symbol == '-') {
                if (token == 0) {
                    preparedExpression += '0';
                } else if(expression.charAt(token - 1) == '(') {
                    preparedExpression += '0';
                }
            }
            preparedExpression += symbol;
        }
        return preparedExpression;
    }
}

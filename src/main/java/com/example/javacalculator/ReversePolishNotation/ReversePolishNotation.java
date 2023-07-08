package com.example.javacalculator.ReversePolishNotation;

import static com.example.javacalculator.ReversePolishNotation.ExpressionPreparator.prepareExpression;
import static PostfixExpressionConverter.convertToPostfixExpression;
import static PostfixExpressionCounter.count;

// Reverse Polish Notation
public class ReversePolishNotation {
    public static double calculate(String expression) {
        String preparedExpression = prepareExpression(expression);
        String postfixExpression = convertToPostfixExpression(preparedExpression);
        return count(postfixExpression);
    }
}
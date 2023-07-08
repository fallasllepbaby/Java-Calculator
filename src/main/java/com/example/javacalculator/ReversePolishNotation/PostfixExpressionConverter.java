package com.example.javacalculator.ReversePolishNotation;

import java.util.Stack;
public class PostfixExpressionConverter implements IsDelimeter, IsOperator, GetPriority {
    public static String convertToPostfixExpression(String expression) {
        String output = "";
        char[] expressionArray = expression.toCharArray();
        Stack<Character> operStack = new Stack<Character>();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expressionArray[i])) {
                while (!IsDelimeter.IsDelimeter(expressionArray[i]) && !IsOperator.isOperator(expressionArray[i])) {
                    output += expressionArray[i];
                    i++;

                    if (i == expressionArray.length) break;
                }
                output += " ";
                i--;
            }
            if (IsOperator.isOperator(expressionArray[i])) {
                if (expressionArray[i] == '(') {
                    operStack.push(expressionArray[i]);
                } else if (expressionArray[i] == ')') {
                    char s = operStack.pop();
                    while (s != '(') {
                        output += s + " ";
                        s = operStack.pop();
                    }
                } else {
                    if (operStack.size() > 0) {
                        if (GetPriority.getPriority(expressionArray[i]) <= GetPriority.getPriority(operStack.peek())) {
                            output += operStack.pop().toString() + " ";
                        }
                    }
                    operStack.push(expressionArray[i]);
                }
            }
        }
        while (operStack.size() > 0) {
            output += operStack.pop() + " ";
        }
        return output;
    }
}

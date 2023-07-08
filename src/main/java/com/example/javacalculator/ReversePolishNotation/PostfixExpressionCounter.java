package com.example.javacalculator.ReversePolishNotation;

import java.util.Stack;
public class PostfixExpressionCounter implements IsDelimeter, IsOperator {
    public static double count(String expression) {
        double result = 0;
        Stack<Double> temp = new Stack<Double>();
        char[] expressionArray = expression.toCharArray();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expressionArray[i])) {
                String a = "";
                while (!IsDelimeter.IsDelimeter(expressionArray[i]) && !IsOperator.isOperator(expressionArray[i])) {
                    a += expressionArray[i];
                    i++;
                    if (i == expressionArray.length) break;
                }
                a = a.replace(",", ".");
                temp.push(Double.parseDouble(a));
                i--;
            } else if (IsOperator.isOperator(expressionArray[i])) {
                double a = temp.pop();
                double b = temp.pop();
                switch (expressionArray[i]) {
                    case '+' : result = b + a; break;
                    case '-' : result = b - a; break;
                    case '*' : result = b * a; break;
                    case '/' : result = b / a; break;
                }
                temp.push(result);
            }
        }
        return temp.peek();
    }
}

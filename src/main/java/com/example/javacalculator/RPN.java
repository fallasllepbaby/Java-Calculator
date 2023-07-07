package com.example.javacalculator;

import java.util.Stack;

// Reverse Polish Notation
public class RPN {
    public static double calculate(String expression) {
        String preparedExpression = prepareExpression(expression);
        String output = getExpression(preparedExpression);
        return count(output);
    }

    private static String prepareExpression(String expression) {
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

    private static String getExpression(String expression) {
        String output = "";
        char[] expressionArray = expression.toCharArray();
        Stack<Character> operStack = new Stack<Character>();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expressionArray[i])) {
                while (!IsDelimeter(expressionArray[i]) && !isOperator(expressionArray[i])) {
                    output += expressionArray[i];
                    i++;

                    if (i == expressionArray.length) break;
                }

                output += " ";
                i--;
            }
            if (isOperator(expressionArray[i])) {
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
                        if (getPriority(expressionArray[i]) <= getPriority(operStack.peek())) {
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

    private static double count(String expression) {
        double result = 0;
        Stack<Double> temp = new Stack<Double>();
        char[] expressionArray = expression.toCharArray();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expressionArray[i])) {
                String a = "";
                while (!IsDelimeter(expressionArray[i]) && !isOperator(expressionArray[i])) {
                    a += expressionArray[i];
                    i++;
                    if (i == expressionArray.length) break;
                }
                temp.push(Double.parseDouble(a));
                i--;
            } else if (isOperator(expressionArray[i])) {
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

    private static boolean isOperator(char mathOperator) {
        if (("+-/*^()".indexOf(mathOperator) != -1)) {
            return true;
        }
        return false;
    }

    private static byte getPriority(char mathOperator) {
        switch (mathOperator) {
            case '('   : return 0;
            case ')'   : return 1;
            case '+'   : return 2;
            case '-'   : return 3;
            case '*'   : return 4;
            case '/'   : return 5;
            default    : return 6;

        }
    }

    private static boolean IsDelimeter(char character)
    {
        if ((" =".indexOf(character) != -1))
            return true;
        return false;
    }
}
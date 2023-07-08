package com.example.javacalculator.ReversePolishNotation;

public interface GetPriority {
    static byte getPriority(char mathOperator) {
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
}

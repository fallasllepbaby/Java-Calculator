package com.example.javacalculator.ReversePolishNotation;

public interface IsDelimeter {
    static boolean IsDelimeter(char character)
    {
        if ((" =".indexOf(character) != -1))
            return true;
        return false;
    }
}

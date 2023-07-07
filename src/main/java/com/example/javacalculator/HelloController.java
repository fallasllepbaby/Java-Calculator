package com.example.javacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {
    private StringBuilder outPutExpression;

    public TextField expression;

    private String mathOperator;

    private boolean hasCalculated = false;

    private boolean hasRightBracket = false;

    private boolean hasLeftBracket = false;


    public void initialize() {
        expression.setText("0");
        expression.setFocusTraversable(false);
    }

    public void insertNumber(ActionEvent event) {
        if (expression.getText().equals("0") || hasCalculated) {
            Button btn = (Button) event.getSource();
            outPutExpression = new StringBuilder("");
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
            hasCalculated = false;
        } else {
            Button btn = (Button) event.getSource();
            outPutExpression = new StringBuilder(expression.getText());
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
        }
    }

    public void insertMathOperator(ActionEvent event) {
        if (!(expression.getText().equals("0")) && expression.getLength() > 0 && !(outPutExpression.substring(outPutExpression.length() - 1).equals("+")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("-")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("*")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("/"))) {
            Button btn = (Button) event.getSource();
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
            mathOperator = btn.getText();
            hasCalculated = false;
        }
    }

    public void clear(ActionEvent event) {
        if (!(expression.getText().equals("0")) && expression.getLength() > 0) {
            outPutExpression.delete(0, outPutExpression.length());
            expression.setText("0");
        }
    }

    public void backspace(ActionEvent event) {
        if (expression.getLength() == 1) {
            outPutExpression.delete(expression.getLength() - 1, expression.getLength());
            expression.setText("0");
            return;
        }
        if (expression.getLength() > 0) {
            outPutExpression.delete(expression.getLength() - 1, expression.getLength());
            expression.setText(outPutExpression.toString());
        }
    }

    @FXML
    private void calculate() {
        if ( !(expression.getText(expression.getLength() - 1, expression.getLength()).equals(mathOperator)) && (expression.getText().contains("+") || expression.getText().contains("*") || expression.getText().contains("/") || expression.getText().contains("-"))) {
            outPutExpression = new StringBuilder("" + RPN.calculate(expression.getText()));
            expression.setText(outPutExpression.toString());
        }
    }

    public void insertComma(ActionEvent event) {
        if (!(expression.getText().contains("."))) {
            Button btn = (Button) event.getSource();
            outPutExpression = new StringBuilder(expression.getText());
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
        }
    }

    public void insertLeftBracket(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (expression.getText().length() == 1) {
            outPutExpression = new StringBuilder("");
        } else {
            outPutExpression = new StringBuilder(expression.getText());
        }
        outPutExpression.append(btn.getText());
        expression.setText(outPutExpression.toString());

        hasLeftBracket = true;
    }

    public void insertRightBracket(ActionEvent event) {
        if (hasLeftBracket) {
            Button btn = (Button) event.getSource();
            outPutExpression = new StringBuilder(expression.getText());
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
            hasRightBracket = true;
            hasLeftBracket = false;
        }
    }

    public void changeSign(ActionEvent event) {
        if (isNumeric(expression.getText())) {
            outPutExpression = new StringBuilder(expression.getText());
            if (Double.parseDouble(expression.getText()) < 0) {
                outPutExpression.delete(0,1);
            } else if (Double.parseDouble(expression.getText()) > 0) {
                outPutExpression.insert(0, "-");
            }
            expression.setText(outPutExpression.toString());
        }
    }

    public static boolean isNumeric(String strNum) {
        String num = "0123456789,.";
        ArrayList<Character> numbers = new ArrayList<>();
        for (int i = 0; i < num.length(); i++) {
            numbers.add(num.charAt(i));
        }

        for (int i = 0; i < strNum.length(); i++) {
            if (!numbers.contains(strNum.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void findSquareRoot(ActionEvent event) {
        if (isNumeric(expression.getText())) {
            String ex = expression.getText();
            ex = ex.replace(",", ".");
            double result = Math.sqrt(Double.parseDouble(ex));
            String resultString = String.format("%.2f", result);
            expression.setText(resultString);
        }
    }
}
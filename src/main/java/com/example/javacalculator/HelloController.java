package com.example.javacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    private StringBuilder outPutExpression;

    public TextField expression;

    private String mathOperator;

    private boolean hasCalculated = false;


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
            if (mathOperator.equals("+") || mathOperator.equals("*")) {
                mathOperator = "\\" + mathOperator;
            }
            String[] tokens = expression.getText().split(mathOperator);

            double expressionResult = 0;
            switch (mathOperator) {
                case "-" :
                    expressionResult = Double.parseDouble(tokens[0]) - Double.parseDouble(tokens[1]);
                    break;
                case "/" :
                    expressionResult = Double.parseDouble(tokens[0]) / Double.parseDouble(tokens[1]);
                    break;
                case "\\*" :
                    expressionResult = Double.parseDouble(tokens[0]) * Double.parseDouble(tokens[1]);
                    break;
                case "\\+" :
                    expressionResult = Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[1]);
                    break;
            }

            outPutExpression = new StringBuilder(String.valueOf(expressionResult));
            expression.setText(outPutExpression.toString());
            hasCalculated = true;
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
}
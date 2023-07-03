package com.example.javacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    private StringBuilder outPutExpression;

    public TextField expression = new TextField("0");

    private String mathOperator;

    public void insertNumber(ActionEvent event) {
        Button btn = (Button) event.getSource();
        outPutExpression = new StringBuilder(expression.getText());
        outPutExpression.append(btn.getText());
        expression.setText(outPutExpression.toString());
    }

    public void insertMathOperator(ActionEvent event) {
        if (expression.getLength() > 0 && !(outPutExpression.substring(outPutExpression.length() - 1).equals("+")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("-")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("*")) && !(outPutExpression.substring(outPutExpression.length() - 1).equals("/"))) {
            Button btn = (Button) event.getSource();
            outPutExpression.append(btn.getText());
            expression.setText(outPutExpression.toString());
            mathOperator = btn.getText();
        }
    }

    public void clear(ActionEvent event) {
        if (expression.getLength() > 0) {
            outPutExpression.delete(0, outPutExpression.length());
            expression.setText(outPutExpression.toString());
        }
    }

    public void backspace(ActionEvent event) {
        if (expression.getLength() > 0) {
            outPutExpression.delete(expression.getLength() - 1, expression.getLength());
            expression.setText(outPutExpression.toString());
        }
    }

    @FXML
    private void calculate() {
        if (mathOperator.equals("+") || mathOperator.equals("*")) {
            mathOperator = "\\\\" + mathOperator;
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
    }
}
module com.example.javacalculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.javacalculator to javafx.fxml;
    exports com.example.javacalculator;
    exports com.example.javacalculator.ReversePolishNotation;
    opens com.example.javacalculator.ReversePolishNotation to javafx.fxml;
}
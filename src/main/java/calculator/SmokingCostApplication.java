
package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmokingCostApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx.fxml"));
        stage.setTitle("JavaFX Smoking Cost Calculator - dr. Csonka Norbert");
        stage.setScene(new Scene(root));
        stage.show();

    }
}


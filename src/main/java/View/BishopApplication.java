package View;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class BishopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        stage.setTitle("2.4 Bishop Problem");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

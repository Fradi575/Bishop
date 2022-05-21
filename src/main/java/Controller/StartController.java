package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

public class StartController {
    @FXML
    Button startButton;
    @FXML
    TextField nevMezo;

    @FXML
    private void initialize(){
        Logger.debug("inicializálás");
    }

    public void startButtonPressed(ActionEvent actionEvent) throws IOException {
        if (!nevMezo.getText().isEmpty()){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bishop.fxml"));
            fxmlLoader.setLocation(getClass().getResource("/bishop.fxml"));
            Parent root = fxmlLoader.load();
            BishopController bishopController = fxmlLoader.<BishopController>getController();
            bishopController.setNev(nevMezo.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("{} has started a game.",nevMezo.getText());
        }else{
            Logger.warn("No name provided.");
        }
    }

    public void highScoreButtonPressed(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/highscore.fxml"));
        fxmlLoader.setLocation(getClass().getResource("/highscore.fxml"));
        Parent root = fxmlLoader.load();
        HighScoreController highScoreController = fxmlLoader.<HighScoreController>getController();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

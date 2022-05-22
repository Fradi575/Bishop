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

    /**
     * Inicializálás
     */
    @FXML
    private void initialize(){
        Logger.debug("Indítás...");
    }

    /**
     * Start gomb megnyomva.
     * Betölti a bishop.fxml-t, beállítja a játékos nevét. Megkezdi a játékot.
     * @param actionEvent
     * @throws IOException
     */
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
            Logger.info("{} elindított egy játékot.",nevMezo.getText());
        }else{
            Logger.warn("Nincs név megadva.");
        }
    }

    /**
     * Eredmények gomb megnyomása. Betölti a highscore.fxml-t.
     * @param actionEvent
     * @throws IOException
     */
    public void highScoreButtonPressed(ActionEvent actionEvent) throws IOException{
        Logger.info("Eredmények megtekintése.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/highscore.fxml"));
        fxmlLoader.setLocation(getClass().getResource("/highscore.fxml"));
        Parent root = fxmlLoader.load();
        HighScoreController highScoreController = fxmlLoader.<HighScoreController>getController();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

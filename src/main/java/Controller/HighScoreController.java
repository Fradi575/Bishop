package Controller;

import Dao.HighScore;
import Dao.HighScoreDao;
import Dao.Score;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.tinylog.Logger;

public class HighScoreController {
    @FXML
    private Label highscoreLabel;

    private StringProperty eredmenyek = new SimpleStringProperty("");

    /**
     * inicializálás.
     * Beolvassa a highscore.xml tartalmát, és hozzácsatolja a labelhez.
     */
    @FXML
    public void initialize(){
        Logger.info("Eredmények betöltése...");
        HighScoreDao highScoreDao = new HighScoreDao();
        HighScore hs = new HighScore();
        hs = highScoreDao.getHighScores();
        String highscoretext = "";
        for(Score sc : hs.getHighscore()){
            String text = sc.getName() + " : " + sc.getScore() + "\n";
            highscoretext += text + "\n";
        }
        highscoreLabel.setText(highscoretext);
        highscoreLabel.setVisible(true);
    }

}

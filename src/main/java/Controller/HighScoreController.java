package Controller;

import Dao.HighScore;
import Dao.HighScoreDao;
import Dao.Score;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HighScoreController {
    @FXML
    private TextField scoreMezo;

    private StringProperty eredmenyek = new SimpleStringProperty();

    @FXML
    public void initialize(){
        scorezacsatolas();
        HighScoreDao highScoreDao = new HighScoreDao();
        HighScore hs = new HighScore();
        hs = highScoreDao.getHighScores();
        String highscoretext = "";
        for(Score sc : hs.getHighscore()){
            String text = sc.getName() + " : " + sc.getScore() + "\n";
            highscoretext += text;
        }
        eredmenyek.set(eredmenyek.getValue()+highscoretext);
    }
    private void scorezacsatolas() {
        scoreMezo.textProperty().bind(eredmenyek);
    }

}

package Dao;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;

/**
 *HighScore osztály amely egy lista ami {@link Score} -ket tartalmaz.
 */

@XmlRootElement(name = "highscores")
public class HighScore {
    private ArrayList<Score> highscore;


    /**
     * {@code HighScore} üres példány létrehozása.
     */
    public HighScore() {
        this.highscore = new ArrayList<Score>();
    }

    /**
     * Sorted the scores and get the first ten high scores.
     * @return highscore score.
     */
    public ArrayList<Score> getHighscore() {
        Collections.sort(highscore, (hs1, hs2) -> {
            return Integer.parseInt(hs2.getScore()) - Integer.parseInt(hs1.getScore());
        });
        if (highscore.size() > 10) {
            for (int i = 0; i < highscore.size(); i++) {
                highscore.subList(5, highscore.size()).clear();
            }
        }
        return highscore;
    }

    /**
     * Setter a scores listához
     * @param scores {@link ArrayList} lista ami {@link Score} elemeket tárol.
     */
    @XmlElement
    public void setHighscore(ArrayList<Score> scores){
        this.highscore = scores;
    }

    /**
     * Új {@link Score} hozzáadása
     * @param score eredmény.
     */

    public void addScore(Score score){
        highscore.add(score);
    }
}
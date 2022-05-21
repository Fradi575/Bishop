package Dao;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The {@code Score} class implements the score.
 */
@XmlRootElement
public class Score {
    private SimpleStringProperty score;
    private SimpleStringProperty name;

    /**
     * Creates an empty instance of Score.
     */
    public Score() {
        this.score = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    /**
     * Creates a new instance of Score with a specified score and date.
     *
     * @param score The achieved score.
     * @param name The actual date.
     */
    public Score(String name, String score) {
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.score.set(score);
        this.name.set(name);
    }

    /**
     * Get the name.
     *
     * @return Name.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Get the score.
     *
     * @return Score.
     */
    public String getScore() {
        return score.get();
    }


    /**
     * Set the name.
     * @param name Name.
     */
    @XmlElement
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Set the score.
     *
     * @param score Score.
     */
    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "name=" + name +
                ", score=" + score +
                '}';
    }
}

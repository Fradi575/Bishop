package Dao;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@code Score} eredmények eltárolására szolgál
 */
@XmlRootElement
public class Score {
    private SimpleStringProperty score;
    private SimpleStringProperty name;

    /**
     * Üres példány létrehozása
     */
    public Score() {
        this.score = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    /**
     * Eredmény létrehozása megadott névvel és eredménnyel
     *
     * @param score Elért eredmény
     * @param name Megadott név.
     */
    public Score(String name, String score) {
        this.name = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.score.set(score);
        this.name.set(name);
    }

    /**
     * Név lekérdezése
     *
     * @return Név
     */
    public String getName() {
        return name.get();
    }

    /**
     * Eredmény lekérdezése
     *
     * @return Eredmény.
     */
    public String getScore() {
        return score.get();
    }


    /**
     * Név beállítása
     * @param name Név.
     */
    @XmlElement
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Eredmény beállítása
     *
     * @param score eredmény
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

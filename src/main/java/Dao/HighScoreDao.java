package Dao;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * The HighScoreDAO implementálja az XML olvasását és írását
 */
public class HighScoreDao {
    /**
     * XML fájlneve
     */
    public String path ="highscores.xml";
    /**
     * Eredmény hozzáadása az XML-hez
     *
     * @param score eredmény.
     */
    public void addScore(Score score){
        HighScore highScore = getHighScores();
        highScore.addScore(score);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(HighScore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File file = new File(path);
            OutputStream outputStream = new FileOutputStream(path);
            marshaller.marshal(highScore,file);
            marshaller.marshal(highScore,System.out);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * XML beolvasása
     *
     * @return Highscore lista.
     */
    public HighScore getHighScores(){
        HighScore highScore = new HighScore();
        try {
            JAXBContext jaxbContext =JAXBContext.newInstance(HighScore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream inputStream = new FileInputStream(path);
            highScore = (HighScore) unmarshaller.unmarshal(inputStream);
            inputStream.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}
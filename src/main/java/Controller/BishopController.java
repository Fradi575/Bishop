package Controller;

import Dao.HighScoreDao;
import Dao.Score;
import Models.Tabla;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class BishopController {
    @FXML
    private GridPane grid;

    @FXML
    private TextField klikkekMezo;

    private Tabla tabla = new Tabla();

    private StackPane[][] teglalapok = new StackPane[5][4];

    private StringProperty nev = new SimpleStringProperty();
    private IntegerProperty klikkek = new SimpleIntegerProperty(0);
    private boolean celallapot = false;
    private ArrayList<Integer> selections = new ArrayList<Integer>();

    /**
     * A játékos neve beállítása
     * @param name játékos neve
     */
    public void setNev(String name) {
        this.nev.set(name);
    }

    /**
     * Inicializálás, a játékot kezdeti állapotba helyezi
     */
    @FXML
    public void initialize(){
        jatekbeallitas();
        klikkhozzacsatolas();
        Logger.info("Játék indítása...");
    }

    /**
     * Bind-olja a javafx-es mezőhöz a klikkek számlálót
     */
    private void klikkhozzacsatolas() {
        klikkekMezo.textProperty().bind(klikkek.asString());
    }


    /**
     * Az egérkezelés.
     * Menti egy listába a klikkelések pozicióját.
     * Ha 2 klikkelés volt, akkor meghívja a mozgatást.
     * Ellenőrzi mozgatás után, hogy célállapot-e.
     * Ha célállapot, akkor menti az eredményeket
     * Mozgatás után kiűríti a választott poziciók listáját
     * @param event klikkelés
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var source = (Node) event.getSource();
        var row = GridPane.getRowIndex(source);
        var col = GridPane.getColumnIndex(source);
        Logger.debug("Clicked square row: {}, col: {}",row,col);
        System.out.println("Clicked square :" + row + " " + col);
        selections.add(row);
        selections.add(col);
        if(selections.size()==4){
            tabla.mozgat(selections.get(0), selections.get(1), selections.get(2), selections.get(3));
            teglalapFrissites();
            klikkek.set(klikkek.get()+1);
            if(tabla.megoldva()){
                Logger.info("Megoldva, gratulálok!");
                Logger.info("Eredmények mentése...");
                Score newScore = new Score(nev.getValue(), String.valueOf(klikkek));
                HighScoreDao highScoreDao = new HighScoreDao();
                highScoreDao.addScore(newScore);
            }
            selections.clear();
        }
    }

    /**
     * Kezdeti állapotba rakja a játékot
     */
    public void jatekbeallitas(){
        tabla = new Tabla();
        klikkek.set(0);
        GridTabla();
        teglalapFrissites();

    }

    /**
     * Grid feltöltése téglalapokkal
     */
    private void GridTabla(){
        int db = 0;
        for(var row=0; row<5; row++){
            for(var col=0; col< 4; col++){
                teglalapok[row][col] = teglalapLetrehozas();
                grid.add(teglalapok[row][col], col,row);
            }
        }
    }

    /**
     * Téglalapok létrehozása, és a klikkelés figyelésének hozzáadása.
     * @return StackPane
     */
    private StackPane teglalapLetrehozas() {
        var teglalap = new StackPane();
        teglalap.getStyleClass().removeAll();
        teglalap.getStyleClass().add("square");
        teglalap.setOnMouseClicked(this::handleMouseClick);
        return teglalap;
    }

    /**
     * Frissíti a téglalapok stílusát aszerint, hogy milyen színű bábú van rajta.
     */
    private void teglalapFrissites(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <4; j++) {
                int futo = tabla.getMezok()[i][j].getBabu();
                String szin = "ures";
                if(futo==0){
                    szin="ures";
                }
                if(futo==1){
                    szin="black";
                }
                if(futo==2){
                    szin="white";
                }
                teglalapok[i][j].getStyleClass().clear();
                teglalapok[i][j].getStyleClass().add("square");
                teglalapok[i][j].getStyleClass().add(szin);
            }
        }
    }


    /**
     * Felad gomb megnyomásának végrehajtása.
     * Célállapot megjelenítése, eredmények tárolása.
     * @param actionEvent klikkelés
     * @throws IOException
     */
    public void feladButtonPressed(ActionEvent actionEvent) throws IOException {
        Logger.info("Játék feladva, cél beállítása, mentés az eredményekbe!");
        for(int i=0;i<5;i++){
            for(int j=0; j<4; j++){
                tabla.getMezok()[i][j].setBabu(0);
            }
        }
        for(int k=0; k<4; k++){
            tabla.getMezok()[0][k].setBabu(2);
            tabla.getMezok()[4][k].setBabu(1);
        }
        teglalapFrissites();
        Score newScore = new Score(nev.getValue(), String.valueOf(klikkek.getValue()));
        HighScoreDao highScoreDao = new HighScoreDao();
        highScoreDao.addScore(newScore);
    }
}

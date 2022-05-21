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


    public void setNev(String name) {
        this.nev.set(name);
    }

    @FXML
    public void initialize(){
        jatekbeallitas();
        klikkhozzacsatolas();
        Logger.info("Játék indítása...");
    }
    private void klikkhozzacsatolas() {
        klikkekMezo.textProperty().bind(klikkek.asString());
    }
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
            System.out.println("mozgatás volt");
            teglalapFrissites();
            klikkek.set(klikkek.get()+1);
            if(megoldva()){
                System.out.println("megoldottad, ügyes vagy");
                Score newScore = new Score(nev.getValue(), String.valueOf(klikkek));
                HighScoreDao highScoreDao = new HighScoreDao();
                highScoreDao.addScore(newScore);
            }
            selections.clear();
        }
    }

    public void jatekbeallitas(){
        tabla = new Tabla();
        klikkek.set(0);
        GridTabla();
        teglalapFrissites();

    }

    private void GridTabla(){
        int db = 0;
        for(var row=0; row<5; row++){
            for(var col=0; col< 4; col++){
                teglalapok[row][col] = teglalapLetrehozas();
                grid.add(teglalapok[row][col], col,row);
            }
        }
    }

    private StackPane teglalapLetrehozas() {
        var teglalap = new StackPane();
        teglalap.getStyleClass().removeAll();
        teglalap.getStyleClass().add("square");
        teglalap.setOnMouseClicked(this::handleMouseClick);
        return teglalap;
    }

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

    private boolean megoldva(){
        for(int i=0; i<4; i++){
            if(tabla.getMezok()[0][i].getBabu()!=2)
                return false;
            if(tabla.getMezok()[4][i].getBabu()!=1)
                return false;
        }
        return true;
    }

    public void feladButtonPressed(ActionEvent actionEvent) throws IOException {
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
        Score newScore = new Score(nev.getValue(), String.valueOf(klikkek));
        HighScoreDao highScoreDao = new HighScoreDao();
        highScoreDao.addScore(newScore);
    }
}

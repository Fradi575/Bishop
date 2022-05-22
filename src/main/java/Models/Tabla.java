package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

/**
 * Tábla osztály, amely a mezőket tárolja, és azokhoz tartozó metódosukat.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tabla {
    private Mezo[][] mezok = new Mezo[5][4];

    /**
     * Konstruktor, amely a táblát alaphelyzetbe helyezi. Négy fekete futó az első sorban, négy fehér az utolsó sorban.
     */
    public Tabla(){
        for(int i=0; i<5; i++)
            for(int j=0; j<4; j++){
                mezok[i][j] = new Mezo(0,0);
            }
        for(int k=0; k<4; k++){
            mezok[0][k].setBabu(1);
            mezok[4][k].setBabu(2);
        }
        for(int i=0; i<5; i++)
            for(int j=0; j<4; j++){
                if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
                    mezok[i][j].setSzin(1);
                else
                    mezok[i][j].setSzin(0);
            }
    }

    /**
     * Bábú mozgatása a táblán
     * @param honnan_x A bábú kezdő poziciójának sora
     * @param honnan_y A bábú kezdő poziciójának oszlopa
     * @param hova_x A célmező poziciójának sora
     * @param hova_y A célmező poziciójának oszlopa
     */
    public void mozgat(int honnan_x, int honnan_y, int hova_x, int hova_y) {
        if (mezok[honnan_x][honnan_y].getBabu() == 0) {
            Logger.info("Nincs bábú amit mozgassak a mezőn!");
            return;
        }
        if (mezok[hova_x][hova_y].getBabu() != 0) {
            Logger.info("Nem üres a célmező!");
            return;
        }
        if(!((hova_x-honnan_x == hova_y-honnan_y)||(-hova_x+honnan_x == hova_y-honnan_y))){
            Logger.info("Nem futólépés!");
            return;
        }
        int jelenlegi_babu = mezok[honnan_x][honnan_y].getBabu();
        if(szabade(honnan_x,honnan_y,hova_x,hova_y)) {
            mezok[honnan_x][honnan_y].setBabu(0);
            mezok[hova_x][hova_y].setBabu(jelenlegi_babu);
            Logger.info("Sikeres lépés!");
        }
        else{
            Logger.info("A mezőre nem tudsz lépni, mert ütés alatt áll!");
        }

    }

    /**
     * A kijelölt mezőn lévő bábú számára szabad-e a célmező
     * @param honnan_x A bábú kezdő poziciójának sora
     * @param honnan_y A bábú kezdő poziciójának oszlopa
     * @param hova_x A célmező poziciójának sora
     * @param hova_y A célmező poziciójának oszlopa
     * @return szabad-e a mező, vagy üti ellenséges fútó
     */

    public boolean szabade(int honnan_x, int honnan_y, int hova_x, int hova_y){
        int jelenlegibabu = mezok[honnan_x][honnan_y].getBabu();
        int ellenseges;
        if(jelenlegibabu==1){
            ellenseges=2;
        }
        else{
            ellenseges=1;
        }
        for(int i=0; i<5; i++){
            for(int j=0; j<4; j++){
                if(i!=honnan_x && j!=honnan_y){
                    if(((hova_x-i == hova_y-j)||(-hova_x+i == hova_y-j)) && mezok[i][j].getBabu()==ellenseges){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Meg van-e oldva a tábla
     * @return célállapotban vannak-e a bábúk a táblák. Célállapot, ha négy fehér az első sorban, a feketék pedig az utolsókban vannak
     */
    public boolean megoldva(){
        for(int i=0; i<4; i++){
            if(mezok[0][i].getBabu()!=2)
                return false;
            if(mezok[4][i].getBabu()!=1)
                return false;
        }
        return true;
    }
}

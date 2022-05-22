import Models.Tabla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {
    @Test
    void TestMozgat(){
        Tabla tabla = new Tabla();
        tabla.mozgat(0,1,1,2);
        assertEquals(0, tabla.getMezok()[0][1].getBabu());
        assertEquals(1, tabla.getMezok()[1][2].getBabu());
    }

    @Test
    void TestSikertelenMozgat(){
        Tabla tabla = new Tabla();
        tabla.mozgat(0,2,1,3);
        assertNotEquals(0, tabla.getMezok()[0][2].getBabu());
        assertNotEquals(1, tabla.getMezok()[1][3].getBabu());
    }

    @Test
    void TestSzabade(){
        Tabla tabla = new Tabla();
        assertTrue(tabla.szabade(0,1,1,2));
    }

    @Test
    void TestNemSZabad(){
        Tabla tabla = new Tabla();
        assertFalse(tabla.szabade(0,2,1,3));
    }

    @Test
    void TestMegoldva(){
        Tabla tabla = new Tabla();
        for(int i=0;i<5;i++){
            for(int j=0; j<4; j++){
                tabla.getMezok()[i][j].setBabu(0);
            }
        }
        for(int k=0; k<4; k++){
            tabla.getMezok()[0][k].setBabu(2);
            tabla.getMezok()[4][k].setBabu(1);
        }
        assertTrue(tabla.megoldva());
    }

    @Test
    void TestNincsMegoldva(){
        Tabla tabla = new Tabla();
        assertFalse(tabla.megoldva());
    }
}

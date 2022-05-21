import Models.Tabla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TablaTest {
    @Test
    void TestMozgat(){
        Tabla tabla = new Tabla();
        tabla.mozgat(0,1,1,2);
    }
}

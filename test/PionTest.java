import Models.Pion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PionTest {

    @Test
    public void pionConstructionCorrect(){
        Pion pion = new Pion(Pion.Type.CROIX);
        assertEquals(Pion.Type.CROIX, pion.type);
    }

    @Test
    public void pionConstructionNotCorrect(){
        Pion pion = new Pion(Pion.Type.CROIX);
        assertNotEquals(Pion.Type.ROUND, pion.type);
    }

}

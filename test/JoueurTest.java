import Models.Joueur;
import Models.Pion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {

    @Test
    public void joueurConstructorCroixCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.CROIX);
        assertEquals(Pion.Type.CROIX, joueur.pionType);
        assertFalse(joueur.win);
    }

    @Test
    public void joueurConstructorCroixNotCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.CROIX);
        assertNotEquals(Pion.Type.ROUND, joueur.pionType);
        assertNotEquals(true,joueur.win);
    }

    @Test
    public void joueurConstructorRondCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.ROUND);
        assertEquals(Pion.Type.ROUND, joueur.pionType);
        assertFalse(joueur.win);
    }

    @Test
    public void joueurConstructorRondNotCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.ROUND);
        assertNotEquals(Pion.Type.CROIX, joueur.pionType);
        assertNotEquals(true,joueur.win);
    }


    @Test
    public void createPionCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.ROUND);
        assertEquals(Pion.Type.ROUND, joueur.createPion().type);
    }

    @Test
    public void createPionPasCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.ROUND);
        assertNotEquals(Pion.Type.CROIX, joueur.createPion().type);
    }

    public void createPionCroixCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.CROIX);
        assertEquals(Pion.Type.CROIX, joueur.createPion().type);
    }

    @Test
    public void createPionCroixPasCorrectTest(){
        Joueur joueur = new Joueur(Pion.Type.CROIX);
        assertNotEquals(Pion.Type.ROUND, joueur.createPion().type);
    }
}

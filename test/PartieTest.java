import Models.Joueur;
import Models.Partie;
import Models.Pion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PartieTest {

    private Partie partie;
    private Pion croix;
    private Pion rond;

    @BeforeEach
    public void init(){
         partie = new Partie();
         croix = new Pion(Pion.Type.CROIX);
         rond = new Pion(Pion.Type.ROUND);
    }

    //CONSTRUCTOR
    @Test
    public void constructorPartieCorrect(){
        assertFalse(partie.end);
        assertEquals(0,partie.indiceJoueur);
        assertEquals(Pion.Type.CROIX,partie.joueurs[0].pionType);
        assertEquals(Pion.Type.ROUND,partie.joueurs[1].pionType);
        assertEquals(partie.currentJoueur, partie.joueurs[0]);
    }

    @Test
    public void constructorPartieNotCorrect(){
        assertNotEquals(true,partie.end);
        assertNotEquals(1,partie.indiceJoueur);
        assertNotEquals(Pion.Type.CROIX,partie.joueurs[1].pionType);
        assertNotEquals(Pion.Type.ROUND,partie.joueurs[0].pionType);
        assertNotEquals(partie.currentJoueur, partie.joueurs[1]);
    }

    // JOUEUR SUIVANT
    @Test
    public void joueurSuivantJ2CorrectTest(){
        Joueur joueur2 = partie.joueurs[1];
        partie.joueurSuivant();
        assertEquals(joueur2,partie.currentJoueur);
    }

    @Test
    public void joueurSuivantJ1CorrectTest(){
        Joueur joueur1 = partie.joueurs[0];
        partie.joueurSuivant();
        partie.joueurSuivant();
        assertEquals(joueur1,partie.currentJoueur);
    }

    @Test
    public void joueurSuivantJ2NotCorrectTest(){
        Joueur joueur1 = partie.joueurs[0];
        partie.joueurSuivant();
        assertNotEquals(joueur1,partie.currentJoueur);
    }

    @Test
    public void joueurSuivantJ1NotCorrectTest(){
        Joueur joueur2 = partie.joueurs[1];
        partie.joueurSuivant();
        partie.joueurSuivant();
        assertNotEquals(joueur2,partie.currentJoueur);
    }

    // IS FIN PARTIE
    @ParameterizedTest(name = "{0} + {1} should equal to true")
    @CsvSource({ "true,false", "false,true", "true,true" })
    public void isFinPartieCorrectTest(boolean arg0, boolean arg1){
        assertTrue(partie.isFinPartie(arg0,arg1));
    }

    @Test
    public void isFinPartieNotCorrectTest(){
        assertFalse(partie.isFinPartie(false,false));
    }

    // IS CURRENT PLAYER WIN
    @ParameterizedTest(name = "{0} + {1} + {2} should equal to true")
    @CsvSource({    "false,false,true",
                    "false,true,true",
                    "false,true,false",
                    "true,false,false",
                    "true,false,true",
                    "true,true,false",
                    "true,true,true" })
    public void isCurrentPlayerWinCorrectTest(boolean arg0, boolean arg1,boolean arg2){
        assertTrue(partie.isCurrentPlayerWinTheGame(arg0,arg1,arg2));
        assertTrue(partie.currentJoueur.win);
    }

    @Test
    public void isCurrentPlayerNotWinYetCorrectTest(){
        assertFalse(partie.isCurrentPlayerWinTheGame(false,false,false));
        assertFalse(partie.currentJoueur.win);
    }


    // TEST PION LIGN ALIGN
    public void troisPionColonneAligneCorrectCAS1Test(){
        partie.plateau.grille = new Pion[][]{
                {croix,null,null},
                {croix,rond,null},
                {croix,null,rond}
        };
        assertTrue(partie.isTroisColoneAligne());
    }

    @Test
    public void troisPionColonneAligneCorrectCAS2Test(){

        partie.plateau.grille = new Pion[][]{
                {null,croix,rond},
                {rond,croix,null},
                {null,croix,null}
        };
        assertTrue(partie.isTroisColoneAligne());
    }

    @Test
    public void troisPionColonneAligneCorrectCAS3Test(){
        partie.plateau.grille = new Pion[][]{
                {rond,null,croix},
                {null,rond,croix},
                {null,null,croix}
        };
        assertTrue(partie.isTroisColoneAligne());
    }

    @Test
    public void troisPionColonneAligneNotCorrectTest(){
        partie.plateau.grille = new Pion[][]{
                {null,null,croix},
                {null,rond,null},
                {null,null,croix}
        };
        assertFalse(partie.isTroisColoneAligne());
    }

    // TEST PION LIGN ALIGN
    @Test
    public void troisPionLigneAligneCorrectCAS1Test(){
        Partie partie = new Partie();
        partie.plateau.grille = new Pion[][]{
                {croix,croix,croix},
                {null,null,rond},
                {rond,null,null}
        };
        assertTrue(partie.isTroisLigneAligne());
    }

    @Test
    public void troisPionLigneAligneCorrectCAS2Test(){
        Partie partie = new Partie();
        partie.plateau.grille = new Pion[][]{
                {rond,null,null},
                {croix,croix,croix},
                {null,rond,null}
        };
        assertTrue(partie.isTroisLigneAligne());
    }

    @Test
    public void troisPionLigneAligneCorrectCAS3Test(){
        Partie partie = new Partie();
        partie.plateau.grille = new Pion[][]{
                {rond,null,null},
                {null,rond,null},
                {croix,croix,croix}
        };
        assertTrue(partie.isTroisLigneAligne());
    }

    @Test
    public void troisPionLigneAligneNotCorrectTest(){
        Partie partie = new Partie();
        partie.plateau.grille = new Pion[][]{
                {rond,null,null},
                {null,null,null},
                {croix,null,croix}
        };
        assertFalse(partie.isTroisLigneAligne());
    }

    // TEST PION DIAG ALIGN
    @Test
    public void troisPionDiagonalAligneCorrectCAS1Test(){
        partie.plateau.grille = new Pion[][]{
                {rond,null,croix,},
                {rond,croix,null},
                {croix,null,null}
        };
        assertTrue(partie.isTroisDiagonalAligne());
    }

    @Test
    public void troisPionDiagonalAligneCorrectCAS2Test(){
        partie.plateau.grille = new Pion[][]{
                {croix,null,null},
                {rond,croix,null},
                {rond,null,croix}
        };
        assertTrue(partie.isTroisDiagonalAligne());
    }

    @Test
    public void troisPionDiagonalAligneNotCorrectTest(){
        partie.plateau.grille = new Pion[][]{
                {croix,null,null},
                {null,rond,null},
                {null,null,croix}
        };
        assertFalse(partie.isTroisDiagonalAligne());
    }
}

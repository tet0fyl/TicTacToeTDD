import Models.Pion;
import Models.Plateau;
import MyException.PionOutOfPlateau;
import MyException.PionOverlapAnOtherPion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {
        @Test
        public void verifierMouvementPossibleCorrectTest() throws PionOutOfPlateau, PionOverlapAnOtherPion {
            Plateau plateau = new Plateau();
            int x = 1, y = 1;
            assertTrue(plateau.verifierSiMouvementPossible(x,y));
        }

        @Test
        public void verifierMouvementPossiblePionOverlapAnOtherPionThrowCorrectTest() throws PionOutOfPlateau, PionOverlapAnOtherPion {
            Plateau plateau = new Plateau();
            int x=1,y=1;
            Pion pionOverlap = new Pion(Pion.Type.ROUND);
            plateau.placerPion(x,y,pionOverlap);
            assertThrows(PionOverlapAnOtherPion.class, () -> plateau.verifierSiMouvementPossible(x,y));
        }

        @Test
        public void verifierMouvementPossiblePionOverlapAnOtherPionDoesNotThrowCorrectTest() throws PionOutOfPlateau, PionOverlapAnOtherPion {
            Plateau plateau = new Plateau();
            int x=1,y=1;
            assertDoesNotThrow(() -> plateau.verifierSiMouvementPossible(x,y));
        }

        @Test
        public void verifierMouvementPossiblePionOutOfPlateauThrowCorrectTest() throws PionOutOfPlateau, PionOverlapAnOtherPion{
            Plateau plateau = new Plateau();
            int x = 4, y = 8;
            assertThrows(PionOutOfPlateau.class, () -> plateau.verifierSiMouvementPossible(x, y));
        }

        @Test
        public void verifierMouvementPossiblePionOutOfPlateauDoesNotThrowCorrectTest() throws PionOutOfPlateau, PionOverlapAnOtherPion{
            Plateau plateau = new Plateau();
            int x = 2, y = 2;
            assertDoesNotThrow(() -> plateau.verifierSiMouvementPossible(x, y));
    }

        @Test
        public void isPlateauPleinCorrectTest(){
            Plateau plateau = new Plateau();
            plateau.totalPions = 9;
            assertTrue(plateau.isPlateauPlein());
        }

        @Test
        public void isPlateauPleinPasCorrectTest(){
            Plateau plateau = new Plateau();
            plateau.totalPions = 6;
            assertFalse(plateau.isPlateauPlein());
        }

        @Test
        public void placerPionCorrectTest(){
            Plateau plateau = new Plateau();
            int x = 1, y = 2;
            Pion pion = new Pion(Pion.Type.CROIX);
            plateau.placerPion(x,y,pion);
            assertEquals(pion,plateau.grille[x][y]);
        }

        @Test
        public void incrementationDuTotalDePionsCorrectTest() {
            Plateau plateau = new Plateau();
            int expected = 3;
            for (int i = 0; i < expected; i++) {
                plateau.incrementationDuTotalDePions();
            }
            assertEquals(expected,plateau.totalPions);
        }

        @Test
        public void incrementationDuTotalDePionsPasCorrectTest() {
            Plateau plateau = new Plateau();
            int expected = -1;
            int turn = 5;
            for (int i = 0; i < turn; i++) {
                plateau.incrementationDuTotalDePions();
            }
            assertNotEquals(expected,plateau.totalPions);
    }

}

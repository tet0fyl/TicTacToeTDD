package Models;

import MyException.PionOutOfPlateau;
import MyException.PionOverlapAnOtherPion;

public class Plateau {
    public Pion[][] grille;
    public int nombreDeCase;
    public int totalPions;

    public Plateau(){
        nombreDeCase = 3;
        grille = new Pion[nombreDeCase][nombreDeCase];
        totalPions = 0;
    }

    /**
     * Test si le plateau est plein
     * @return true si plateau plein
     */
    public boolean isPlateauPlein() {
        return totalPions >= nombreDeCase * nombreDeCase;
    }

    public int incrementationDuTotalDePions(){
        return totalPions++;
    }

    /**
     * Vérifier si l'on peut poser un pion sur la grille du plateau
     * @param x la position x du pion a posé
     * @param y la position y du pion a posé
     * @throws PionOutOfPlateau Si le pion veut être poser en dehors du plateau
     * @throws PionOverlapAnOtherPion Si le pion veut être poser sur un autre pion
     */
    public boolean verifierSiMouvementPossible(int x, int y) throws PionOutOfPlateau, PionOverlapAnOtherPion {
        if(     x > nombreDeCase
                || x < 0
                || y > nombreDeCase
                || y < 0 )
            throw new PionOutOfPlateau("Le pion est en dehors du plateau");

        if(grille[x][y] != null)
            throw new PionOverlapAnOtherPion("Il a déjà un pion en x : " + x + " et y : " + y);

        return true;
    }

    public void placerPion(int x, int y, Pion pion) {
        grille[x][y] = pion;
    }

}

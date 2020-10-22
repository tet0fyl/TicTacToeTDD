package Models;

public class Partie {
        public Joueur[] joueurs;
        public Joueur currentJoueur;
        public Plateau plateau;
        public int indiceJoueur;
        public boolean end;

        public Partie(){
            end= false;
            indiceJoueur = 0;
            joueurs = new Joueur[2];
            joueurs[0] = new Joueur(Pion.Type.CROIX);
            joueurs[1] = new Joueur(Pion.Type.ROUND);
            currentJoueur = joueurs[indiceJoueur];
            plateau = new Plateau();
        }

    /**
     * Passe au joueur suivant
     */
    public void joueurSuivant() {
            indiceJoueur++;
            if(indiceJoueur >= joueurs.length) {
                indiceJoueur = 0;
            }
            currentJoueur = joueurs[indiceJoueur];
        }

    /**
     * Test si c'est la fin de la partie
     * @param currentJoueurWin boolean win du joueur courant
     * @param isPlateauPlein cf: -> plateau.isPlateauPlein()
     * @return true si partie terminé
     */
    public boolean isFinPartie(boolean currentJoueurWin, boolean isPlateauPlein){
            return currentJoueurWin || isPlateauPlein;
    }

    /**
     * Verifie si le joueur actuel gagne (et met a jour l'attribut win du joueur actuelle)
     * @param troisLigne cf -> methode isTroisLigneAligne()
     * @param troisColonne cf -> methode isTroisCoolonneAligne()
     * @param deuxDiag cf -> methode isTroisDiagonaleAligne()
     * @return true if a player gagne
     */
    public boolean isCurrentPlayerWinTheGame(boolean troisLigne, boolean troisColonne, boolean deuxDiag){
        return currentJoueur.win = (troisLigne || troisColonne || deuxDiag);
    }

    /**
     * Verifie si le joueur actuelle a 3 pions alignée en ligne
     * @return true if 3 pions en ligne
     */
    public boolean isTroisLigneAligne(){
            Pion.Type currentPionType = currentJoueur.pionType;
            for (int i = 0; i < plateau.nombreDeCase; i++) {
                int count = 0;
                for (int j = 0; j < plateau.nombreDeCase; j++) {
                    if(plateau.grille[i][j] == null) break;
                    if(plateau.grille[i][j].type.equals(currentPionType)) count++;
                    if(!plateau.grille[i][j].type.equals(currentPionType)) break;
                }
                if(count==3)  return true;
            }
            return false;
        };


    /**
     * Verifie si le joueur actuelle a 3 pions alignée en colonne
     * @return true if 3 pions en colonne
     */
    public boolean isTroisColoneAligne(){
            Pion.Type currentPionType = currentJoueur.pionType;
            for (int i = 0; i < plateau.nombreDeCase; i++) {
                int count = 0;
                for (int j = 0; j < plateau.nombreDeCase; j++) {
                    if(plateau.grille[j][i] == null) break;
                    if(plateau.grille[j][i].type.equals(currentPionType)) count++;
                    if(!plateau.grille[j][i].type.equals(currentPionType)) break;
                }
                if(count==3) return true;
            }
            return false;
        }

    /**
     * Verifie si le joueur actuelle a 3 pions alignée en diagonale
     * @return true if 3 pions en diagonale
     */
    public boolean isTroisDiagonalAligne(){
            Pion.Type currentPionType = currentJoueur.pionType;
            for (int i = 0; i < plateau.nombreDeCase; i+=2) {
                int count = 0;
                for (int j = 0; j < plateau.nombreDeCase; j++) {
                    int x = Math.abs(i-j);
                    int y = j;
                    if(plateau.grille[x][y] == null) break;
                    if(plateau.grille[x][y].type.equals(currentPionType)) count++;
                    if(!plateau.grille[x][y].type.equals(currentPionType)) break;
                }
                if(count==3) return true;
            }
            return false;
        }
}

package Controllers;
import Main.TicTacToe;
import Models.Partie;
import Models.Plateau;
import MyException.PionOutOfPlateau;
import MyException.PionOverlapAnOtherPion;
import Views.GrapicalElement.GraphicalCase;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ControllerMain implements EventHandler<MouseEvent> {
    public TicTacToe main;
    public Partie partie;
    public Plateau plateau;

    public ControllerMain(TicTacToe main) {
        this.main = main;
        beginTheParty();
    }

    /**
     * Init la Partie
     */
    public void beginTheParty(){
        partie = new Partie();
        plateau = partie.plateau;
        main.viewMain.setEvent(this);
        main.viewMain.textConsole.setText("Au tour du Joueur " + (partie.indiceJoueur + 1));
        main.viewMain.initPlateauGUI(partie.plateau,this);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // TANT QUE LA PARTIE N'EST PAS FINIS
        if(!partie.end) {
            // ON VERIFIE SI ON A CLIQUER SUR UNE ELEMENT GRAPHIQUE CASE
            if (mouseEvent.getSource() instanceof GraphicalCase) {
                // ON CAST
                GraphicalCase targetRect = (GraphicalCase) mouseEvent.getSource();
                // ON EXTRAIT LA POSITION DE LA CASE CIBLE
                int choiceX = targetRect.posX; int choiceY = targetRect.posY;
                try {
                    // CHECK SI LE MOVE EST POSSIBLE
                    plateau.verifierSiMouvementPossible(choiceX, choiceY);
                    // SI PAS D'ERREUR ON PLACE LE PION
                    plateau.placerPion(choiceX, choiceY, partie.currentJoueur.createPion());
                    // ACTIONNE L'UPDATE GRAPHIQUE
                    targetRect.setSymbol(partie.currentJoueur.pionType);
                    // ON INCREMENTE LE NOMBRE DE PION POSER SUR LE PLATEAU
                    plateau.incrementationDuTotalDePions();
                    // ON VERIFIE SI FIN DU GAME =>  SI LE JOUEUR COURANT A ALIGNER TROIS PIONS
                    //                           =>  OU SI LE PLATEAU ET PLEIN
                    partie.end = partie.isFinPartie(    partie.isCurrentPlayerWinTheGame(   partie.isTroisLigneAligne(),
                                                                                            partie.isTroisColoneAligne(),
                                                                                            partie.isTroisDiagonalAligne()),
                                                        plateau.isPlateauPlein());
                    // SI PARTIE TERMINE
                    if(partie.end){
                        // SI UN JOUER A GAGNER
                        if(partie.currentJoueur.win) {
                            main.viewMain.textConsole.setText("Partie Terminée!! \nLe Joueur " + (partie.indiceJoueur + 1 ) + " gagne !");
                        } else { // SINON EGALITE CAR PLATEAU PLEIN
                        main.viewMain.textConsole.setText("Partie Terminée!! \nEgalité !");
                        }
                        // ON AJOUTE UN BTN POUR REJOUER
                        main.viewMain.root.getChildren().add(main.viewMain.btnRetry);
                    } else { // SINON LA PARTIE CONTINUE
                        partie.joueurSuivant();
                        main.viewMain.textConsole.setText("Au tour du Joueur " + (partie.indiceJoueur + 1));
                    }
                    //SI ERREUR ON AFFICHE UN MESSAGE D'ERREUR
                } catch (PionOutOfPlateau | PionOverlapAnOtherPion pionOutOfPlateau) {
                    main.viewMain.textConsole.setText(    "Au tour du Joueur " + (partie.indiceJoueur + 1) + " !!!\n "
                                                    + pionOutOfPlateau.getMessage());
                }
            }
        }
        // SI ON CLIQUE SUR LE BTN RETRY ON REDEMARRE UNE PARTIE
        if(mouseEvent.getSource().equals(main.viewMain.btnRetry)){
            main.viewMain.root.getChildren().remove(main.viewMain.btnRetry);
            beginTheParty();
        }
    }
}

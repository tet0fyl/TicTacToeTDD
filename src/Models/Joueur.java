package Models;

public class Joueur {
    public boolean win;
    public Pion.Type pionType;

    public Joueur(Pion.Type pionType) {
        this.win = false;
        this.pionType = pionType;
    }

    /**
     * Creer un pion
     * @return le pion crée
     */
    public Pion createPion(){
        return new Pion(this.pionType);
    }

}

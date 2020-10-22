package Models;

public class Pion {
    public enum Type { CROIX, ROUND };
    public Type type;

    public Pion(Type type){
        this.type = type;
    }
}

package Views.GrapicalElement;


import Models.Pion;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GraphicalCase extends StackPane {
    public int sizeRectangle = 50;
    public Rectangle rectangle;
    public Label textSymbol;
    public int posX;
    public int posY;

    public GraphicalCase(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        rectangle = new Rectangle();
        textSymbol = new Label();
        textSymbol.setFont(Font.loadFont(GraphicalCase.class.getResourceAsStream("../../Asset/font/8bit.ttf"), 20));
        rectangle.setWidth(sizeRectangle);
        rectangle.setHeight(sizeRectangle);
        this.setLayoutX(posX *sizeRectangle);
        this.setLayoutY(posY *sizeRectangle);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().addAll(rectangle,textSymbol);
    }

    public void setSymbol(Pion.Type pionType){
        if(pionType.equals(Pion.Type.CROIX)){
            textSymbol.setText("X");
            textSymbol.setTextFill(Color.web("#eb4559",0.8));
        } else {
            textSymbol.setText("O");
            textSymbol.setTextFill(Color.web("#f78259",0.8));

        }
    }
}

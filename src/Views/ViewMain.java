package Views;

import Controllers.ControllerMain;
import Models.Plateau;
import Views.GrapicalElement.GraphicalCase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ViewMain {
    public VBox root;
    public Group grpGame;
    public Label textConsole;
    public Label textTitle;
    public Button btnRetry;

    public ViewMain(VBox root) {
        this.root = root;
        this.root.setAlignment(Pos.CENTER);
        // BOX DU JEU
        grpGame = new Group();
        grpGame.getStyleClass().add("shadow");

        // BTN RETRY
        btnRetry = new Button("TRY AGAIN");
        btnRetry.setFont(Font.loadFont(GraphicalCase.class.getResourceAsStream("../../Asset/font/8bit.ttf"), 15));
        // TITLE
        textTitle = new Label("TICTACTOE");
        textTitle.setFont(Font.loadFont(GraphicalCase.class.getResourceAsStream("../../Asset/font/8bit.ttf"), 30));
        textTitle.setPadding(new Insets(0,0,20,0));
        // MSG CONSOLE
        textConsole = new Label("A Tour du Joueur 1");
        textConsole.setFont(Font.loadFont(GraphicalCase.class.getResourceAsStream("../../Asset/font/8bit.ttf"), 8));
        textConsole.setTextAlignment(TextAlignment.CENTER);
        textConsole.setPadding(new Insets(20,0,20,0));
        // ADD TO ROOT
        root.getChildren().addAll(textTitle,grpGame,textConsole);
    }

    /**
     * Init le plateau graphique
     * @param plateau   model plateau
     * @param controllerMain le controller pour rendre les element graphique cliquable
     */
    public void initPlateauGUI(Plateau plateau, ControllerMain controllerMain) {
        for (int i = 0; i < plateau.nombreDeCase ; i++) {
            for (int j = 0; j < plateau.nombreDeCase; j++) {
                GraphicalCase graphCase = new GraphicalCase(j,i);
                graphCase.setOnMouseClicked(controllerMain);
                grpGame.getChildren().add(graphCase);
            }
        }
    }
    public void setEvent(ControllerMain controllerMain) {
        btnRetry.setOnMouseClicked(controllerMain);
    }
}

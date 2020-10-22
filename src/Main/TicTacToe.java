package Main;

import Controllers.ControllerMain;
import Views.ViewMain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    public VBox root;
    public Scene scene;
    public ViewMain viewMain;
    public ControllerMain controllerMain;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TICTACTOE!");
        VBox root = new VBox();
        viewMain = new ViewMain(root);
        controllerMain = new ControllerMain(this);
        scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("Asset/css/style.css");
        root.getStyleClass().add("bg");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

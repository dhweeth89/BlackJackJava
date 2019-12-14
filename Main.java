package guitest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene login, logout;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Label label1 = new Label("hello");
        Parent root = FXMLLoader.load(getClass().getResource("mainscreennotlogged.fxml"));
        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();

    }



}

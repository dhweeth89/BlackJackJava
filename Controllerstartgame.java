package guitest;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controllerstartgame {

    @FXML
    public Button Backbutton;

    @FXML
    public void Back(ActionEvent event) throws IOException {
        System.out.print("Goes back to main screen from start game \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainscreennotlogged.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public Button Selectbutton;

    @FXML
    public void Select(ActionEvent event) throws IOException{
        System.out.print("start game ask for players \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("playercounts.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}

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

public class Controller {

    @FXML public Button Managebutton;

    @FXML
    public void Manage(ActionEvent event) throws IOException {
        System.out.print("Run code user doesnt see \t");
        //start real code

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("managerooms.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    public Button Createbutton;

    @FXML
    public void Create(ActionEvent event) throws IOException {
        System.out.print("create account \t");
        //start real code

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("createaccount.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @FXML public Button Startbutton;

    @FXML
    public void Start(ActionEvent event) throws IOException {
        System.out.print("start game \t");
        //start real code

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("startgame.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public Button loginbutton;

    @FXML
    public void login(ActionEvent event) throws IOException {
    System.out.print("login \t");
    //login code for mongodb

    Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene tableViewScene = new Scene(tableViewParent);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();


    }
}

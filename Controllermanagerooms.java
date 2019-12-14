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

public class Controllermanagerooms {

    @FXML
    public Button Backbutton;

    @FXML
    public Button Buybutton;

    @FXML
    public Button Addbutton;

    @FXML
    public Button Removebutton;

    @FXML
    public void Back(ActionEvent event) throws IOException {
        System.out.print(" goes back to main screen not logged \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainscreennotlogged.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
    @FXML
    public void Add(ActionEvent event) throws IOException {
        System.out.print(" add room \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("addfunds.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
    @FXML
    public void Buy(ActionEvent event) throws IOException {
        System.out.print(" goes back to main screen not logged \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("buyroom.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    public void Remove(ActionEvent event) throws IOException {
        System.out.print(" goes back to main screen not logged \t");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("removefunds.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}

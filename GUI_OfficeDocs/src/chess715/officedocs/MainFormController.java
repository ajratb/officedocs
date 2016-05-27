/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.officedocs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 *
 * @author ayrat
 */
public class MainFormController implements Initializable {

    @FXML
    private Button btnSetDirectory;

    @FXML
    private Accordion accordion;

    @FXML
    private Button btnSearch;

    @FXML
    private TitledPane titledPanePath;

    @FXML
    private TextField txtSearch;

    @FXML
    private TitledPane titledPaneSearch;

    @FXML
    private Label lbPath;

    private String path = "";

    @FXML
    void handleBtnSetDirectory(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выберите папку хранения служебных писем");

        if (!path.equals("")) {
            dirChooser.setInitialDirectory(new File(path));
        }
        try {
            path = dirChooser.showDialog(lbPath.getScene().getWindow()).getPath();
            lbPath.setText(path);
        } catch (NullPointerException e) {
        }

    }

    @FXML
    void handleBtnSearch(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        accordion.setExpandedPane(titledPanePath);
    }

}

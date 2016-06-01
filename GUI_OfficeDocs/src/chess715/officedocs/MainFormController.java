/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.officedocs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accordion.setExpandedPane(titledPanePath);
    }

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
            boolean structured=structureDocs(path);
            if(structured)indexDocs();
            
        } catch (NullPointerException e) {
        }

    }

    @FXML
    void handleBtnSearch(ActionEvent event) {
        SearchResultForm dialog = new SearchResultForm(btnSearch.getParent(),null);
        dialog.showAndWait();
    }
    
    private boolean structureDocs(String path) {
        //1. если нет папки "StructuredDocs", то создаём
        Path dir=Paths.get(path);
        try(DirectoryStream<Path> stream=Files.newDirectoryStream(dir, "*.pdf")){
            for(Path file:stream){
                System.out.println(file.getFileName());
            }
        }catch(PatternSyntaxException|DirectoryIteratorException|IOException ex){
            System.err.println(ex);
        }
        //2. каждый файл из выбранной папки прогоняем через тику
        return false;
    }

    private void indexDocs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

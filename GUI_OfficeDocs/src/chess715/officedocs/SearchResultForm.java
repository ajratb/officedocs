/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.officedocs;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ayrat
 */
public class SearchResultForm extends Stage implements Initializable{
    
    @FXML
    private TextArea textArea;
    
    public SearchResultForm(Parent parent, List<SearchResult> results) {

        setResizable(false);

        initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("searchResultForm.fxml"));
        loader.setController(this);
        try {
            setScene(new Scene((Parent) loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setTitle("Результаты поиска");
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
}

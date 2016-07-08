/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.officedocs;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 *
 * @author ayrat
 */
public class SearchResultForm extends Stage implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button btnPrevPage;

    @FXML
    private Button btnNextPage;

    @FXML
    private Button btnMorePages;


    public SearchResultForm(Parent parent, List<SearchResult> results) {
//        start = 0;
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

    
    public void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query,
            int hitsPerPage, boolean raw, boolean interactive) throws IOException {

//        this.hitsPerPage = hitsPerPage;
        int start=0;
        // Collect enough docs to show 5 pages
        TopDocs results = searcher.search(query, 5 * hitsPerPage);
        ScoreDoc[] hits = results.scoreDocs;
        searcher.doc(start).getFields();
        int numTotalHits = results.totalHits;
        textArea.setText(numTotalHits + " total matching documents \n");
        
        int end = Math.min(numTotalHits, hitsPerPage);

        while (true) {

            end = Math.min(hits.length, start + hitsPerPage);

            for (int i = start; i < end; i++) {

                Document doc = searcher.doc(hits[i].doc);
                for(IndexableField f:doc.getFields()){
                    System.out.println("Name: "+f.name()+
                            " -stringValue: "+f.stringValue()+
                            " toString: "+f.toString()
                            );
                }
                
//                int contents=doc.getField("contents").binaryValue().bytes.length;//.readerValue();
                
                
                
//                while(r.read()!=-1){
//                    
//                    contents.concat(String.valueOf(r.read()));
//                    
//                }
                
//                System.out.println(contents);
                
                String path = doc.get("path");
                
                if (path != null) {
                    textArea.appendText((i + 1) + ". " + path + "\n");
                    String title = doc.get("title");
                    if (title != null) {
                        textArea.appendText("   Title: " + doc.get("title") + "\n");
                    }
                } else {
                    textArea.appendText((i + 1) + ". " + "No path for this document" + "\n");
                }

            }

            if (!interactive || end == 0) {
                break;
            }

            if (numTotalHits >= end) {              
                if (start - hitsPerPage >= 0) {
                    btnPrevPage.setDisable(false);
                }
                if (start + hitsPerPage < numTotalHits) {
                    btnNextPage.setDisable(false);
                }

            }

            break;
        }

        end = Math.min(numTotalHits, start + hitsPerPage);
        showAndWait();
    }

    @Override
        public void initialize(URL location, ResourceBundle resources) {
        //
    }

}

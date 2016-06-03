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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.event.HyperlinkEvent;
import org.apache.lucene.document.Document;
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

//    private int start;
//
//    private int hitsPerPage;

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

    /**
     * This demonstrates a typical paging search scenario, where the search
     * engine presents pages of size n to the user. The user can then go to the
     * next page if interested in the next hits.
     *
     * When the query is executed for the first time, then only enough results
     * are collected to fill 5 result pages. If the user wants to page beyond
     * this limit, then the query is executed another time and all hits are
     * collected.
     *
     */
    public void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query,
            int hitsPerPage, boolean raw, boolean interactive) throws IOException {

//        this.hitsPerPage = hitsPerPage;
        int start=0;
        // Collect enough docs to show 5 pages
        TopDocs results = searcher.search(query, 5 * hitsPerPage);
        ScoreDoc[] hits = results.scoreDocs;

        int numTotalHits = results.totalHits;
        textArea.setText(numTotalHits + " total matching documents \n");
        
        int end = Math.min(numTotalHits, hitsPerPage);

        while (true) {
            //если результатов несколько страниц, то просто активна кнопка "Ещё"
//            if (end > hits.length) {
//                textArea.setText("Only results 1 - " + hits.length + " of "
//                        + numTotalHits + " total matching documents collected. \n");
//                //textArea.setText("Collect more (y/n) ?");
//                btnMorePages.setDisable(false);
//
//                hits = searcher.search(query, numTotalHits).scoreDocs;
//            }

            end = Math.min(hits.length, start + hitsPerPage);

            for (int i = start; i < end; i++) {
//                if (raw) {                              // output raw format
//                    textArea.setText("doc=" + hits[i].doc + " score=" + hits[i].score);
//                    continue;
//                }

                Document doc = searcher.doc(hits[i].doc);
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
               

//                    System.out.print("Press ");
                if (start - hitsPerPage >= 0) {
                    btnPrevPage.setDisable(false);
                }
                if (start + hitsPerPage < numTotalHits) {
                    btnNextPage.setDisable(false);
                }
//                    System.out.println("(q)uit or enter number to jump to a page.");

//                    String line = in.readLine();
//                    if (line.length() == 0 || line.charAt(0) == 'q') {
//                        quit = true;
//                        break;
//                    }
                //Переход на страницу
//                        int page = Integer.parseInt(line);
//                        if ((page - 1) * hitsPerPage < numTotalHits) {
//                            start = (page - 1) * hitsPerPage;
//                            break;
//                        } else {
//                            System.out.println("No such page");
//                        }
            }

            break;
        }

        end = Math.min(numTotalHits, start + hitsPerPage);
        showAndWait();
    }


//        btnNextPage.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//            @Override
//        public void handle(ActionEvent event) {
//                if (start + hitsPerPage < numTotalHits) {
//                            start += hitsPerPage;
//                        }
//            }
//        });
//
//        btnPrevPage.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//            @Override
//        public void handle(ActionEvent event) {
//                start = Math.max(0, start - hitsPerPage);
//            }
//        });
//
//        btnMorePages.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//            @Override
//        public void handle(ActionEvent event) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });

        

    @Override
        public void initialize(URL location, ResourceBundle resources) {
        //
    }

}

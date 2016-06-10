/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715.officedocs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.demo.IndexFiles;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

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

    @FXML
    private Label lbSearchWarning;

    private String path = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accordion.setExpandedPane(titledPanePath);
        titledPaneSearch.setCollapsible(false);
        lbSearchWarning.setText("");
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
            lbPath.setText(path + "\n not indexed yet");
            boolean structured = structureDocs(path);
            if (structured) {
                String indexPath = path + "\\Index";
                
                indexIt();
            }
            titledPaneSearch.setCollapsible(true);
        } catch (NullPointerException e) {
        }

    }

    @FXML
    void handleBtnSearch(ActionEvent event) {
        searchIt();
    }

    private boolean structureDocs(String path) {
        //1. если нет папки "StructuredDocs", то создаём
        Path dir = Paths.get(path);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{pdf,ppt,docx,pptx}")) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
            return true;
        } catch (PatternSyntaxException | DirectoryIteratorException | IOException ex) {
            System.err.println(ex);
            return false;
        }
        //2. каждый файл из выбранной папки прогоняем через тику

    }

    private void indexIt() {
        String indexPath = path + "\\Index";
        
        boolean create = true;
        
        final Path indexDir = Paths.get(indexPath);
        if (Files.exists(indexDir)) {
            create = false;
        }

        final Path docDir = Paths.get(path);
        if (!Files.isReadable(docDir)) {
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not readable, please check the path");
            System.exit(1);
        }

        Date start = new Date();
        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");

            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            if (create) {
                // Create a new index in the directory, removing any
                // previously indexed documents:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            } else {
                // Add new documents to an existing index:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }

            // Optional: for better indexing performance, if you
            // are indexing many documents, increase the RAM
            // buffer.  But if you do this, increase the max heap
            // size to the JVM (eg add -Xmx512m or -Xmx1g):
            //
            // iwc.setRAMBufferSizeMB(256.0);
            IndexWriter writer = new IndexWriter(dir, iwc);
            IndexFiles.indexDocs(writer, docDir);

            // NOTE: if you want to maximize search performance,
            // you can optionally call forceMerge here.  This can be
            // a terribly costly operation, so generally it's only
            // worth it when your index is relatively static (ie
            // you're done adding documents to it):
            //
            // writer.forceMerge(1);
            writer.close();

            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");

        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass()
                    + "\n with message: " + e.getMessage());
        }

        lbPath.setText(path + "\n indexed");
    }

    private void searchIt() {
        String index = path + "\\Index";
        String field = "contents";

        String queryString = null;
        int repeat = 0;
        boolean raw = false;//if true, uncomment in doPagingSearch method

        int hitsPerPage = 20;

        IndexReader reader;
        try {
            reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
//Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer();

            BufferedReader in = null;

            in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("Windows-1251")));
            QueryParser parser = new QueryParser(field, analyzer);
            while (true) {
                
                if (txtSearch.getText().equals("")) {
                    lbSearchWarning.setText("Введите строку поиска");
                }

                String line = queryString != null ? queryString : txtSearch.getText();

                if (line == null || line.length() == -1) {
                    break;
                }

                line = line.trim();
                if (line.length() == 0) {
                    break;
                }

                Query query;
                try {
                    query = parser.parse(line);

                    System.out.println("Searching for: " + query.toString(field));

                    if (repeat > 0) {                           // repeat & time as benchmark
                        Date start = new Date();
                        for (int i = 0; i < repeat; i++) {
                            searcher.search(query, 100);
                        }
                        Date end = new Date();
                        System.out.println("Time: " + (end.getTime() - start.getTime()) + "ms");
                    }

                    SearchResultForm dialog = new SearchResultForm(btnSearch.getParent(), null);

                    dialog.doPagingSearch(in, searcher, query, hitsPerPage,
                            raw, true);
                    
                    txtSearch.setText("");

//                    if (queryString != null) {
                        break;
//                    }
                } catch (ParseException ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        dialog.showAndWait();
    }

}

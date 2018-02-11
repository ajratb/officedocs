/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.AreaBreakType;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 *
 * @author Ayrat
 */
public class MainController implements Initializable {

    @FXML
    private Button btnGetFile;

    @FXML
    private Label label;

    @FXML
    private Button btnGetPdf;

    List<File> files = new ArrayList<>();

    @FXML
    void onAction_btnGetFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());
        if (f != null) {
            if (FilesUtils.checkFileIsImg(f)) {
                files.add(f);
            }
        }
    }

    @FXML
    void onAction_btnGetPdf(ActionEvent event) {

        files.forEach(f -> System.out.println(f.getName()));
        try {
            File file = new File("C:/test.pdf");
            file.getParentFile().mkdirs();
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter("d:/test.pdf"));
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            document.setMargins(15, 10, 10, 10);

            for (int i=0; i<files.size();i++) {
                Image img = new Image(ImageDataFactory.create(files.get(i).getAbsolutePath()));
                img.setAutoScale(true);
                
                document
                        //                    .add(new Text("The quick brown "))
                        .add(img)
                //                    .add(new Text(" jumps over the lazy "))
                //                    .add(new Image(ImageDataFactory.create(files.get(1).getAbsolutePath())))
               ;
                if(i<files.size()-1)
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }
            // step 4
            document.close();
        } catch (FileNotFoundException fnfEx) {
            System.out.println("FileNotFoundException");
            fnfEx.printStackTrace();
        } catch (MalformedURLException urlEx) {
            System.out.println("MalformedURLException");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

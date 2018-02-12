/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.officedocs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author ayrat
 */
public class BasicPOI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("first.docx"));
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("This is Apache, babe");
        document.write(out);
        out.close();
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.officedocs;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author ayrat
 */
public class BasicTikaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new EncodingDetection();

        System.out.println(EncodingDetection.getADR().getCharset().name());
        
        Examples examples=new Examples();
        String resource="./docs/SampleChapter-8.pdf";
        try {
            System.out.println(examples.parseToHTML(resource));
        } catch (IOException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
//catch (NullPointerException ex) {
//            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.INFO, "ebs", ex);
//        }
    }    
}

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
        // TODO code application logic here
        Examples examples=new Examples();
        try {
            System.out.println(examples.parseExample());
        } catch (IOException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
            Logger.getLogger(BasicTikaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

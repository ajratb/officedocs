/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.officedocs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.txt.Icu4jEncodingDetector;
import org.apache.tika.parser.txt.UniversalEncodingDetector;

/**
 *
 * @author ayrat
 */
public class EncodingDetection {

    public EncodingDetection() {
        AutoDetectReader adr;
        String res = "";

        EncodingDetector r = new Icu4jEncodingDetector();
        try {
            TikaInputStream stream = TikaInputStream.get(Paths.get("./docs/SampleChapter-8.pdf"));
            Charset chs = r.detect(stream, new Metadata());
            System.out.println(chs.name());
        } catch (IOException ex) {
            Logger.getLogger(EncodingDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static  AutoDetectReader getADR() {
        AutoDetectReader adr = null;
        try (InputStream is = TikaInputStream.get(Paths.get("./docs/русский.docx"))) {
            adr = new AutoDetectReader(is);
        } catch (IOException ex) {
            Logger.getLogger(EncodingDetection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
            Logger.getLogger(EncodingDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
return adr;
    }

    public static void main(String[] args) {
        String res = "мама мыла раму";
        File file = new File("testEncoding.txt");
        try {
            byte[] win1251bytes = res.getBytes("windows-1251");
            byte[] utf8bytes = res.getBytes("utf8");
            String utf8String = new String(utf8bytes, "utf8");
            String win1251String = new String(win1251bytes, "windows-1251");
            byte[] bytes = win1251String.getBytes();
            
            System.out.println(new String(bytes, "utf8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncodingDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

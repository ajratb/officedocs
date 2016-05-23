/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.officedocs;

import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author ayrat
 */
public class Examples {
    public String parseToStringExample() throws IOException, SAXException, TikaException {
    Tika tika = new Tika();
    try (InputStream stream = ParsingExample.class.getResourceAsStream("test.doc")) {
        return tika.parseToString(stream);
    }
}
}

/*
 * http://tika.apache.org/1.12/examples.html
 */
package waytosky.officedocs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author ayrat
 *
 * воспроизвожу примеры из http://tika.apache.org/1.12/examples.html (Apache
 * Tika API Usage Examples) Похоже что есть все необходимое! Там, где
 * getResourceAsStream берётся из класса Xx..xExample Просто подставляю этот
 * класс
 *
 */
public class Examples {

    /**
     * Parsing using the Tika Facade
     *
     * The Tika facade, provides a number of very quick and easy ways to have
     * your content parsed by Tika, and return the resulting plain text
     *
     * @return The content of a file.
     */
    public String parseToStringExample(String resource) throws IOException, SAXException, TikaException {
        Tika tika = new Tika();
        //Here was ParsingExample class
        try (InputStream stream = Examples.class.getResourceAsStream(resource)) {
            return tika.parseToString(stream);
        }
    }

    /**
     * Parsing using the Auto-Detect Parser
     *
     * For more control, you can call the Tika Parsers directly. Most likely,
     * you'll want to start out using the Auto-Detect Parser, which
     * automatically figures out what kind of content you have, then calls the
     * appropriate parser for you.
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public String parseExample(String resource) throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        //Here was ParsingExample class
        try (InputStream stream = new FileInputStream(resource)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    //=========================================================================
    // -------------------- Picking different output formats ------------------
    //=========================================================================
    /**
     * Parsing to Plain Text
     *
     * By using the BodyContentHandler, you can request that Tika return only
     * the content of the document's body as a plain-text string.
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public String parseToPlainText(String resource) throws IOException, SAXException,
            TikaException {
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        //Here was ContentHandlerExample class
        try (InputStream stream = Examples.class.getResourceAsStream(resource)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    public String parseToHTML(String resource) throws IOException, SAXException, TikaException {
        ContentHandler handler = new ToXMLContentHandler();
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        //Here was ContentHandlerExample class
        try (InputStream stream = new FileInputStream(resource)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    public String parseBodyToHTML(String resource) throws IOException, SAXException, TikaException {
        ContentHandler handler = new BodyContentHandler(new ToXMLContentHandler());
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        //Here was ContentHandlerExample class
        try (InputStream stream = Examples.class.getResourceAsStream(resource)) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    //...
}

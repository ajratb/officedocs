/*
 * 
 * Скопировано из Apache Tika examples 
 * последний метод закоментирован так как отсутствует нужный класс
 *
 */

package waytosky.officedocs;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.serialization.JsonMetadataList;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.sax.BasicContentHandlerFactory;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
     * 
     * Скопировано из Apache Tika examples
     * 
*/
public class ParsingExample {
    
    
    /**
     * Стёр то что дублируется в примере
     */

    /**
     * If you don't want content from embedded documents, send in
     * a {@link org.apache.tika.parser.ParseContext} that does not contain a
     * {@link Parser}.
     *
     * @return The content of a file.
     */
    public String parseNoEmbeddedExample() throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try (InputStream stream = ParsingExample.class.getResourceAsStream("test_recursive_embedded.docx")) {
            parser.parse(stream, handler, metadata, new ParseContext());
            return handler.toString();
        }
    }


    /**
     * This example shows how to extract content from the outer document and all
     * embedded documents.  The key is to specify a {@link Parser} in the {@link ParseContext}.
     *
     * @return content, including from embedded documents
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public String parseEmbeddedExample() throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        context.set(Parser.class, parser);
        try (InputStream stream = ParsingExample.class.getResourceAsStream("test_recursive_embedded.docx")) {
            parser.parse(stream, handler, metadata, context);
            return handler.toString();
        }
    }

    /**
     * For documents that may contain embedded documents, it might be helpful
     * to create list of metadata objects, one for the container document and
     * one for each embedded document.  This allows easy access to both the
     * extracted content and the metadata of each embedded document.
     * Note that many document formats can contain embedded documents,
     * including traditional container formats -- zip, tar and others -- but also
     * common office document formats including: MSWord, MSExcel,
     * MSPowerPoint, RTF, PDF, MSG and several others.
     * <p>
     * The "content" format is determined by the ContentHandlerFactory, and
     * the content is stored in {@link org.apache.tika.parser.RecursiveParserWrapper#TIKA_CONTENT}
     * <p>
     * The drawback to the RecursiveParserWrapper is that it caches metadata and contents
     * in memory.  This should not be used on files whose contents are too big to be handled
     * in memory.
     *
     * @return a list of metadata object, one each for the container file and each embedded file
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public List<Metadata> recursiveParserWrapperExample() throws IOException,
            SAXException, TikaException {
        Parser p = new AutoDetectParser();
        ContentHandlerFactory factory = new BasicContentHandlerFactory(
                BasicContentHandlerFactory.HANDLER_TYPE.HTML, -1);

        RecursiveParserWrapper wrapper = new RecursiveParserWrapper(p, factory);
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, "test_recursive_embedded.docx");
        ParseContext context = new ParseContext();

        try (InputStream stream = ParsingExample.class.getResourceAsStream("test_recursive_embedded.docx")) {
            wrapper.parse(stream, new DefaultHandler(), metadata, context);
        }
        return wrapper.getMetadata();
    }

    /**
     * We include a simple JSON serializer for a list of metadata with
     * {@link org.apache.tika.metadata.serialization.JsonMetadataList}.
     * That class also includes a deserializer to convert from JSON
     * back to a List<Metadata>.
     * <p>
     * This functionality is also available in tika-app's GUI, and
     * with the -J option on tika-app's commandline.  For tika-server
     * users, there is the "rmeta" service that will return this format.
     *
     * @return a JSON representation of a list of Metadata objects
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public String serializedRecursiveParserWrapperExample() throws IOException,
            SAXException, TikaException {
        List<Metadata> metadataList = recursiveParserWrapperExample();
        StringWriter writer = new StringWriter();
        JsonMetadataList.toJson(metadataList, writer);
        return writer.toString();
    }


    /**
     * @param outputPath -- output directory to place files
     * @return list of files created
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
//    public List<Path> extractEmbeddedDocumentsExample(Path outputPath) throws IOException,
//            SAXException, TikaException {
//        InputStream stream = ParsingExample.class.getResourceAsStream("test_recursive_embedded.docx");
//        ExtractEmbeddedFiles ex = new ExtractEmbeddedFiles();
//        ex.extract(stream, outputPath);
//        List<Path> ret = new ArrayList<>();
//        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(outputPath)) {
//            for (Path entry : dirStream) {
//                ret.add(entry);
//            }
//        }
//        return ret;
//    }
}

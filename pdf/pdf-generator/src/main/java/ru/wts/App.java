package ru.wts;

import java.io.ByteArrayOutputStream;
import com.lowagie.text.Anchor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        new App().createPdf();
    }

    private void createPdf() {
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        try (FileOutputStream fos = new FileOutputStream("C:/temp/sample.pdf"); // step 1: creation of a document-object
                Document document = new Document()) {
            // step 2:
            // we create 3 different writers that listen to the document
            PdfWriter pdf
                    = //PdfWriter.getInstance(document, baos);
                    PdfWriter.getInstance(document, fos);
            // step 3: we open the document
            document.open();
            document.setPageSize(PageSize.A4);
//            document.setMargins(4, 4, 4, 4);

            // step 4: we add a paragraph to the document
//            document.add(new Paragraph("Hello World"));
            // we make references
//            Anchor pdfRef = new Anchor("see Hello World in PDF.");
//            pdfRef.setReference("./HelloWorldPdf.pdf");
//            Anchor rtfRef = new Anchor("see Hello World in RTF.");
//            rtfRef.setReference("./HelloWorldRtf.rtf");
            // we add the references, but only to the HTML page:
//            pdf.pause();
//            document.add(pdfRef);
            document.add(Chunk.NEWLINE);

//            document.add(new Paragraph("A picture of my dog: otsoe.jpg"));

            String[] imgs = {"small", "wider", "taller","large", "large2"};
            for(String s: imgs){
                System.out.println("! file:"+s);
                  URI uri = App.class.getResource("/img/"+s+".PNG").toURI();
            Image jpg = Image.getInstance(uri.toURL());
            AutoScaledImage asImg = new AutoScaledImage(jpg);document.add(asImg.getImage());
            document.newPage();
            }
          
//            jpg.scaleToFit(PageSize.A4.getWidth()-8, PageSize.A4.getHeight()-8);
//            jpg.setAlignment(Image.MIDDLE);
//            jpg.setAbsolutePosition(4,
//                    4);
            
            
//            document.add(rtfRef);
//document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            
            
//            URI uri2 = App.class.getResource("/img/Penguins.jpg").toURI();
//            Image jpg2 = Image.getInstance(uri2.toURL());
//            AutoScaledImage asImg2 = new AutoScaledImage(jpg2);
////            jpg2.scaleToFit(PageSize.A4.getWidth() - 4, PageSize.A4.getHeight() - 4);
////            jpg2.scaleAbsoluteWidth(PageSize.A4.getWidth());
////            jpg2.setAlignment(Image.MIDDLE);
//            document.add(asImg2.getImage());
//            
//             URI uri3 = App.class.getResource("/img/iText.tif").toURI();
//            Image jpg3 = Image.getInstance(uri3.toURL());
//            jpg3.scaleToFit(PageSize.A4.getWidth() - 20, PageSize.A4.getHeight() - 20);
//            document.add(jpg3);
//            
            
//            pdf.resume();

        } catch (IOException ioEx) {
            System.out.println("ioEx: " + ioEx.getMessage());
        } catch (URISyntaxException uriEx) {
            System.out.println("uriEx: " + uriEx.getMessage());
        }
    }
}


import com.asprise.imaging.core.Imaging;
import com.asprise.imaging.core.Request;
import com.asprise.imaging.core.RequestOutputItem;
import com.asprise.imaging.core.Result;
import com.asprise.imaging.core.scan.twain.TwainConstants;
import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BikchentaevAA
 */
public class TwainStudy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Result result = new AspriseScanUI().setRequest(new Request()
                .setTwainCap( // Scan in color:
                        TwainConstants.ICAP_PIXELTYPE, TwainConstants.TWPT_RGB)
                .setTwainCap( // Paper size: US letter
                        TwainConstants.ICAP_SUPPORTEDSIZES, TwainConstants.TWSS_USLETTER)
                .addOutputItem(
                        new RequestOutputItem(Imaging.OUTPUT_SAVE, Imaging.FORMAT_JPG)
                                .setSavePath(".\\${TMS}${EXT}") // Timestamp as file name
                )
                .addOutputItem(
                        new RequestOutputItem(Imaging.OUTPUT_SAVE_THUMB,
                                Imaging.FORMAT_JPG).setSavePath(".\\${TMS}-thumb${EXT}")
                )
        ).showDialog(null, "Scan", true, null); // owner can be null
        
    }

}

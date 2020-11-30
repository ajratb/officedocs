package ru.wts;

import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

/**
 *
 * @author BikchentaevAA
 */
public class AutoScaledImage {

    private final Image img;

    private final float A4_WIDTH = PageSize.A4.getWidth() - 2;
    private final float A4_HEIGHT = PageSize.A4.getHeight() - 2;

    private final float[] startPos = {1, 1};

    private float timesWider = 1;
    private float timesTaller = 1;

//    Logger appLog = AppLoggerHolder.get();

    public AutoScaledImage(Image img) {

        this.img = img;
        img.scaleToFit(A4_WIDTH, A4_HEIGHT);

        if (imgWiderThanA4()) {
            timesWider = img.getWidth() / A4_WIDTH;
//            appLog.debug("in {} times wider", timesWider);
            System.out.println("in " + timesWider + " times wider");
        }

        if (imgTallerThanA4()) {
            timesTaller = img.getHeight() / A4_HEIGHT;
//            appLog.debug("in {} times taller", timesTaller);
        }

        if (timesWider - timesTaller > 0.001) {
//            appLog.debug("- WIDER!: calculate Y higher position");
            startPos[1] = calculateYHigherPos();//calculateYPos();
        } else if (timesTaller - timesWider > 0.001) {
//            appLog.debug("- TALLER!: calculate X position");
            startPos[0] = calculateXPos();
        } else {
//            appLog.debug("- PROPORTIONAL IMG OR SMALLER: ");
            if (timesTaller > 1 || timesWider > 1) {//PROPORTIONAL
//                appLog.debug("-- set ABS.POSITION: {}, {}", startPos[0], startPos[1]);
                img.setAbsolutePosition(startPos[0], startPos[1]);//1, 1
                return;
            }
            final double A4_PROPORTION = A4_WIDTH / A4_HEIGHT;
            final double IMG_PROPORTION = img.getWidth() / img.getHeight();
            
//            appLog.debug("- {} : {}", A4_PROPORTION, IMG_PROPORTION);
            
            if (img.getWidth() - img.getHeight() > -100) {
//                appLog.debug("-- set MIDDLE alignment");
                img.setAlignment(Image.MIDDLE);
                return;
            }
            
        }
        img.setAbsolutePosition(startPos[0], startPos[1]);
    }

    private boolean imgWiderThanA4() {
        float w = img.getWidth();
        if (Math.abs(w - A4_WIDTH) > 0.5) {
            return w > A4_WIDTH;
        }
        return false;
    }

    private boolean imgTallerThanA4() {
        float h = img.getHeight();
        if (Math.abs(h - A4_HEIGHT) > 0.5) {
            return h > A4_HEIGHT;
        }
        return false;
    }

    private float calculateYHigherPos() {
        float times = img.getWidth() / A4_WIDTH;
        float heightAfter = img.getHeight() / times;
        return (A4_HEIGHT - heightAfter);
    }

    private float calculateXPos() {
        float times = img.getHeight() / A4_HEIGHT;
        float widthAfter = img.getWidth() / times;
        return (A4_WIDTH - widthAfter) / 1.9f;
    }

    public Image getImage() {
        return img;
    }

//    private float calculateYPos() {
//        float times = img.getWidth() / A4_WIDTH;
//        float heightAfter = img.getHeight() / times;
//        return (A4_HEIGHT - heightAfter) / 2;
//    }
}

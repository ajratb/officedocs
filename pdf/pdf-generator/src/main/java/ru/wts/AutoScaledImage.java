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

    public AutoScaledImage(Image img) {
        this.img = img;
        img.scaleToFit(A4_WIDTH, A4_HEIGHT);
        if (imgWider()) {
            timesWider = img.getWidth() / A4_WIDTH;
            System.out.println("in " + timesWider + " times wider");
        }
        if (imgTaller()) {
            timesTaller = img.getHeight() / A4_HEIGHT;
            System.out.println("in " + timesTaller + " times taller");
        }

        if (timesWider - timesTaller > 0.01) {
            System.out.println("WIDER!");
            startPos[1] = calculateYHigherPos();//calculateYPos();
        } else if (timesTaller - timesWider > 0.01) {
            System.out.println("TALLER!");
            startPos[0] = calculateXPos();
        } else {
            if (timesTaller > 1 || timesWider > 1) {
                img.setAbsolutePosition(startPos[0], startPos[1]);
            }
            img.setAlignment(Image.MIDDLE);
            return;
        }
        img.setAbsolutePosition(startPos[0], startPos[1]);

    }

    private boolean imgWider() {
        float w = img.getWidth();
        if (Math.abs(w - A4_WIDTH) > 0.5) {
            return w > A4_WIDTH;
        }
        return false;
    }

    private float calculateYPos() {
        float times = img.getWidth() / A4_WIDTH;
        float heightAfter = img.getHeight() / times;
        return (A4_HEIGHT - heightAfter) / 2;
    }

    private float calculateYHigherPos() {
        float times = img.getWidth() / A4_WIDTH;
        float heightAfter = img.getHeight() / times;
        return (A4_HEIGHT - heightAfter);
    }

    private boolean imgTaller() {
        float h = img.getHeight();
        if (Math.abs(h - A4_HEIGHT) > 0.5) {
            return h > A4_HEIGHT;
        }
        return false;
    }

    private float calculateXPos() {
        float times = img.getHeight() / A4_HEIGHT;
        float widthAfter = img.getWidth() / times;
        return (A4_WIDTH - widthAfter) / 1.9f;
    }

    public Image getImage() {
        return img;
    }
}

package captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Random;

public class Captcha {
    private final static int WIDTH = 200;
    private final static int HEIGHT = 100;
    private RenderedImage image;
    private String code;
    private Saver saver;

    public Captcha(String code) {
        this.code = code;

        image = fromCode(code);
    }

    /**
     * Generates random captcha from 100 to 199
     */
    public Captcha() {
        // generate from 100 to 199
        this(String.valueOf(new Random().nextInt(100) + 100));
    }

    public static RenderedImage fromCode(String code) {
        final int X_OFFSET = 50;
        final int Y_OFFSET = 20;

        BufferedImage i = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = i.createGraphics();
        g.drawString(code, X_OFFSET, Y_OFFSET);

        return i;
    }

    public void save(String path) throws IOException {
        saver.save(image, path);
    }
}

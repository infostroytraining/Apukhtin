package captcha;

import java.awt.image.RenderedImage;
import java.io.IOException;

public class Captcha {
    private RenderedImage image;
    private String code;
    private Saver saver;

    public Captcha(String code) {
        this.code = code;
    }

    public Captcha() {
    }

    public void save(String path) throws IOException {
        saver.save(image, path);
    }

}

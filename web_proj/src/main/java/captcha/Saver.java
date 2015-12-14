package captcha;

import java.awt.image.RenderedImage;
import java.io.IOException;

public interface Saver {
    void save(RenderedImage i, String path) throws IOException;
}

package captcha;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by vlad on 12.12.2015.
 */
public class JPGSaver implements Saver {
    @Override
    public void save(RenderedImage i, String path) throws IOException {
        ImageIO.write(i, "JPG", new File(path));
    }
}

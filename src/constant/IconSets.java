package constant;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.*;

public class IconSets {
    private final static Logger logger;

    private final static String FRAME_LOGO_PATH = "img/marco-logo.png";
	private final static String LABEL_LOGO_PATH = "img/marco-logo.png";
    private final static int LABEL_ICON_WIDTH_SIZE = 180;
    private final static int LABEL_ICON_HEIGHT_SIZE = 40;

    static {
        logger = Logger.getLogger(IconSets.class.getName());
    }

    private static ImageIcon validateImageFile(String path) {
        File file = new File(path);
        try {
            FileReader logoFileReader = new FileReader(file);
            logger.log(Level.INFO, String.format("Loaded image: %s, file: %s", path, file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING,
                    String.format("Failed to load images. Attempted path: %s", file.getAbsolutePath()),
                    e);
            throw new RuntimeException(e);
        }
        return new ImageIcon(path);
    }

    public static Image getFrameLogoImage() {
        ImageIcon imageLogo = validateImageFile(IconSets.FRAME_LOGO_PATH);
        return imageLogo.getImage();
    }

    public static ImageIcon getLabelIcon() {
        ImageIcon imageIcon = validateImageFile(IconSets.LABEL_LOGO_PATH);
        Image image = imageIcon.getImage().getScaledInstance(LABEL_ICON_WIDTH_SIZE, LABEL_ICON_HEIGHT_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}

package classfile.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconSets {
    //Add image files to relaive directory.
	private final String FRAME_LOGO = "../img/NAVYLOGO_02.png"; //must edit: do not use absolute directory location.
	private final String LABEL_LOGO = "../img/LabelLogo_mini.png"; //must edit: do not use absolute directory location.

    //TODO Refactor - file not found detection needed.
	static ImageIcon mainIcon = new ImageIcon(IconSets.FRAME_LOGO);
	static ImageIcon labelIcon = new ImageIcon(IconSets.LABEL_LOGO);

    public static Image getMainIconImage() {
        return mainIcon.getImage();
    }

    public static Image getLabelIconImage() {
        return mainIcon.getImage();
    }
}

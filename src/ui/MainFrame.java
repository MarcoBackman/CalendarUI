package ui;

import constant.IconSets;

import javax.swing.JFrame;
import java.util.logging.Logger;

public class MainFrame extends JFrame {

    private final static Logger logger = Logger.getLogger(MainFrame.class.getName());
	private static final long serialVersionUID = 0L;

    //Fixed frame size
	final int FRAME_WIDTH = 900, FRAME_HEIGHT = 700;

    //global variable
    MenuBar menuBar; //This is discrete from java.awt.MenuBar;
    MainPanel mainPanel;

    public MainFrame () {
        //Name of the frame
        super("Duty Assigner");
        logger.info("Frame initialization commenced");

        //Components initialization
    	setupFrame();
        addMenuBar();
        addMainPanel();
        validate();

        logger.info("Completed frame initialization");
    }

	private void setupFrame() {
        logger.info("Setting up the frame...");
        setIconImage(IconSets.getFrameLogoImage());
        setResizable(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        CommonUIData.setScreenLocation(this, FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(CommonUIData.SCREEN_CENTER_X,
                    CommonUIData.SCREEN_CENTER_Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();
    }

    private void addMenuBar() {
        logger.info("Setting up the menu bar...");
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
    }

    public void addMainPanel() {
        logger.info("Setting up the panel...");
        mainPanel = new MainPanel();
        this.add(mainPanel);
    }
}

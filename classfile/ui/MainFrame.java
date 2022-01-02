package classfile.ui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 0L;

    //Fixed frame size
	final int FRAME_WIDTH = 900, FRAME_HEIGHT = 700;

    //global variable
    MenuBar menuBar; //This is discrete from java.awt.MenuBar;
    MainPanel mainPanel;

    public MainFrame () {
        //Name of the frame
        super("Duty Assigner");

        //Components initialization
    	setupFrame();
        addMenuBar();
        addMainPanel();
        validate();
    }

	private void setupFrame() {
		setIconImage(IconSets.getMainIconImage());
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
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
    }

    public void addMainPanel() {
        mainPanel = new MainPanel();
        this.add(mainPanel);
    }
}

package classfile.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

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
        setResizable(false);
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

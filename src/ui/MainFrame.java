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

import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame implements ActionListener,
                                                 MenuKeyListener,
                                                 MouseListener {

	private static final long serialVersionUID = 0L;

    //Fixed frame size
	final int FRAME_WIDTH = 900, FRAME_HEIGHT = 700;

    //global variable
    MenuBar menuBar;

    //Panels - TODO refactor these
    JPanel mainPanel, calendarPanel, rightPanel, lowerPanel;
    JPanel upperMargin, lowerMargin, leftMargin, rightMargin;


    public MainFrame () {
        //Name of the frame
        super("Duty Assigner");

        //Components initialization
    	setupFrame();
        addMenuBar();
        setCalendarComponent();
        addPanels();
        activateListener();
    }

	private void setupFrame() {
		setIconImage(IconSets.getMainIconImage());
        setResizable(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        CommonUIData.getScreenLocation(this, FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(CommonUIData.SCREEN_CENTER_X,
                    CommonUIData.SCREEN_CENTER_Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setVisible(true);
        requestFocusInWindow();
    }

    private void addMenuBar() {
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
    }

    private void setCalendarComponent() {
        calendarPanel = new JPanel(); //put Calendar panel to here
        //calendarPanel.setIcon(FRAME_IMAGE); //panel does not support setIcon()
        calendarPanel.setLayout(new GridLayout(7, 6, 15, 15));
        calendarPanel.setBackground(IconSets.WHITE_BACKGROUND);
        calendarPanel.setOpaque(true);
    }

    // TODO: refactor these
    private void addPanels () {
        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(1, 1, 10, 10));

        mainPanel.add(calendarPanel, BorderLayout.WEST);
        mainPanel.addMouseListener(this);
        mainPanel.setFocusable(true);

        upperMargin = new JPanel();
        lowerMargin = new JPanel();
        leftMargin = new JPanel();
        rightMargin = new JPanel();

		//top margin labels: calendar

		//lower margin buttons
		setButtons();


        //------lower panel setup - buttons for additional features----//
        lowerPanel = new JPanel();                                     //
        lowerPanel.setLayout(new GridLayout(2, 0));                    //
                                                                       //
		//margin add at the bottom-left                                //
        lowerPanel.add(lowerMargin);                                   //
		                                                               //
		//process panel located at the bottom-right tab                //
        JPanel processPanel = new ProcessPanel();                      //
        lowerPanel.add(processPanel);                                  //
		//------------------ end of lower panel setup -----------------//


        this.add(upperMargin, BorderLayout.NORTH);
        this.add(leftMargin, BorderLayout.WEST);
        this.add(rightMargin, BorderLayout.EAST);
        this.add(lowerPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);

        validate();
    }

    // TODO: refactor these
    public void activateListener() {
		standerButton.addActionListener(this);
        tableButton.addActionListener(this);
        dateSelectButton.addActionListener(this);
    	genButton.addActionListener(this);
    }

    public void activateMenuListener() {
        open.addActionListener(this);
        exit.addActionListener(this);
        addStander.addActionListener(this);
        help.addActionListener(this);
        version.addActionListener(this);

        open.addMenuKeyListener(this);
        exit.addMenuKeyListener(this);
        addStander.addMenuKeyListener(this);
        help.addMenuKeyListener(this);
        version.addMenuKeyListener(this);
    }

    @Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuKeyTyped(MenuKeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuKeyPressed(MenuKeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuKeyReleased(MenuKeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

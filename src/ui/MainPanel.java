package classfile.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class MainPanel extends JPanel implements ActionListener,
                                          MouseListener {

    final int MAX_BLOCK_COUNT = 42;

    //External Panel
    JPanel centerPanel, northMargin, southMargin, westMargin, eastMargin;

    //Internal Panel(Sub panel of @centerPanel)
    JPanel calendarPanel, buttonPanel;

    //Internal Panel(Sub panel of @southMargin)
    JPanel upperSouthPanel, lowerSouthPanel;

    //Buttons
    JButton searchButton, //TODO implement later
            addStanderButton,
            editTableButton,
            dateSelectButton,
            createScheduleButton;

    //Do not change the order of the methods.
    MainPanel() {
        setCalendarPanel();
        setPanels();
        setButtons();
        activateListener();
        validate();
    }
    private void setCalendarPanel() {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(7, 6, 15, 15));
        calendarPanel.setBackground(ColorCode.WHITE_BACKGROUND);
        calendarPanel.setOpaque(true);

        addDatePanel();
    }

    private void addDatePanel() {
        for (int i = 0; i < MAX_BLOCK_COUNT; i++) {
            //add calendar date panel
        
        }
    }

    private void setPanels () {
        centerPanel = new JPanel();
        northMargin = new JPanel();
        eastMargin = new JPanel();
        southMargin = new JPanel();
        westMargin = new JPanel();

        centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
        centerPanel.add(calendarPanel, BorderLayout.CENTER);
        centerPanel.addMouseListener(this);
        centerPanel.setFocusable(true);

        southMargin = new JPanel();
        southMargin.setLayout(new GridLayout(2, 0));

        this.add(northMargin, BorderLayout.NORTH);
        this.add(eastMargin, BorderLayout.EAST);
        this.add(southMargin, BorderLayout.SOUTH);
        this.add(westMargin, BorderLayout.WEST);
        this.add(calendarPanel, BorderLayout.CENTER);
    }

    private void setButtons() {
        addStanderButton = new JButton("Add stander");
        addStanderButton.setFocusable(false);

        editTableButton = new JButton("Edit duty table");
        editTableButton.setFocusable(false);

        dateSelectButton = new JButton("Select date");
        dateSelectButton.setFocusable(false);

        //Icon not ready - @param (IconSets.labelIcon, SwingConstants.CENTER)
        JLabel spaceGap = new JLabel();
        spaceGap.setFocusable(false);

        createScheduleButton = new JButton("Create Schedule");
        createScheduleButton.setFocusable(false);
        createScheduleButton.addActionListener(this);

        addStanderButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        editTableButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        dateSelectButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        createScheduleButton.setBackground(ColorCode.BUTTON_BACKGROUND);

        southMargin.add(addStanderButton);
        southMargin.add(editTableButton);
        southMargin.add(dateSelectButton);
		southMargin.add(spaceGap);
        southMargin.add(createScheduleButton);

    }

    // TODO: refactor these
    public void activateListener() {
		addStanderButton.addActionListener(this);
        editTableButton.addActionListener(this);
        dateSelectButton.addActionListener(this);
    	createScheduleButton.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
		//Button actions
		if (obj.equals(addStanderButton)) {
            //TODO bring up stander window.
		} else if (obj.equals(editTableButton)) {
			//TODO bring up table edit window.
		} else if (obj.equals(dateSelectButton)) {
			//TODO bring up date select window.
		} else if (obj.equals(createScheduleButton)) {
            //TODO bring up create schedule window.
		}
    }
}

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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classfile.calendar.CalendarGenerator;
import classfile.calendar.CalendarList;
import classfile.calendar.DateSet;
import classfile.calendar.SingleCalendar;

import classfile.data.SharedDateValue;
import classfile.data.SharedUserValue;

@SuppressWarnings("serial")
class MainPanel extends JPanel implements ActionListener,
                                          MouseListener,
                                          MouseWheelListener {

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
            createScheduleButton,
            nextMonth,
            previousMonth;

    JLabel monthYearLabel = new JLabel("");

    CalendarGenerator calendarGenerator = new CalendarGenerator();
    CalendarList calendarList;

    //Do not change the order of the methods.
    MainPanel() {
        //load serialized calendar list data
        loadData();
        //Panel setup
        setCalendarPanel();
        setPanels();
        //Button setup
        setNorthMarginButtons();
        setSouthMarginButtons();
        //Listener activation
        activateCalendarListener();
        activateNorthMarginListener();
        activateSouthMarginListener();
        //Graphics
        setLabel();
        refreshGUI();
    }

    //@TODO - add data search function
    private void loadData() {
        //find existing data - not implemented yet

        //or generate new List
        calendarList = new CalendarList();
    }

    //Default date set to PC time - consider refactoring
    private void setCalendarPanel() {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(1, 1, 10, 10));
        addSingleCalendarPanels();
    }

    //consider refactoring
    private void addSingleCalendarPanels() {
        //de-referencing values
        int year = SharedDateValue.getTodayYear();
        int month = SharedDateValue.getTodayMonth();
        int date = SharedDateValue.getTodayDate();

        //check if this data is de-referenced from static values
        DateSet today = new DateSet(year, month, date);

        //Add that calendar to the calendar set
        SingleCalendar newCalendar = calendarList.addNewMonth(today);
        calendarPanel.add(newCalendar);

        //will be used lately - for exportation
        SharedUserValue.calendarList = calendarList;
    }

    private void getNextPanels() {
        SharedDateValue.shiftNextMonth();
        DateSet currentDateSet = SharedDateValue.getCurrentDateSet();
        //get calendar from the list
        calendarPanel.removeAll();
        SingleCalendar calendar = calendarList.addNewMonth(currentDateSet);
        calendarPanel.add(calendar);
        setLabel();
        refreshGUI();
    }

    private void getPreviousPanels() {
        SharedDateValue.shiftPreviousMonth();
        DateSet currentDateSet = SharedDateValue.getCurrentDateSet();
        //get calendar from the list
        calendarPanel.removeAll();
        SingleCalendar calendar = calendarList.addNewMonth(currentDateSet);
        calendarPanel.add(calendar);
        setLabel();
        refreshGUI();
    }

    private void setPanels () {
        this.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        northMargin = new JPanel();
        eastMargin = new JPanel();
        southMargin = new JPanel();
        westMargin = new JPanel();

        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.add(calendarPanel, BorderLayout.CENTER);
        centerPanel.addMouseListener(this);
        centerPanel.setFocusable(true);

        northMargin.setLayout(new GridLayout(1, 5));

        southMargin.setLayout(new GridLayout(1, 16));

        this.add(northMargin, BorderLayout.NORTH);
        //this.add(eastMargin, BorderLayout.EAST); - Not implemented yet
        this.add(southMargin, BorderLayout.SOUTH);
        //this.add(westMargin, BorderLayout.WEST);  - Not implemented yet
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void setNorthMarginButtons() {
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

        addStanderButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        editTableButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        dateSelectButton.setBackground(ColorCode.BUTTON_BACKGROUND);
        createScheduleButton.setBackground(ColorCode.BUTTON_BACKGROUND);

        northMargin.add(addStanderButton);
        northMargin.add(editTableButton);
        northMargin.add(dateSelectButton);
		northMargin.add(spaceGap);
        northMargin.add(createScheduleButton);
    }

    private void setSouthMarginButtons() {
        previousMonth = new JButton("<");
        previousMonth.setFocusable(false);
        previousMonth.setBackground(ColorCode.BUTTON_BACKGROUND);

        nextMonth = new JButton(">");
        nextMonth.setFocusable(false);
        nextMonth.setBackground(ColorCode.BUTTON_BACKGROUND);

        southMargin.add(new JLabel());
        southMargin.add(monthYearLabel);
        for (int i = 0; i < 10; i++) {
            southMargin.add(new JLabel());
        }
        southMargin.add(previousMonth);
        southMargin.add(new JLabel());
        southMargin.add(nextMonth);
        southMargin.add(new JLabel());
        southMargin.add(new JLabel());
    }

    private void setLabel() {
        DateSet dateSet = SharedDateValue.getCurrentDateSet();
        int year = dateSet.getYear();
        int month = dateSet.getMonth();
        String labelText = year + "/" + (month + 1);
        monthYearLabel.setText(labelText);
    }

    private void refreshGUI() {
        validate();
        repaint();
    }

    public void activateCalendarListener() {
        calendarPanel.addMouseListener(this);
        calendarPanel.addMouseWheelListener(this);
    }

    public void activateNorthMarginListener() {
		addStanderButton.addActionListener(this);
        editTableButton.addActionListener(this);
        dateSelectButton.addActionListener(this);
    	createScheduleButton.addActionListener(this);
    }

    public void activateSouthMarginListener() {
        previousMonth.addActionListener(this);
        nextMonth.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //button color change
        Object obj = e.getSource();
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        //Show next month when negative sign
        if (e.getUnitsToScroll() < 0) {
            getNextPanels();
        }


        //Show previous month when positive sign
        else if (e.getUnitsToScroll() > 0) {
            getPreviousPanels();
        }

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
		} else if (obj.equals(nextMonth)) {
            getNextPanels();
        } else if (obj.equals(previousMonth)) {
            getPreviousPanels();
        }
    }
}

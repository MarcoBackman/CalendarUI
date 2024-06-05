package ui;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

import calendar.CalendarGenerator;
import calendar.CalendarList;
import calendar.DateSet;
import calendar.SingleCalendar;

import constant.ColorCode;
import constant.IconSets;
import data.SharedDateValue;
import data.SharedUserValue;

class MainPanel extends JPanel implements ActionListener,
                                          MouseListener,
                                          MouseWheelListener {
    private final int INNER_BOTTOM_NAV_WIDTH = 300;
    private final int INNER_BOTTOM_NAV_HEIGHT = 40;
    private final Font MONTH_YEAR_DISPLAY_FONT = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);

    //External Panel
    JPanel centerPanel, topNavBar, bottomNavBar, westMargin, eastMargin;

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
        setBottomNavBarButtons();
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
        topNavBar = new JPanel();
        eastMargin = new JPanel();
        bottomNavBar = new JPanel();
        westMargin = new JPanel();

        centerPanel.setLayout(new GridLayout(1, 1));
        centerPanel.add(calendarPanel, BorderLayout.CENTER);
        centerPanel.addMouseListener(this);
        centerPanel.setFocusable(true);

        topNavBar.setLayout(new GridLayout(1, 5));

        bottomNavBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.add(topNavBar, BorderLayout.NORTH);
        //this.add(eastMargin, BorderLayout.EAST); - Not implemented yet
        this.add(bottomNavBar, BorderLayout.SOUTH);
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

        topNavBar.add(addStanderButton);
        topNavBar.add(editTableButton);
        topNavBar.add(dateSelectButton);
		topNavBar.add(spaceGap);
        topNavBar.add(createScheduleButton);
    }

    private JPanel initializeBottomLeftNavBarComponents() {
        FlowLayout buttonLayout = new FlowLayout(FlowLayout.CENTER);
        JPanel monthDisplayPanel = new JPanel();
        monthDisplayPanel.setPreferredSize(new Dimension(INNER_BOTTOM_NAV_WIDTH, INNER_BOTTOM_NAV_HEIGHT));
        monthDisplayPanel.setLayout(buttonLayout);
        monthDisplayPanel.add(monthYearLabel);
        return monthDisplayPanel;
    }

    private JPanel initializeBottomRightNavBarComponents() {
        previousMonth = new JButton("<");
        previousMonth.setFocusable(false);
        previousMonth.setBackground(ColorCode.BUTTON_BACKGROUND);

        nextMonth = new JButton(">");
        nextMonth.setFocusable(false);
        nextMonth.setBackground(ColorCode.BUTTON_BACKGROUND);

        GridLayout buttonLayout = new GridLayout(1, 5);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(INNER_BOTTOM_NAV_WIDTH, INNER_BOTTOM_NAV_HEIGHT));
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(previousMonth);
        buttonPanel.add(new JLabel());
        buttonPanel.add(nextMonth);
        return buttonPanel;
    }

    private void setBottomNavBarButtons() {
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(IconSets.getLabelIcon());

        //Left
        JPanel bottomLeftComponents = initializeBottomLeftNavBarComponents();
        bottomNavBar.add(bottomLeftComponents);

        //Center
        bottomNavBar.add(iconLabel);

        //Right
        JPanel bottomRightComponents = initializeBottomRightNavBarComponents();
        bottomNavBar.add(bottomRightComponents);
    }

    private void setLabel() {
        DateSet dateSet = SharedDateValue.getCurrentDateSet();
        int year = dateSet.getYear();
        int month = dateSet.getMonth();
        String labelText = year + "/" + (month + 1);
        monthYearLabel.setText(labelText);
        monthYearLabel.setFont(MONTH_YEAR_DISPLAY_FONT);
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

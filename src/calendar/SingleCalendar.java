package calendar;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import java.util.Hashtable;

import java.io.Serializable;

import constant.ColorCode;

public class SingleCalendar extends JPanel implements Serializable {

    final static int MAX_BLOCK_COUNT = 42;

    int year = 0;
    int month = 0;
    int counterIndex = 0; //Counter that indicates block index - 0 ~ 41;
    int startingIndex = 0; //Value that indicates where the first date starts
    boolean startingIndexAssigned = false;
    SingleCalendar nextCalendar, previousCalendar;

    static Hashtable<Integer, JPanel> dateBlockCollection
      = new Hashtable<Integer, JPanel> (MAX_BLOCK_COUNT);

    SingleCalendar(DateSet dateSet) {
        setLayout(new GridLayout(6, 7, 3, 3));
        setBackground(ColorCode.DARK_GRAY_BACKGROUND);
        setOpaque(true);
        this.year = dateSet.getYear();
        this.month = dateSet.getMonth();
    }

    //Add empty(inactive) block node to the table
    public void addEmptyBlock() {
        dateBlockCollection.put(counterIndex, new JPanel() {
            @Override
            public void setBackground (Color bg) {
                super.setBackground(ColorCode.EMPTY_PANEL);
                repaint();
            }
        });
        add(dateBlockCollection.get(counterIndex));
        counterIndex++;
    }

    //Add active day block node to the table
    public void addDateBlock(DateBlock dateBlock) {
        if (!startingIndexAssigned) {
            startingIndex = counterIndex;
            startingIndexAssigned = true;
        }
        dateBlockCollection.put(counterIndex, dateBlock);
        add((JPanel)dateBlock);
        counterIndex++;
    }

    /**
     * includes empty block and active blocks
     * @param index - block by index
     * @return JPanel block
     */
    public JPanel getDateBlockByIndex(Integer index) {
        return dateBlockCollection.get(index);
    }

    /**
     * only refers to active blocks
     * @param date - specific date block to retrieve
     * @return JPanel block with date assigned
     */
    public JPanel getDateBlockByDate(Integer date) {
        Integer dateIndex = (startingIndex + date) - 1;
        return dateBlockCollection.get(dateIndex);
    }

    public DateBlock replaceDateBlock(Integer newDate, DateBlock dateBlock) {
        Integer dateIndex = (startingIndex + newDate) - 1;
        DateBlock oldBlock = (DateBlock)dateBlockCollection.get(dateIndex);
        dateBlockCollection.put(dateIndex, dateBlock);
        return oldBlock;
    }

    public boolean hasNextCalendarLinked() {
        return this.nextCalendar != null;
    }

    public boolean hasPreviousCalendarLinked() {
        return this.previousCalendar != null;
    }

    public void setNextCalendar(SingleCalendar nextCalendar) {
        this.nextCalendar = nextCalendar;
    }

    public void setPreviousCalendar(SingleCalendar previousCalendar) {
        this.previousCalendar = previousCalendar;
    }
}

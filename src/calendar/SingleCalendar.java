package classfile.calendar;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Hashtable;

import java.io.Serializable;

import classfile.ui.ColorCode;

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
            Color bg = ColorCode.EMPTY_PANEL;
            @Override
            public void setBackground (Color bg) {
                Color oldBg = getBackground();
                super.setBackground(ColorCode.EMPTY_PANEL);
                //need explaination why I'm doing this
                if ((oldBg != null) ? !oldBg.equals(bg) :
                 (bg != null) && !bg.equals(oldBg)) {
                     repaint();
                 }
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
        dateBlockCollection.put(counterIndex, (JPanel)dateBlock);
        add((JPanel)dateBlock);
        counterIndex++;
    }

    /*
     * Do not get confused with two getters below
     *   - getDateBlockByIndex includes empty block and active blocks
     *   - getDateBlockByDate only refers to active blocks
     */

    public JPanel getDateBlockByIndex(Integer index) {
        return (JPanel)dateBlockCollection.get(index);
    }

    public JPanel getDateBlockByDate(Integer date) {
        Integer dateIndex = (Integer)((startingIndex + date) - 1);
        return (JPanel)dateBlockCollection.get(dateIndex);
    }

    //add((JPanel)dateBlock) method required
    public DateBlock replaceDateBlock(Integer newDate, DateBlock dateBlock) {
        //TODO: add type hanlder - refrain from using casting
        Integer dateIndex = (Integer)((startingIndex + newDate) - 1);
        DateBlock oldBlock = (DateBlock)dateBlockCollection.get(dateIndex);
        dateBlockCollection.put(dateIndex, (JPanel)dateBlock);
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

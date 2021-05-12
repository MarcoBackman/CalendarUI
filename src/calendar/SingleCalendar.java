package classfile.calendar;

import java.awt.Color;

import javax.swing.Janel;

import java.uitl.ArrayList;
import java.util.HashTable;

import java.io.Serializable;

public class SingleCalendar implements Serializable {

    final int MAX_DATE_COUNT = 42;

    int year = 0;
    int month = 0;
    int counterIndex = 0; //Counter that indicates block index - 0 ~ 41;
    int startingIndex = 0; //Value that indicates where the first date starts
    boolean startingIndexAssigned = false;

    static HashTable<Integer, JPanel> dateBlockCollection
      = new HashTable<Integer, JPanel> (MAX_DATE_COUNT);

    SingleCalendar(int year, int month) {
        this.year = year;
        this.month = month;
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
        counterIndex++;
    }

    //Add active day block node to the table
    public void addDateBlock(DateBlock block) {
        if (!startingIndexAssigned) {
            startingIndex = counterIndex;
            startingIndexAssigned = true;
        }
        dateBlockCollection.put(counterIndex, (JPanel)block);
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

    public DateBlock replaceDateBlock(Integer newDate, DateBlock newBlock) {
        //TODO: add type hanlder - refrain from using casting
        Integer dateIndex = (Integer)((startingIndex + newDate) - 1);
        DateBlock oldBlock = (DateBlock)dateBlockCollection.get(dateIndex);
        dateBlockCollection.put(dateIndex, (JPanel)newBlock);
        return oldBlock;
    }

}

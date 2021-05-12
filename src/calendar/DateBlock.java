package classfile.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import classfile.ui.ColorCode;

public class DateBlock extends JPanel implements MouseListener {

    private int year;
    private int month;
    private int date;
    private String day;

    private Color currentColor;

    private boolean isFirstDate;
    private boolean isLastDate;
    private boolean isFirstWeekend;
    private boolean isWeekend;

    private DateBlock previousDateBlock, nextDateBlock;
    private DateBlock previousWeekend, nextWeekend;

    private JLabel dateLabel;
    //private DetailPanel detailPanel; - detailPanel not implemented yet

    /* - drop handler not implemented yet
    private DragGestureRecognizer dgr;
    private DragGesturehandler dragGesturehandler;
    private DropTarget dropTarget;
    private DropHandler DropHandler;
    */

    private SingleCalendar associatedCalendar;

    DateBlock (SingleCalendar associatedCalendar, DateSet dateSet) {
        this.associatedCalendar = associatedCalendar;
        this.year = dateSet.year;
        this.month = dateSet.month;
        this.date = dateSet.date;
        this.day = dateSet.day;
        setDefaultLayout();
    }

    private setDefaultLayout() {
        setLayout(new BorderLayout());
        dateLabel = new JLabel("" + date + " (" + day + ")");
        //detailPanel = new DetailPanel(); - detailPanel not implemented yet

        this.addMouseListener(this); //seems something not right...review this

        if (isToday()) {
            currentColor = ColorCode.CURRENT_DATE_PANEL;
        } else {
            currentColor = ColorCode.WHITE_BACKGROUND;
        }
        setBackground(currentColor);
        detailPanel.setBackground(currentColor);

        add(dateLabel, BorderLayout.NORTH);
        //add(detailPanel, BorderLayout.CENTER);- detailPanel not implemented yet

        /* not implemented yet; consider refactoring DropTarget's constructer
        DropHandler = new DropHandler();
        dropTarget = new DropTarget(this,
                                    DndConstants.ACTION_MOVE,
                                    DropHandler,
                                    true);
        */
    }

    public boolean isToday() {
        if (year != SharedValue.current_year)
            return false;
        if (month != SharedValue.current_month)
            return false;
        if (date != SharedValue.current_date)
            return false;
        return true;
    }

    /*
     * #################################################
     * #                    Setters                    #
     * #################################################
     */

     public void setYear(int year) {
         this.year = year;
     }

     public void setMonth(int month) {
         this.month = month;
     }

     public void setDate(int date) {
         this.date = date;
     }

     public void setDay(String day) {
         this.day = day;
     }

     public setWeekday() {
         this.isWeekend = false;
     }

     public void setWeekend() {
         dateLabel.setForeground(ColorCode.RED_BACKGROUND);
         this.isWeekend = true;
     }

     public void setFirstDate() {
         if (!isLastDate) {
             isFirstDate = true;
         } else {
             //throw exception or error messages
         }
     }

     public void setFirstWeekend() {
         isFirstWeekend = true;
     }

     public void setPreviousBlock(DateBlock block) {
         this.previousDateBlock = block;
     }

     public void setNextBlock(DateBlock block) {
         this.nextDateBlock = block;
     }

     public void setPreviousWeekend(DateBlock block) {
         this.previousWeekend = block;
     }

     public void setNextWeekend(DateBlock block) {
         this.nextWeekend = node;
     }

     //Used for background color change
     // when hovering mouse cursor exits the area
     public void setOriginalColor() {
         //must sustain today block's color even after exiting the area
         if (isToday()) {
             currentColor = ColorCode.CURRENT_DATE_PANEL;
         } else {
             currentColor = ColorCode.WHITE_BACKGROUND;
         }
         setBackground(currentColor);
         detailPanel.setBackground(currentColor);
         this.validate();
         this.repaint();
         //detailPanel.validate(); - detailPanel not implemented yet
         //detailPanel.repaint(); - detailPanel not implemented yet
     }

     /*
      * #################################################
      * #                    Getters                    #
      * #################################################
      */

      public int getYear() {

      }

      public int getMonth() {

      }

      public int getDate() {

      }

      /* - StanderInfo Not implemented yet
      public StanderInfo getStander() {
          return detailPanel.getStander();
      }
      */

      public boolean isFirstDate() {
          return isFirstDate;
      }

      public boolean isLastDate() {
          return isLastDate;
      }

      public DetailPanel getDetailPanel() {
          return detailPanel;
      }

      public SingleCalendar getAssociatedCalendar() {
          return associatedCalendar;
      }

      public DateBlock getNextDateBlock() {
          return this.nextDateBlock;
      }

      public DateBlock getPreviousDateBlock() {
          return this.previousDateBlock;
      }

      public DateBlock getNextWeekendBlock() {
          return nextWeekend;
      }

      public DateBlock getPreviousWeekendBlock() {
          return previousWeekend;
      }

      /*
       * #################################################
       * #                    Events                     #
       * #################################################
       */

       @Override
       	public void mouseClicked(MouseEvent e) {

       	}

       	@Override
       	public void mousePressed(MouseEvent e) {

       	}

       	@Override
       	public void mouseReleased(MouseEvent e) {
            setBackground(currentColor);
            detailPanel.setBackground(ColorCode.DATE_PANEL_HOVER);
       	}

       	@Override
       	public void mouseEntered(MouseEvent e) {
       		setBackground(ColorCode.DATE_PANEL_HOVER);
            detailPanel.setBackground(ColorCode.DATE_PANEL_HOVER);
       	}

       	@Override
       	public void mouseExited(MouseEvent e) {
       		setBackground(currentColor);
            detailPanel.setBackground(currentColor);
       	}

}

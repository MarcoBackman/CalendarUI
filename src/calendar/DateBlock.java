package classfile.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import classfile.ui.ColorCode;

import classfile.data.SharedDateValue;

// Do not get confused with DateSet.java
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
    private DetailPanel detailPanel;

    /* - drop handler not implemented yet
    private DragGestureRecognizer dgr;
    private DragGesturehandler dragGesturehandler;
    private DropTarget dropTarget;
    private DropHandler DropHandler;
    */

    private SingleCalendar associatedCalendar;

    DateBlock (SingleCalendar associatedCalendar, DateSet dateSet) {
        this.associatedCalendar = associatedCalendar;
        this.year = dateSet.getYear();
        this.month = dateSet.getMonth();
        this.date = dateSet.getDate();
        this.day = dateSet.getDay();
        setDefaultLayout();
    }

    private void setDefaultLayout() {
        setLayout(new BorderLayout());
        dateLabel = new JLabel("" + date + " (" + day + ")");
        detailPanel = new DetailPanel();

        this.addMouseListener(this);

        if (isToday()) {
            currentColor = ColorCode.CURRENT_DATE_PANEL;
        } else {
            currentColor = ColorCode.WHITE_BACKGROUND;
        }
        setBackground(currentColor);
        detailPanel.setBackground(currentColor);

        add(dateLabel, BorderLayout.NORTH);
        add(detailPanel, BorderLayout.CENTER);

        /* not implemented yet; consider refactoring DropTarget's constructer
        DropHandler = new DropHandler();
        dropTarget = new DropTarget(this,
                                    DndConstants.ACTION_MOVE,
                                    DropHandler,
                                    true);
        */
    }

    public boolean isToday() {
        if (year != SharedDateValue.getTodayYear())
            return false;
        if (month != SharedDateValue.getTodayMonth())
            return false;
        if (date != SharedDateValue.getTodayDate())
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

     public void setWeekday() {
         dateLabel.setForeground(ColorCode.BLACK);
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

     public void setLastDate() {
         if (!isFirstDate) {
             isLastDate = true;
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
         this.nextWeekend = block;
     }

     /*
      * #################################################
      * #                    Getters                    #
      * #################################################
      */

      public int getYear() {
          return this.year;
      }

      public int getMonth() {
          return this.month;
      }

      public int getDate() {
          return this.date;
      }

      public String getDay() {
          return this.day;
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

      /* DetailPanel not implemented yet
      public DetailPanel getDetailPanel() {
          return detailPanel;
      }
      */

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
            validateAllComponents();
       	}

       	@Override
       	public void mouseEntered(MouseEvent e) {
            detailPanel.setBackground(ColorCode.DATE_PANEL_HOVER);
            validateAllComponents();
       	}

       	@Override
       	public void mouseExited(MouseEvent e) {
       		setBackground(currentColor);
            detailPanel.setBackground(currentColor);
            validateAllComponents();
       	}

        public void validateAllComponents(){
            this.validate();
            this.repaint();
            detailPanel.validate();
            detailPanel.repaint();
        }
}

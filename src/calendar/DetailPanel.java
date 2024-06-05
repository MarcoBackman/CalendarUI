package calendar;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DetailPanel extends JPanel
    implements ActionListener {

    private Color originalColor;
    //this line must be reviewed
    //private Object panelObject = new Object();

    DetailPanel () {
        setPanel();
    }

    private void setPanel() {
        this.setLayout(new GridLayout(1, 1));
        this.removeAll();
        //check Object instance
        //this.add((Component)panelObject);
    }

    private void setObjectColor () {

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

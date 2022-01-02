package classfile.calendar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DetailPanel extends JPanel
    implements ActionListener {

    DetailPanel () {
        setPanel();
    }

    private void setPanel() {
        this.setLayout(new GridLayout(1, 1));
        this.removeAll();
        //check Object instance
        //this.add((Component)panelObject);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

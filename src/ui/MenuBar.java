package classfile.ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

class MenuBar extends JMenuBar implements ActionListener,
                                          MenuKeyListener {

    public static JMenu fileTab, editTab, searchTab, helpTab;
    public static JMenuItem open, exit,       //File tab components
                            addStander,       //Edit tab components
                            help, version;    //Help tab components
    private Insets insets = new Insets(10, 5, 10, 5);

    public MenuBar() {
        setupMenuBar();
        activateMenuListener();
    }

    private void setupMenuBar() {
        this.setBorderPainted(true);
        this.setBackground(ColorCode.FILE_TAB_BACKGROUND);
        this.setOpaque(true);
        this.setMargin(insets);

        addFileTab();
        addEditTab();
        addSearchTab();
        addHelpTab();
    }

    private void addFileTab() {
        fileTab = new JMenu("File");
        fileTab.setOpaque(true);
        fileTab.setBackground(ColorCode.BUTTON_BACKGROUND);
        addFileMenuItems();
        this.add(fileTab);
    }

    private void addFileMenuItems() {
        open = new JMenuItem("Open");
        fileTab.add(open);

        exit = new JMenuItem("Exit");
        fileTab.add(exit);
    }

    private void addEditTab() {
        editTab = new JMenu("Edit");
        editTab.setBackground(ColorCode.BUTTON_BACKGROUND);
        editTab.setOpaque(true);
        addEditMenuItems();
        this.add(editTab);
    }

    private void addEditMenuItems() {
        addStander = new JMenuItem("Add Stander(s)");
        editTab.add(addStander);
    }

    private void addSearchTab() {
        searchTab = new JMenu("Search");
        searchTab.setBackground(ColorCode.BUTTON_BACKGROUND);
        searchTab.setOpaque(true);
        addSearchMenuItems();
        this.add(searchTab);
    }

    private void addSearchMenuItems() {
        // TODO menu items to be added
    }

    private void addHelpTab() {
        helpTab = new JMenu("Help");
        helpTab.setBackground(ColorCode.BUTTON_BACKGROUND);
        helpTab.setOpaque(true);
        addHelpMenuItems();
        this.add(helpTab);

    }

    private void addHelpMenuItems() {
        help = new JMenuItem("About Duty Assigner");
        helpTab.add(help);

        version = new JMenuItem("Program Version");
        helpTab.add(version);
    }

    public void activateMenuListener() {
        open.addActionListener(this);
        exit.addActionListener(this);
        addStander.addActionListener(this);
        help.addActionListener(this);
        version.addActionListener(this);

        open.addMenuKeyListener(this);
        exit.addMenuKeyListener(this);
        addStander.addMenuKeyListener(this);
        help.addMenuKeyListener(this);
        version.addMenuKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        //menu bar objects
        //TODO refactor getItem index name to non-numeric value
        if (object.equals(fileTab.getItem(0))) { //open
        } else if (object.equals(fileTab.getItem(1))) { //exit
            System.exit(0);
        }
    }

    @Override
    public void menuKeyTyped(MenuKeyEvent e) {

    }

    @Override
    public void menuKeyPressed(MenuKeyEvent e) {

    }

    @Override
    public void menuKeyReleased(MenuKeyEvent e) {

    }
}
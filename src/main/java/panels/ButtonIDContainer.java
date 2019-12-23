package panels;

import javax.swing.*;
import java.awt.*;

/*
The panels.ButtonIDContainer class is a JPanel that is used to set the layout of
the + and - buttons (in panels.ButtonPanel), and the ant buttons (in panels.IDPanel)
*/

public class ButtonIDContainer extends JPanel {
    private IDPanel idPanel;

    public ButtonIDContainer(){
        idPanel = (IDPanel) ButtonPanel.getIDPanel();
        setSize(250,300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(new ButtonPanel(),c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty=6;
        c.gridx = 0;
        c.gridy = 1;
        add(idPanel, c);
    }
}
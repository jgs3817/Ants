package panels;

import javax.swing.*;
import java.awt.*;

/*
The panels.ButtonIDContainer class is a JPanel that is used to set the layout of
the + and - buttons (in panels.ButtonPanel), and the ant buttons (in panels.IDPanel)
*/

public class ButtonIDContainer extends JPanel {

    public ButtonIDContainer(){
        IDPanel idPanel = (IDPanel) ButtonPanel.getIDPanel();
        setSize(250,300);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;

        setGridBagLayout(c,0,0,1,1);
        add(new ButtonPanel(),c);

        setGridBagLayout(c,0,1,1,6);
        add(idPanel, c);
    }

    private void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }
}
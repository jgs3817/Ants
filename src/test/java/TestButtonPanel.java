import static org.junit.Assert.*;
import javax.swing.*;
import junit.framework.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import abbot.tester.*;

public class TestButtonPanel {
    ComponentTester tester;

    public void testAntButton(){
        int count=1;
        JButton btn1 = new JButton(String.valueOf(count));
        count++;
        JButton btn2 = new JButton(String.valueOf(count));
        assertEquals(btn1.getText(), "1");
        assertEquals(btn2.getText(), "2");
    }

    public void testAddMinusButton(){
        //Testing for add and minus buttons
        //When add button is clicked, check that a new button is created in another panel
        JPanel panel0 = new JPanel();
        JPanel panel1 = new JPanel();
        JButton btn3 = new JButton("3");
        JButton addButton = new JButton("Add button 3 to panel 1");
        final String[] gotClick = new String[1];
        gotClick[0] = null;

        ActionListener add = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                panel1.add(btn3);
                gotClick[0] = ev.getActionCommand();
            }
        };

        addButton.add((Component) add);
        panel0.add(addButton);
        tester.actionClick(addButton);
        assertEquals("Action failed", addButton, gotClick[0]); // Add button is clicked
        assertEquals("Button 3 not inside panel 1",btn3.getParent(),panel1);

        //When minus button is clicked, check that the button is removed from this panel
        JButton minusBtn = new JButton("Remove button 3 from panel 1");
        gotClick[0] = null;
        ActionListener minus = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                panel1.remove(btn3);
                gotClick[0] = ae.getActionCommand();
            }
        };
        minusBtn.add((Component) minus);
        tester.actionClick(minusBtn);
        assertEquals("Action failed", minusBtn, gotClick[0]); // Minus button is clicked
        assertEquals("Button 3 not inside panel 1",panel1.getComponentCount(),0);
    }


}
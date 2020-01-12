import javax.swing.*;
import java.awt.*;
import abbot.tester.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static abbot.tester.ComponentTester.*;
import static org.junit.Assert.*;

public class TestFBPanel {
    int frameID;
    ComponentTester tester;

    public void testBackButton(){
        //When clicked, check that we go back one frame
        frameID = 0;
        JButton backBtn = new JButton("Back");
        ActionListener back = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                frameID--;
            }
        };
        backBtn.add((Component) back);
        tester.actionClick(backBtn); //click button ?
        assertEquals(frameID, -1);
    }

    public void testForwardButton(){
        //When clicked, check that we go forward one frame
        frameID = 0;
        /*JButton fwdBtn = new JButton("Forward");
        ActionListener forward = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                frameID++;
            }
        };
        fwdBtn.add((Component) forward);
        tester.actionClick(fwdBtn);
        */
        assertEquals("not equal",frameID, 23);
    }

}
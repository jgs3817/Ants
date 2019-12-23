import org.junit.Assert;

import javax.swing.*;

public class TestButtonPanel {

    public void testAntButton(){
        int count=1;
        JButton btn1 = new JButton(String.valueOf(count));
        count++;
        JButton btn2 = new JButton(String.valueOf(count));
        Assert.assertEquals(btn1.getText(), "1");
        Assert.assertEquals(btn2.getText(), "2");
    }

    public void testAddButton(){
        //When add button is clicked, check that a new button is created in another panel
    }

    public void testMinusButton(){
        //When minus button is clicked, check that the button is removed from this panel
    }

    public void testGetLastButton(){
        //check that the method getLastButton() works
    }

}

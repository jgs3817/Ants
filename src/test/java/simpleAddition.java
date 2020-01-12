import static org.junit.Assert.*;
import junit.framework.*;

public class simpleAddition {
    public void addition(){
        int a = 1;
        int b = 2;
        int c = a+b;
        Assert.assertEquals("Incorrect",c,3);
    }

}

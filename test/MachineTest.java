
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Observable;

public class MachineTest {

    public MachineTest() {
    }

    @Test
    public void testUpdate1() { // Tests if the methods isBroken() and update() work properly
        Machine m = new Machine();
        m.setBroken();
        assertEquals(true, m.isBroken());
        m.repair();
        assertEquals(false, m.isBroken());
    }

    @Test
    public void testUpdate2() {       
        Machine m1 = new Machine();
        m1.setBroken();
        
        Machine m2 = new Machine();
        m2.setBroken();
        
        Machine m3 = new Machine();
        m3.repair();

        assertEquals(true, m1.isBroken());
        m1.repair();
        assertEquals(false, m1.isBroken());
        m2.repair();
        assertEquals(false, m2.isBroken());
        assertEquals(false, m3.isBroken());
    }

}

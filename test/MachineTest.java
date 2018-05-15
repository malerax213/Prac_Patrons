
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Observable;

public class MachineTest {

    public MachineTest() {
    }

    @Test
    public void testUpdate1() { // Tests if the methods isBroken() and update() work properly
        MachineComposite mc = new MachineComposite();
        Machine m = new Machine();
        m.setBroken();
        
        mc.addComponent(m);
        assertEquals(true, mc.isBroken());
        m.repair();
        mc.update(m, 0);
        assertEquals(false, mc.isBroken());
    }

    @Test
    public void testUpdate2() {       
        MachineComposite mc = new MachineComposite();
        Machine m1 = new Machine();
        m1.setBroken();
        
        Machine m2 = new Machine();
        m2.setBroken();
        
        Machine m3 = new Machine();
        m3.repair();
        
        mc.addComponent(m1);
        mc.addComponent(m2);
        mc.addComponent(m3);
        // There are 2 broken components
        assertEquals(true, mc.isBroken());
        // The first machine is being repaired
        m1.repair();
        // There is still a broken component
        assertEquals(true, mc.isBroken());
        // The second machine is being repaired
        m2.repair();
        mc.update(m2, 0);
        // Now there shouldn't be any broken component
        assertEquals(false, mc.isBroken());
    }

}

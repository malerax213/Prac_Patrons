
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Observable;

public class MachineTest {

    public MachineTest() {
    }

    @Test
    public void testUpdate1() { // Tests if the methods isBroken() and update() work properly
        MachineComposite mc = new MachineComposite();

        mc.addComponent(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return true;
            }
        });
        assertEquals(true, mc.isBroken());
        mc.update(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return false;
            }
        }, 0);
        assertEquals(false, mc.isBroken());
    }

    @Test
    public void testUpdate2() {
        MachineComposite mc = new MachineComposite();

        mc.addComponent(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return false;
            }
        });
        mc.addComponent(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return true;
            }
        });
        mc.addComponent(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return true;
            }
        });
        // There are 2 broken components
        assertEquals(true, mc.isBroken());
        // The update method handles the subComponents
        mc.update(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return false;
            }
        }, 0);
        // There is still a broken component
        assertEquals(true, mc.isBroken());
        mc.update(new MachineComponent() {
            @Override
            public boolean isBroken() {
                return false;
            }
        }, 0);
        // Now there shouldn't be any broken component
        assertEquals(false, mc.isBroken());
    }

}

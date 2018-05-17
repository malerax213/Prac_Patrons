
import org.junit.Test;
import static org.junit.Assert.*;

public class MachineCompositeTest {

    // Check if notifies after break MachineComposite
    @Test
    public void Notify_CompositeAfterBreak() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();

        mc.addObserver(os);
        mc.setBroken();

        assertEquals(true, mc.isBroken());
        assertEquals(true, os.alerted);
    }

    // Check if notifies after repair a broken MachineComposite
    @Test
    public void Notify_CompositeAfterBreakRepair() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();

        mc.setBroken();
        assertEquals(true, mc.isBroken());
        mc.addObserver(os);
        mc.repair();

        assertEquals(false, mc.isBroken());
        assertEquals(true, os.alerted);
    }

    // Repairing an already working machine should not notify
    @Test
    public void NotNotify_CompositeAlreadyRepair() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();

        mc.addObserver(os);
        mc.repair();

        assertEquals(false, mc.isBroken());
        assertEquals(false, os.alerted);
    }

    // Breaking Component of MachineComposite should break that MachineComponent and notify
    @Test
    public void Notify_ComponentAfterBreak() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m = new Machine();

        mc.addObserver(os);
        mc.addComponent(m);
        m.setBroken();

        mc.update(m, m);

        assertEquals(true, mc.isBroken());
        assertEquals(true, os.alerted);
    }

     //A MachineComposite with a Broken Machine, should become working after repair that Machine
    @Test
    public void Notify_ComponentAfterBreakRepair() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m = new Machine();

        mc.addObserver(os);
        mc.addComponent(m);
        m.setBroken();
        mc.update(m, m);
        m.repair();
        mc.update(m, m);

        assertEquals(false, mc.isBroken());
        assertEquals(true, os.alerted);
    }

    // Repairing an already working Machine, should no change the state of the Composite
    @Test
    public void NotNotify_ComponentAlreadyRepair() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m = new Machine();

        mc.addObserver(os);
        mc.addComponent(m);
        m.repair();

        assertEquals(false, mc.isBroken());
        assertEquals(false, os.alerted);
    }

    //Composite with multiple Machine should notify when one of them break, and become Broken
    @Test
    public void Notify_MultiComponentAfterBroken() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m1 = new Machine();
        Machine m2 = new Machine();

        mc.addObserver(os);
        mc.addComponent(m1);
        mc.addComponent(m2);
        m1.setBroken();

        assertEquals(true, mc.isBroken());
        assertEquals(true, os.alerted);
    }

    //Composite with multiple Machine should notify when repaired one of the Machines and become repaired
    @Test
    public void Notify_MultiComponentAfterRepair() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m1 = new Machine();
        Machine m2 = new Machine();

        m1.setBroken();
        mc.addObserver(os);
        mc.addComponent(m1);
        assertEquals(true, mc.isBroken());
        mc.addComponent(m2);
        m1.repair();

        assertEquals(false, mc.isBroken());
        assertEquals(true, os.alerted);
    }

    // Breaking a Machine in an already broken Composite should not notify
    @Test
    public void NotNotify_ComponentAlreadyBroken() {
        MachineComposite mc = new MachineComposite();
        ObserverSample os = new ObserverSample();
        Machine m1 = new Machine();
        
        mc.setBroken();

        mc.addObserver(os);
        mc.addComponent(m1);

        m1.setBroken();
        mc.update(m1, m1);
        
        assertEquals(true, m1.isBroken());
        assertEquals(true, mc.isBroken());
        assertEquals(false, os.alerted);
    }

}

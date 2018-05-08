
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MachineComposite extends Observable implements Observer {

    private List<MachineComponent> components = new ArrayList<>();
    private int brokenComponents = 0;
    private boolean broken = false;

    public void addComponent(MachineComponent mc) {
        components.add(mc);
        mc.addObserver(this);
        if (mc.isBroken()) {
            brokenComponents += 1;
            if (!broken && brokenComponents == 1) {
                notifyChanges();
            }
        }
    }

    public boolean isBroken() {
        if (broken) {
            return true;
        }
        for (MachineComponent mc : components) {
            if (mc.isBroken()) {
                return true;
            }
        }
        return false;
    }

    public void setBroken() {
        boolean wasBroken = isBroken();
        broken = true;
        if (!wasBroken) {
            notifyChanges();
        }
    }

    public void repair() {
        boolean wasBroken = isBroken();
        broken = false;
        if (!wasBroken) {
            notifyChanges();
        }
    }

    @Override
    public void update(Observable obs, Object arg) {
        MachineComponent mc = (MachineComponent) obs;
        if (mc.isBroken()) {
            brokenSubcomponent(mc);
        } else {
            repairedSubcomponent(mc);
        }
    }

    private void brokenSubcomponent(MachineComponent mc) {
        boolean wasBroken = mc.isBroken();
        brokenComponents += 1;
        if (!wasBroken) {
            notifyChanges();
        }
    }

    private void repairedSubcomponent(MachineComponent mc) {
        brokenComponents -= 1;
        if (!mc.isBroken()) {
            notifyChanges();
        }
    }

    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }
}

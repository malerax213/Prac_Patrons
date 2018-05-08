
public class Machine extends MachineComponent {

    private boolean broken = false;

    @Override
    public void setBroken() {
        boolean wasBroken = broken;
        broken = true;
        if (!wasBroken) {
            notifyChanges();
        }
    }

    @Override
    public void repair() {
        boolean wasBroken = broken;
        broken = false;
        if (wasBroken) {
            notifyChanges();
        }
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    private void notifyChanges() {
        setChanged();
        notifyObservers();
    }
}

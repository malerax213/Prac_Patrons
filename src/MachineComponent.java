
import java.util.Observable;


public abstract class MachineComponent extends Observable{

    protected boolean broken = false;

    public void setBroken() {
        broken = isBroken();	
    }

    public void repair() {
        broken = isBroken();
    }

    public abstract boolean isBroken();
}

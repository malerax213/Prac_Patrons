
import java.util.Observable;
import java.util.Observer;

public class ObserverSample implements Observer {
    protected boolean alerted = false;

    @Override
    public void update(Observable o, Object arg) {
        alerted = true;
    }

}

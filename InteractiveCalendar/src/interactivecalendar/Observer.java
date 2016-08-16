package hw4;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Arjun
 */
public interface Observer {
    
    public void stateChanged(GregorianCalendar g, ArrayList<EventModel> list);
}
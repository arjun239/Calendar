package hw4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arjun
 */
public class Model_EventsDay {
    private ArrayList<EventModel> eventsDay; 
    private ArrayList<Observer> listeners;
    private GregorianCalendar date;
    
    public Model_EventsDay() 
    {
        date= new GregorianCalendar();
        listeners=new ArrayList<Observer>();
        try {
            PopulateAllEvents.PopulateAllEvents();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model_EventsDay.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        eventsDay=PopulateAllEvents.getEventsForDay(new GregorianCalendar());
    }
    public GregorianCalendar getDate()
    {
        return date;
    }
    public void UpdateModel(final GregorianCalendar g)
    { 
        eventsDay=PopulateAllEvents.getEventsForDay(g);
        this.date=g;
        for(Observer o:listeners)
        {
            o.stateChanged(date, eventsDay);
        }
     
      
    }
    public void CreateEvent(String title, GregorianCalendar g , GregorianCalendar endTime)
    {
    	if(g.before(endTime)){
    		EventModel em= new EventModel(title, g, endTime);
            ArrayList<EventModel> list = PopulateAllEvents.getEventsForDay(g);
            for(EventModel e: list){
            	if(em.isTimingClash(e)){
            		System.out.println("Cannot create event. Time clash with another event !!");
            		return;
            	}
            }
            PopulateAllEvents.addEvent(em);
            UpdateModel(g);
    	}
    	else
    		System.out.println("Cannot create event. start time > end time !!");
    }
    public void attach(Observer o)
    {
        listeners.add(o);
    
    }
    public void quit()
    {
        try {     
            PopulateAllEvents.quit();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model_EventsDay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model_EventsDay.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}

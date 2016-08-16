package hw4;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Arjun
 */
public class EventModel implements Comparable
{
    private GregorianCalendar EventDate, endTime;
    
    private String title;
    
    public EventModel(String x, GregorianCalendar g, GregorianCalendar endTime)
    {
        EventDate=g;
        title=x;
        this.endTime=endTime;
    }
    public GregorianCalendar getDate()
    {
        return EventDate;
    }
    
	public String getTitle()
	{
	    return this.title;
	}
	public GregorianCalendar getEndTime()
	{
	    return endTime;
	}
    @Override
    public int compareTo(Object t) 
    {
        EventModel x= (EventModel)t;
        GregorianCalendar y= x.getDate();
        if(this.EventDate.before(y))
        	return -1;
        if(EventDate.after(y))
        	return 1;
        return 0;
    }
    
    @Override
    public String toString(){
        String x = "";
        x+=title+", ";
        x+="start: "+Integer.toString(EventDate.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(EventDate.get(Calendar.MINUTE))+", ";
        x+="end: "+Integer.toString(endTime.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(endTime.get(Calendar.MINUTE))+"\n";
        return x;
    }
    
    public boolean isTimingClash(EventModel checkDate){
    	if(getDate().equals(checkDate.getDate()))
    		return true;
    	
    	if(getDate().before(checkDate.getDate())){    		
    		if(getEndTime().after(checkDate.getDate()) || getEndTime().equals(checkDate.getDate()))
    			return true;
    	}
    	
    	if(getDate().after(checkDate.getDate())){
    		if(getDate().before(checkDate.getEndTime()) || getDate().equals(checkDate.getEndTime()))
    			return true;
    	}
    	return false;
    }
    
}
 
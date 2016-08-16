package hw4;

import java.awt.Button;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;

class Controller {

    private Model_EventsDay model;
    public Controller(Model_EventsDay m)
    {
        model=m;
    }
        
    public void decrementDay()
    {
        GregorianCalendar g= model.getDate();
        g.add(GregorianCalendar.DAY_OF_MONTH,-1);
        //System.out.println("decrement function\n"+g.get(GregorianCalendar.MONTH)+"/"+g.get(GregorianCalendar.DAY_OF_MONTH));
        model.UpdateModel(g);
    }
    public void incrementDay()
    {
        GregorianCalendar g= model.getDate();
        g.add(GregorianCalendar.DAY_OF_MONTH,1);
        //System.out.println("increment function\n"+g.get(GregorianCalendar.MONTH)+"/"+g.get(GregorianCalendar.DAY_OF_MONTH));
        model.UpdateModel(g);

    }
    public void setDate(JButton x)
    {
        GregorianCalendar g= model.getDate();
        if(!x.getText().equalsIgnoreCase(""))
        {
            g.set(Calendar.DAY_OF_MONTH,Integer.parseInt(x.getText()));
        }
        model.UpdateModel(g);
    }
    public void CreateEvent(GregorianCalendar g)
    {
        new CreateFrame(g, model);
        
    }
    public void quit()
    {
        model.quit();
    }
}
package hw4;

public class GUIMain
{
	public static void main(String[] args)
	{
            Model_EventsDay model= new Model_EventsDay();
            Controller c = new Controller(model);
            View v = new View(c);
            model.attach(v);
            v.buildGUI();
	}
}
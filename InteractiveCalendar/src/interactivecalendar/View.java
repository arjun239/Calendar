package hw4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View implements Observer{
	private Controller c;
	private JLabel leftBottomPanel_TopJLabel, rightBottomPanel_JLabel;
	private JButton[][] button2DArray;
	JTextArea eventsTextArea;
        private Button leftButton, rightButton, quitButton, createButton;
        GregorianCalendar currentDate;
       JFrame parentFrame;
       
	public View(final Controller c){
		this.c=c;
		button2DArray=new JButton[6][7];
                JTextArea textArea = new JTextArea();
                leftButton = new Button("<");
                rightButton = new Button(">");
                quitButton = new Button("Quit");
                eventsTextArea = new JTextArea();
                createButton= new Button("Create");
                leftBottomPanel_TopJLabel = new JLabel();
                rightBottomPanel_JLabel= new JLabel();
                this.currentDate=new GregorianCalendar();
                parentFrame=new JFrame();
                 quitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        c.quit();
                        parentFrame.dispose();
                    }
                });
                leftButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        c.decrementDay(); 
                    }
                });
                  rightButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        c.incrementDay(); 
                    }
                });
                   createButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        c.CreateEvent(currentDate);
                    }
                });
                  
                
            
        
	}
	
	public void buildGUI(){
		parentFrame.setSize(800, 500);
		parentFrame.setLayout(new BorderLayout());
		
		/*top panel*/
		JPanel panelTop = buildTopPanel();
		
		/*panel bottom*/
		JPanel panelBottom = buildBottomPanel();
		
		parentFrame.add(panelTop, BorderLayout.NORTH);
		parentFrame.add(panelBottom, BorderLayout.CENTER);
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		parentFrame.pack();
		parentFrame.setVisible(true);
	}
	
	private JPanel buildTopPanel(){
		
		JPanel panelTop = new JPanel();
		panelTop.setSize(800, 150);
		panelTop.add(leftButton);
		panelTop.add(rightButton);
		panelTop.add(quitButton);
		return panelTop;
		
	}
	
	private JPanel buildBottomPanel(){

		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(new GridLayout(1,2));
		
		/*Left bottom panel*/
		JPanel panelLeft = buildCalendarPanel(new GregorianCalendar());
		
		/*bottom right panel*/
		JPanel panelRight = buildTextViewPanel();
    
		/*add bottom two panels to panelBottom*/
		panelBottom.add(panelLeft);
		panelBottom.add(panelRight);
		return panelBottom;
	}
	
	private JPanel buildCalendarPanel(GregorianCalendar g){

		JPanel panelLeft = new JPanel();
		panelLeft.setSize(400,350);
		panelLeft.setLayout(new BorderLayout());
		
		String days[] = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		JPanel panelLeftNorth = new JPanel();
		panelLeftNorth.setLayout(new GridLayout(2,1));
		panelLeftNorth.add(createButton);
		
		panelLeftNorth.add(leftBottomPanel_TopJLabel);
		panelLeft.add(panelLeftNorth, BorderLayout.NORTH);
		
		JPanel calendar = new JPanel();
		calendar.setLayout(new GridLayout(7,7));
		for(int i=0;i<7;i++){
			calendar.add(new JLabel(days[i]));
		}
		for( int i=0;i<6;i++){
			for( int j=0;j<7;j++){
				button2DArray[i][j] = new JButton();
                                final JButton x = button2DArray[i][j];
                               x.addActionListener(new ActionListener() {
                                       @Override
                                        public void actionPerformed(ActionEvent ae)
                                         {
                                              c.setDate(x); 
                                         }
                                            });
				calendar.add(button2DArray[i][j]);
                       }
		}
		this.stateChanged(new GregorianCalendar(), PopulateAllEvents.getEventsForDay(new GregorianCalendar()));
		panelLeft.add(calendar, BorderLayout.CENTER);
		
		return panelLeft;
	}
	
	private JPanel buildTextViewPanel(){
		JPanel panelRight = new JPanel();
		panelRight.setSize(400, 350);
		panelRight.setLayout(new BorderLayout());
		panelRight.add(rightBottomPanel_JLabel, BorderLayout.NORTH);
		eventsTextArea.setSize(400, 300);
		eventsTextArea.setBackground(Color.lightGray);
		panelRight.add(new JScrollPane(eventsTextArea),BorderLayout.CENTER);
		return panelRight;
	}

        public void stateChanged(final GregorianCalendar gc, final ArrayList<EventModel> list)
        {
            currentDate=gc;
            int skipDays=0;
            String [] days = {"Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday"};
            
            rightBottomPanel_JLabel.setText(days[gc.get(GregorianCalendar.DAY_OF_WEEK)-1]+" "+Integer.toString(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.DAY_OF_MONTH));
            
            eventsTextArea.setText("");
            for(EventModel e: list)
            {
                eventsTextArea.append(e.toString());
            }
            
            GregorianCalendar gxx = new GregorianCalendar(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DAY_OF_MONTH));
            gxx.set(Calendar.DAY_OF_MONTH,1);
            skipDays = gxx.get(GregorianCalendar.DAY_OF_WEEK)-1;
            int daysInMonth = gxx.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            int counter=0;

            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


            leftBottomPanel_TopJLabel.setText(months[gc.get(GregorianCalendar.MONTH)]+" "+Integer.toString(gc.get(GregorianCalendar.YEAR)));
            for(int i=0;i<6;i++){
                    for(int j=0;j<7;j++){
                            if(skipDays>0){
                                    skipDays--;
                                     button2DArray[i][j].setText("");
                                     button2DArray[i][j].setBackground(Color.LIGHT_GRAY);
                                    continue;
                            }
                            if(counter<=daysInMonth){
                                    counter++;
                                    button2DArray[i][j].setText(Integer.toString(counter));
                                   button2DArray[i][j].setBackground(Color.LIGHT_GRAY);
                                    if(counter==gc.get(Calendar.DAY_OF_MONTH))
                                    {
                                        button2DArray[i][j].setBackground(Color.BLUE);
                                    }
                            }
                            if(counter>daysInMonth){
                                button2DArray[i][j].setText("");
                                button2DArray[i][j].setBackground(Color.LIGHT_GRAY);
                            }

                    }
            }
                        	
        }
}
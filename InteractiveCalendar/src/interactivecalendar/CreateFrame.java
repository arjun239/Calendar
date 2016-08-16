package hw4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *
 * @author Arjun
 */
public class CreateFrame 
{
    private JTextField  startTime, endTime;
   private JTextArea title;
    private JLabel dateLabel;
    private JButton save;
   private Model_EventsDay m;
    
    public CreateFrame(final GregorianCalendar g, final Model_EventsDay m)
    {
        this.m=m;
        final  JFrame myframe= new JFrame();
        myframe.setLayout(new GridLayout(2, 1));
        JPanel topPanel= new JPanel();
        JPanel bottomPanel= new JPanel();
        title= new JTextArea(1,20);
        topPanel.add(title);
        
        myframe.add(topPanel);
        
        dateLabel= new JLabel();
        bottomPanel.setLayout(new GridLayout(1, 4));
        bottomPanel.add(dateLabel);
        startTime= new JTextField();
        endTime= new JTextField();
        save= new JButton("Save");
        bottomPanel.add(startTime);
        bottomPanel.add(endTime);
        bottomPanel.add(save);
        
        dateLabel.setText(Integer.toString(g.get(Calendar.MONTH)+1)
                +"/"+Integer.toString(g.get(Calendar.DAY_OF_MONTH))
                +"/"+Integer.toString(g.get(Calendar.YEAR)));
        
        myframe.add(bottomPanel);
        myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
       myframe.pack();
       myframe.setVisible(true);
       save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
               String x= startTime.getText();
               GregorianCalendar newG = new GregorianCalendar(g.get(Calendar.YEAR),
            		   g.get(Calendar.MONTH),g.get(Calendar.DAY_OF_MONTH),
            		   Integer.parseInt(x.substring(0,2)), Integer.parseInt(x.substring(2, 4)),0);
                x=endTime.getText();
                GregorianCalendar endTime = new GregorianCalendar(g.get(Calendar.YEAR),
                		g.get(Calendar.MONTH),g.get(Calendar.DAY_OF_MONTH),
                		Integer.parseInt(x.substring(0, 2)), Integer.parseInt(x.substring(2, 4)),0);
                
                m.CreateEvent(title.getText(), newG, endTime);
               myframe.dispose();
            }
        });
        
    }
    
}
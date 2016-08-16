package hw4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;  
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Arjun
 */
public class PopulateAllEvents {
    
     private static ArrayList<EventModel> allEvents= new ArrayList<EventModel>();
     
    
     public static void PopulateAllEvents() throws FileNotFoundException
        {
            File f=new File("Events.txt");
            if(f.exists()){
            	Scanner scan = new Scanner(f);
                String inputline;
                
                while(scan.hasNextLine())
                {
                	ArrayList<String> Splitnames= new ArrayList<String>();
                    inputline=scan.nextLine();
                   for(String x: inputline.split(", "))
                   {
                       Splitnames.add(x);
                   }
                   
                  GregorianCalendar xx= new GregorianCalendar(Integer.parseInt(Splitnames.get(1)),
                          Integer.parseInt(Splitnames.get(2)),Integer.parseInt(Splitnames.get(3)),
                          Integer.parseInt(Splitnames.get(4)),
                          Integer.parseInt(Splitnames.get(5)));
                  GregorianCalendar end= new GregorianCalendar(Integer.parseInt(Splitnames.get(1)),
                          Integer.parseInt(Splitnames.get(2)),Integer.parseInt(Splitnames.get(3)),
                          Integer.parseInt(Splitnames.get(6)),
                          Integer.parseInt(Splitnames.get(7)));
                  
                  EventModel x= new EventModel(Splitnames.get(0),xx, end);
                  allEvents.add(x);
                }
            }
            else
                System.out.println("Save file does not exist");
            
        }
     public static ArrayList<EventModel> getEventsForDay(GregorianCalendar g)
     {
         ArrayList<EventModel> returnList = new ArrayList<EventModel>();
         for(EventModel e: allEvents)
         {
        	 if(e.getDate().get(Calendar.YEAR)==g.get(Calendar.YEAR) && 
        			 e.getDate().get(Calendar.MONTH)==g.get(Calendar.MONTH) &&
        			 e.getDate().get(Calendar.DAY_OF_MONTH)==g.get(Calendar.DAY_OF_MONTH)){
        		 
        		 returnList.add(e); 
        		 System.out.println(e.getTitle()+": "+e.getDate().get(Calendar.MONTH)+"/"+e.getDate().get(Calendar.DAY_OF_MONTH)+"/"+e.getDate().get(Calendar.YEAR));
        		 }
         }
         Collections.sort(returnList);
         return returnList;
     }
     
     public static void addEvent(EventModel m)
            {
                allEvents.add(m);
                try {
					PopulateAllEvents.quit();
				} catch (IOException e) {
					e.printStackTrace();
				}
                allEvents=new ArrayList<EventModel>();
                try {
					PopulateAllEvents.PopulateAllEvents();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }
     
     public static void quit() throws FileNotFoundException, IOException
     {
         File file = new File("Events.txt");
            if(!file.exists()){
        	try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println("could not create file!");
				e1.printStackTrace();
			}
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
            for(EventModel e:allEvents)
            {
                GregorianCalendar start = e.getDate();
                GregorianCalendar end = e.getEndTime();
                bw.write(e.getTitle()+", "+start.get(Calendar.YEAR)+", "+start.get(Calendar.MONTH)+", "
                        + start.get(Calendar.DAY_OF_MONTH)+", "+ start.get(Calendar.HOUR_OF_DAY)+", "
                        +start.get(Calendar.MINUTE)+", "+end.get(Calendar.HOUR_OF_DAY)+", "+end.get(Calendar.MINUTE)+"\n");
                
                
            }

            bw.close();
            
            //System.out.println("Events written to file successfully!");
            
        }
     }

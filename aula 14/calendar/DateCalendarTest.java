package calendar;

import java.util.Calendar;
public class DateCalendarTest
{  public static void main(String[] args)
   {  Calendar c = Calendar.getInstance();
      System.out.println("Current date and time: " + c.getTime());
      System.out.println("Year: " + c.get(Calendar.YEAR));
      System.out.println("Month: " + (c.get(Calendar.MONTH) + 1));
      System.out.println("Day of month: " + c.get(Calendar.DAY_OF_MONTH));

      c.set(Calendar.YEAR, 1963);
      c.set(Calendar.MONTH, Calendar.MARCH);
      c.set(Calendar.DAY_OF_MONTH, 8);
      System.out.println("\nRescheduled date and current time: " + c.getTime());
      System.out.println("Year: " + c.get(Calendar.YEAR));
      System.out.println("Month: " + (c.get(Calendar.MONTH) + 1));
      System.out.println("Day of month: " + c.get(Calendar.DAY_OF_MONTH));

      c = Calendar.getInstance();
      System.out.println("\nCurrent date and time: " + c.getTime());
      System.out.println("Year: " + c.get(Calendar.YEAR));
      System.out.println("Month: " + (c.get(Calendar.MONTH) + 1));
      System.out.println("Day of month: " + c.get(Calendar.DAY_OF_MONTH));
      int hour = c.get(Calendar.HOUR_OF_DAY);
      if(hour > 6 && hour < 12)
      {  System.out.println("Good morning");
      }
      else
      {  if(hour > 12 && hour < 18)
         {  System.out.println("Good afternoon");
         }
         else
         {  System.out.println("Good evening");
         }
      }
   }
}
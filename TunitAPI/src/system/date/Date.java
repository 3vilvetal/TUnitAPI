package system.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date 
{
	
	private static String DATE_FORMAT = "yyyy-MM-dd-hh-mm-ss";
	/**
	 * Get current date in needed format
	 * @return
	 */
	public static String getCurrentDate()
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * Get yesterday date
	 * @return
	 */
	public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        
        calendar.add(Calendar.DATE, -1);    
        return sdf.format(calendar.getTime());
	}
	
	/**
	 * Get tomorrow date
	 * @return
	 */
	public static String getTomorowDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        
        calendar.add(Calendar.DATE, +1);    
        return sdf.format(calendar.getTime());
	}
}
package br.com.gestao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FormatDate {

	SimpleDateFormat novoFormato = null;
	
	 public Date formataData(String data) {
		 
	        if (data == null || data.equals(""))
	            return null;

	        Date dt = new Date();
	        novoFormato = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	        	
				dt = novoFormato.parse(data);
			
	        } catch (ParseException e) {
				e.printStackTrace();
			}

	        return dt;
	    }
	 
	 public Date formatGregorian(String data) throws ParseException{
		 
		 if (data == null || data.equals(""))
	            return null;
	        
	        DateFormat formatterR = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
	        DateFormat formatterW = new SimpleDateFormat("dd/MM/yyyy");
	       
	        Date date = null;
	        try{
	        	date = formatterR.parse(data);
	        	
	        }catch(ParseException e){
	        	e.getMessage();
	        }
	        
	        Date dt = new Date();
	        String dataformt = "";
	        
	        if(date != null){
	        	dataformt = formatterW.format(date);
	        }
	        
	        dt = formatterW.parse(dataformt);
	        
	        return dt;	        
	 }
	 
	 public static String format(GregorianCalendar calendar){
		 
		    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		    
		    fmt.setCalendar(calendar);
		    
		    String dateFormatted = fmt.format(calendar.getTime());
		    
		    return dateFormatted;
		}
	 
	 public static GregorianCalendar convertFromDMY(String dd_mm_yy) throws ParseException {
		 
	     SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	     
	     Date date = fmt.parse(dd_mm_yy);
	     
	     GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
	     
	     cal.setTime(date);
	     
	     return cal;
	 }
}
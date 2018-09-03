package date_conversion;

/*
 * author: arya mukherjee
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;


public class Format_date extends UDF{
    
    //regex for dates like: Jun 30, 2009/ Jan 1, 2010/ Apr 02, 2012
    Pattern date1 = Pattern.compile("[a-zA-Z]{3}+\\s{1}+\\d{1,2}+\\p{Punct}\\s{1}+\\d{4}+");
    Matcher m1 = null;
    
    //regex for dates like: 06/12/2009, 7/14/2010, 07/1/2008, 8/2/2008
    Pattern date2 = Pattern.compile("\\d{1,2}+\\p{Punct}\\d{1,2}+\\p{Punct}\\d{4}+");
    Matcher m2 = null;
    
    //regex for the default format yyyy-MM-dd
    Pattern date3 = Pattern.compile("\\d{4}+\\p{Punct}\\d{2}+\\p{Punct}\\d{2}+");
    Matcher m3 = null;
    
    //takes a string date input and converts it to yyyy-MM-dd format
    public String evaluate(String date)
    {
        StringBuilder dateStr = new StringBuilder("");
        String year = null;
        String month = null;
        String day = null;
        String[] array = null;
        
        m1 = date1.matcher(date);
        m2 = date2.matcher(date);
        m3 = date3.matcher(date);
        
        //handle the case if date already in the correct format
        if(m3.matches())
            return date;
        
        if(m1.matches())
        {
            array = date.split(",");
            //assign the year
            year = array[1].trim();
            month = array[0].split(" ")[0].trim();
            day = array[0].split(" ")[1].trim();
            
            //handle day with single digit
            if(day.length() == 1)
            {
                day = "0" + day;
            }
            
            switch(month)
            {
                case "Jan":
                    month = "01";
                    break;
                case "Feb":
                    month = "02";
                    break;
                case "Mar":
                    month = "03";
                    break;
                case "Apr":
                    month = "04";
                    break;
                case "May":
                    month = "05";
                    break;
                case "Jun":
                    month = "06";
                    break;
                case "Jul":
                    month = "07";
                    break;
                case "Aug":
                    month = "08";
                    break;
                case "Sep":
                    month = "09";
                    break;
                case "Oct":
                    month = "10";
                    break;
                case "Nov":
                    month = "11";
                    break;
                case "Dec":
                    month = "12";
                    break;
            }
            
            //create the final date string
            dateStr.append(year).append("-").append(month).append("-").append(day);
            
        }
        else if(m2.matches())
        {
            array = date.split("/");
            //assign the year
            year = array[2].trim();
            month = array[0].trim();
            day = array[1].trim();
            
            //handle day with single digit
            if(day.length() == 1)
            {
                day = "0" + day;
            }
            //handle month with single digit
            if(month.length() == 1)
            {
                month = "0" + month;
            }
            
            //create the final date string
            dateStr.append(year).append("-").append(month).append("-").append(day);
            
        }
        return dateStr.toString();
        
    }

}
package dbUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Collection of static methods that format various data types (all passed in as objects).
 * For each data type, there is a method that formats the data type and an associated
 * method that wraps that formatted data in an HTML <td> tag.
 */
public class FormatUtils {
    
    
     public static String formatBoolean(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                Boolean bool = (Boolean) obj;
                return bool.toString();
            } catch (Exception e) {
                return "bad Boolean in FormatUtils:" + obj.toString() + " Error:" + e.getMessage();
            }
        }
    } // formatBoolean

    // DecimalFormat percentFormat = new DecimalFormat("%###.##");
    // Turns a date into a nicely formatted String.
    public static String formatDate(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            java.util.Date dateval = (java.util.Date) obj;
            SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            dateformat.setLenient(false);
            return dateformat.format(dateval);
        } catch (Exception e) {
            return "bad date in FormatUtils.formatDate: " + obj.toString() + " error: " + e.getMessage();
        }
    } // formatDate
    
        public static String formatDateYearFirst(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            java.util.Date dateval = (java.util.Date) obj;
            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateformat.setTimeZone(timeZone);
            dateformat.setLenient(false); 
            StringBuilder sb = new StringBuilder(dateformat.format(dateval));
            int index;
            for(index=0; index < sb.length(); index++){
                index = sb.indexOf("/", index);
                if(index == -1){
                    return sb.toString();
                }
                sb.setCharAt(index, '-');
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "bad date in FormatUtils.formatDate: " + obj.toString() + " error: " + e.getMessage();
        }
    } // formatDateYearFirst
    
        public static String formatDueDate(Object obj) {
        if (obj == null) {
            return "";
        }
        try {       
            SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            dateformat.setLenient(false);
            
            Calendar calendar = Calendar.getInstance();
            java.util.Date dateval = (java.util.Date) obj;
            calendar.setTime(dateval);
            calendar.add(Calendar.DATE, 20); //add 20 days
            
            
            return dateformat.format(calendar.getTime());
        } catch (Exception e) {
            return "bad date in FormatUtils.formatDate: " + obj.toString() + " error: " + e.getMessage();
        }
    } // formatDate

    public static String formatDateTd(Object obj) {
        String out = "<td style='text-align:center'>";
        String strDate = formatDate(obj);
        if (strDate.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strDate;
        }
        out += "</td>";
        return out;
    } // formatDateTd
    
        public static String formatDueDateTd(Object obj) {
        String out = "<td style='text-align:center'>";
        String strDate = formatDueDate(obj);
        if (strDate.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strDate;
        }
        out += "</td>";
        return out;
    } // formatDateTd
    
    

    public static String formatDollar(Object obj) {
        // null gets converted to empty string
        if (obj == null) {
            return "";
        }
        BigDecimal bd = (BigDecimal) obj;
        try {
            DecimalFormat intFormat = new DecimalFormat("$###,###,###,##0.00");
            return intFormat.format(bd);
        } catch (Exception e) {
            return "bad Dollar Amount in FormatUtils:" + obj.toString() + " Error:" + e.getMessage();
        }
    } // formatDollar

    public static String formatDollarTd(Object obj) {
        String out = "<td style='text-align:right'>";
        String strDollarAmt = formatDollar(obj);
        if (strDollarAmt.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strDollarAmt;
        }
        out += "</td>";
        return out;
    } // formatDollarTd
    
        public static String formatDollarCentered(Object obj) {
        String out = "<td style='text-align:center'>";
        String strDollarAmt = formatDollar(obj);
        if (strDollarAmt.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strDollarAmt;
        }
        out += "</td>";
        return out;
    } // formatDollarTd

    public static String formatInteger(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                Integer ival = (Integer) obj;
                DecimalFormat intFormat = new DecimalFormat("###,###,###,##0");
                return intFormat.format(ival);
            } catch (Exception e) {
                return "bad Integer in FormatUtils:" + obj.toString() + " Error:" + e.getMessage();
            }
        }
    } // formatInteger
    
    public static int formatIntegerReturnInt(Object obj) {
        if (obj == null) {
            return -1;
        } else {
            try {
                Integer ival = (Integer) obj;
                DecimalFormat intFormat = new DecimalFormat("###,###,###,##0");
                return ival;
            } catch (Exception e) {
                return -1;
            }
        }
    } // formatInteger

    public static String formatIntegerTd(Object obj) {
        String out = "<td style='text-align:right'>";
        String strInteger = formatInteger(obj);
        if (strInteger.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strInteger;
        }
        out += "</td>";
        return out;
    } // formatIntegerTd
    
        public static String formatIntegerCentered(Object obj) {
        String out = "<td style='text-align:center'>";
        String strInteger = formatInteger(obj);
        if (strInteger.length() == 0) {
            // if you don't put a "non-breaking space" in an empty td/cell, 
            // the cell's border doesn't show !
            out += "&nbsp;";
        } else {
            out += strInteger;
        }
        out += "</td>";
        return out;
    } // formatIntegerTd

    // this is not really formatting, but just converting to string type.
    public static String formatString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return (String) obj;
        }
    } // formatString

    public static String formatStringTd(Object obj) {
        String out = "<td style='text-align:left'>";
        String str = formatString(obj);
        if (str.length() == 0) {
            out += "&nbsp;";
        } else {
            out += str;
        }
        out += "</td>";
        return out;
    } // formatString
    
        public static String formatStringCentered(Object obj) {
        String out = "<td style='text-align:center'>";
        String str = formatString(obj);
        if (str.length() == 0) {
            out += "&nbsp;";
        } else {
            out += str;
        }
        out += "</td>";
        return out;
    } // formatString
        
        public static double stringToDollar(String stringDollar){
            if(stringDollar.charAt(0) == '$'){
                stringDollar = stringDollar.substring(1);
            }
            double dollar;
            try {
                dollar = Double.parseDouble(stringDollar);
            } catch(NumberFormatException e){
                dollar = -1;
            }
            
            return dollar;
        }
        
        public static java.sql.Date stringToDate(String date){
            try {
            java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("MM-dd-yyyy"); //please notice the capital M
            dateformat.setLenient(false);
            java.util.Date myDate = dateformat.parse(date);
            java.sql.Date convertedDate = new java.sql.Date(myDate.getTime()); // // not using (on purpose).
            return convertedDate; // means date is good
        } catch (ParseException e) {
            return null;  // can also add (to debug) + e.getMessage();
        }
        }

} // FormatUtils class

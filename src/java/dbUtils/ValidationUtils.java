package dbUtils;

public class ValidationUtils {

    /* Check string "val" to see if it has a valid java.sql.Date in it.
     * Return "" if the input is OK. Otherwise, return error message. */
    public static String dateValidationMsg(String val, boolean required) {
        // System.out.println("*************trying to convert ["+val+"] to date");

        if (val == null) {
            return "ValidationUtils.dateValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        try {
            java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("MM-dd-yyyy"); //please notice the capital M
            dateformat.setLenient(false);
            java.util.Date myDate = dateformat.parse(val);
            java.sql.Date convertedDate = new java.sql.Date(myDate.getTime()); // // not using (on purpose).
            return ""; // means date is good
        } catch (Exception e) {
            return "Please enter a valid date (format: MM-DD-YYYY)";  // can also add (to debug) + e.getMessage();
        }
    } // dateValidationMsg
    
    public static String dateValidationNoFutureMsg(String val, boolean required) {
        // System.out.println("*************trying to convert ["+val+"] to date");

        if (val == null) {
            return "ValidationUtils.dateValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        try {
            java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd"); //please notice the capital M
            dateformat.setLenient(false);
            java.util.Date myDate = dateformat.parse(val);
            java.sql.Date convertedDate = new java.sql.Date(myDate.getTime()); // // not using (on purpose).
            
            java.util.Date current = new java.util.Date();
            if(!myDate.after(current)){
                return ""; // means date is good
            } else {
                return "Date cannot be in the future";
            }
            
        } catch (Exception e) {
            return "Please enter a valid date (format: YYYY-MM-DD)";  // can also add (to debug) + e.getMessage();
        }
    } // dateValidationMsg
    
    public static String dateValidationAfterFirstMsg(String first, String second, boolean firstReq, boolean secondReq) {
        // System.out.println("*************trying to convert ["+val+"] to date");

        if (first == null || second == null) {
            return "ValidationUtils.dateValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        
        if ((first.length() == 0) && !firstReq) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        
        if ((second.length() == 0) && !secondReq) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        
        try {
            java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd"); //please notice the capital M
            dateformat.setLenient(false);
            java.util.Date firstDate = dateformat.parse(first);
            java.util.Date secondDate = dateformat.parse(second);
            
            
            if(secondDate.before(firstDate)){
                return "Returned before Checked Out"; 
            } else {
                return ""; // means date is good
            }
            
        } catch (Exception e) {
            return "Please enter a valid date (format: YYYY-MM-DD)";  // can also add (to debug) + e.getMessage();
        }
    } // dateValidationMsg

    /* Convert "val" (String) to java.sql.Date and return the converted date. */
    public static java.sql.Date dateConversion(String val) {

        if ((val == null) || (val.length() == 0)) {
            return null;
        }
        try {
            java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd"); //please notice the capital M
            dateformat.setLenient(false);
            java.util.Date myDate = dateformat.parse(val);
            return new java.sql.Date(myDate.getTime());
            //return d.toString(); // debugging...
        } catch (Exception e) {
            System.out.println("ValidationUtils.dateConversion(): cannot convert " + val + " to date.");
            return null;
        }
    } // dateConversion()


    /*
    Regex for Dollar Validation
    ^\d+: Start from the beginning of the string (^) and match one or more digits (\d+).
    (\.\d{2})?: Match a period character (\.) followed by exactly 2 digits (\d{2}). The question mark denotes that the value within the brackets can either exist or not.
    $: The string must end here.
    */
    public static String dollarValidation(String val, boolean required){
        if (val == null) {
            return "ValidationUtils.dollarValidation(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        
        String regex = "^^\\d+(\\.\\d{2})?$";
        
        if(val.matches(regex)){
            return "";
        } else {
            return "ValidationUtils.dollarValidation: Invalid dollar input. Must not contain dollar symbol nor commas"
                    + " and must have only two decimals.";
        }
    }
    
    /* Check string "val" to see if it has a valid BigDecimal in it.
     * Return "" if the input is OK. Otherwise, return error message. */
    public static String decimalValidationMsg(String val, boolean required) {

        if (val == null) {
            return "ValidationUtils.decimalValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is valid user entry.
        }
        try {
            java.math.BigDecimal convertedDecimal = new java.math.BigDecimal(val); // not using (on purpose).
            return "";
        } catch (Exception e) {
            return "Please enter a dollar amount";
        }
    } // decimalValidationMsg()

    /* Convert "val" (String) to java.math.BigDecimal and return the converted BigDecimal. */
    public static java.math.BigDecimal decimalConversion(String val) {

        if ((val == null) || (val.length() == 0)) {
            return null;  // Since this field is not required, empty string is valid user entry.
        }
        try {
            return new java.math.BigDecimal(val);
        } catch (Exception e) {
            System.out.println("ValidationUtils.decimalConversion(): cannot convert " + val + " to java.math.BigDecimal.");
            return null;
        }
    } // decimalValidationMsg()

    /* Check string "val" to see if it has a valid integer in it.
     * Return "" if the input is OK. Otherwise, return error message. */
    public static String integerValidationMsg(String val, boolean required) {
        if (val == null) {
            return "ValidationUtils.integerValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is a valid user entry.
        }
        try {
            Integer convertedInteger = new Integer(val); // not using (on purpose).
            return "";
        } catch (Exception e) {
            return "Please enter an integer";
        }
    } // integerValidationMsg()
    
     public static String hoursPerWeekValidation(int val, boolean required) {

        if ((val == -1) && !required) {
            return "";  // Since this field is not required, empty string is a valid user entry.
        }
        if (val <= 0){
            return "Error: Hours Per Week must be greater than 0 or blank";
        }
        
        return "";
    } // integerValidationMsg()

    /* Convert "val" (String) to Integer and return the converted Integer. */
    public static Integer integerConversion(String val) {

        if ((val == null) || (val.length() == 0)) {
            return null;
        }
        try {
            return new Integer(val);
        } catch (Exception e) {
            System.out.println("ValidationUtils.integerConversion(): cannot convert " + val + " to Integer.");
            return null;
        }
    } // integerConversion()  
    
        /* Check string "val" to see if it has a valid integer in it.
     * Return "" if the input is OK. Otherwise, return error message. */
    public static String integerBoundedValidationMsg(String val, boolean required, int lower, int upper) {
        if (val == null) {
            return "ValidationUtils.integerValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if ((val.length() == 0) && !required) {
            return "";  // Since this field is not required, empty string is a valid user entry.
        }
        try {
            Integer convertedInteger = new Integer(val); // not using (on purpose).
            if(convertedInteger < lower || convertedInteger > upper){
                return "Must be between "+ String.valueOf(lower) + " and " + String.valueOf(upper) + ".";
            }
            return "";
        } catch (Exception e) {
            return "Please enter an integer";
        }
    } // integerValidationMsg()

    /* Check string "val" to see if it meets the db constraints (e.g., not emtpy string 
     * if it is a required field, not longer than db allows). If OK, return "". 
     * Otherwise, return error message. */
    public static String stringValidationMsg(String val, int maxlen, boolean required) {

        if (val == null) {
            return "ValidationUtils.stringValidationMsg(): Programmer error - should not be trying to validate null.";
        }
        if (val.length() == 0) {
            if (required) {
                return "Input is required";
            } else {
                return ""; // Empty string OK if fld not req'd.
            }
        }

        if (val.length() > maxlen) {
            return "Cannot be longer than " + maxlen + " characters.";
        } else {
            return ""; // input is good
        }
    }
    
        //Checks val to see if it says true or false, otherwise there is an error
    public static String booleanValidation(String val, boolean required) {

        if (val.length() == 0) {
            if (required) {
                return "Input is required";
            } else {
                return ""; // Empty string OK if fld not req'd.
            }
        }
        if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
            return "";
        } else {
            return "Error in ValidationUtils.booleanValidation: input must be \"true\" or \"false\"";
        }
       
    }
}
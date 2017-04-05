package dbUtils;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * Wrapper class for database connection. Constructor opens connection. Close
 * method closes connection.
 */
public class DbConn {

    private String errMsg = ""; // will remain "" unless error getting connection
    private java.sql.Connection conn = null;
    private String user = "public_user";
    private String password = "tumobilepubuser";

    public DbConn() {

        try {
            String DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER).newInstance();
            try {
                // Assume you are running from home using tunneling...
                String url = "jdbc:mysql://tumobilemarketplacedb.c0eayzivijb8.us-west-2.rds.amazonaws.com:3306/marketplace?user=" + user + "&password=" + password + "&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	                
                // unless you are working from temple (wachman hall)
                
                this.conn = DriverManager.getConnection(url);

            } catch (Exception e) { // cant get the connection
                recordError("Problem getting connection: " + e.getMessage() + " Please try again later.");
            }
        } catch (Exception e) { // cant get the driver...
            recordError("Problem getting driver: " + e.getMessage() + " Please try again later.");
        }
    } // method

    private void recordError(String errorMsg) {
        this.errMsg = errorMsg;
        System.out.println("Error in DbConn. " + errorMsg);
    }

    /* Returns database connection for use in SQL classes.  */
    public Connection getConn() {
        return this.conn;
    }

    /* Returns database connection error message or "" if there is none.  */
    public String getErr() {
        return this.errMsg;
    }

    /**
     * Close database connection.
     */
    public void close() {

        if (conn != null) {
            try {
                conn.close();
            } // try
            catch (Exception e) {
                // Don't care if connection was already closed. Do nothing.
            } // catch
        } // if
    } // method

    /**
     * Checks the hostname to see if web app is running at Temple or not.
     */
    private boolean isTemple() {
        boolean temple = false;
        try {
            String hostName = java.net.InetAddress.getLocalHost().getCanonicalHostName();
            hostName = hostName.toLowerCase();
            if (hostName.endsWith("temple.edu")) {
                temple = true;
                //System.out.println("************* Running from Temple, so using cis-linux2 for db connection");
            } else {
                //System.out.println("************* Not running from Temple, so using local for db connection");
            }
        } catch (Exception e) {
            recordError("Unable to get hostname: " + e.getMessage());
        }
        return temple;
    }
} // class
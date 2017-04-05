package modelUser;

public class User {

    private String tuId = "";
    private String email = "";
    private String firstName = "";
    private String lastName="";
    private String phoneNumber="";
    private String error = "";

    public User(String tuId, String email, String firstName, String lastName, String phoneNumber) {
        this.tuId = tuId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName; 
        this.phoneNumber = phoneNumber;
    }
    public User(){
     tuId = "";
     email = "";
     firstName = "";
     lastName="";
     phoneNumber="";
     error = "";
    }
    
    public boolean isEmpty(){
        String allFields = tuId + email + firstName + lastName + phoneNumber + error;
        if(allFields.length() == 0){
            return true;
        }
        return false;
    }
    
    public static String removeDashes(String phoneNumber){
        int i = phoneNumber.indexOf("-");
        while(i != -1){
            phoneNumber = phoneNumber.substring(0, i) + phoneNumber.substring(i+1);
            i = phoneNumber.indexOf("-");
        }
        return phoneNumber;
    }

    public String getTuId() {
        return tuId;
    }

    public void setTuId(String tuId) {
        this.tuId = tuId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
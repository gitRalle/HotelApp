import java.io.Serializable;

public class Customer extends User implements Serializable {
    private static int idCounter = 0;
    private final int customerId = ++idCounter;
    private String firstName;
    private String lastName;
    private final String SSN;
    private String telephoneNumber;
    private String address;

    public Customer(String firstName, String lastName,
                    String SSN, String telephoneNumber, String address,
                    String userName, String passWord, boolean fullAccess) {
        super(userName, passWord, fullAccess);
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }

    public int getCustomerId() {return customerId;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getSSN() {return SSN;}
    public String getTelephoneNumber() {return telephoneNumber;}
    public void setTelephoneNumber(String telephoneNumber) {this.telephoneNumber = telephoneNumber;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}


}

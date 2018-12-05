import java.util.ArrayList;
import java.util.Scanner;


public class Customers {
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public Customer get(int index) {
        return listOfCustomers.get(index);
    }
    public void remove(int index) {
        listOfCustomers.remove(index);
    }
    public void add(Customer c) {
        listOfCustomers.add(c);
    }
    public int size() {
        return listOfCustomers.size();
    }
    public void viewCustomers() {
        System.out.println("DISPLAY CUSTOMERS\n##################################################################################");
        for (int i = 0; i < listOfCustomers.size(); i++) {
            System.out.printf("[%s] [NAME: %s %s] [SSN: %s] [PHONE: %s] [ADDRESS: %s]%n", listOfCustomers.get(i).getCustomerId(),
                    listOfCustomers.get(i).getFirstName(), listOfCustomers.get(i).getLastName(),
                    listOfCustomers.get(i).getSSN(), listOfCustomers.get(i).getTelephoneNumber(),
                    listOfCustomers.get(i).getAddress());
        }
        System.out.print("##################################################################################\nPress any key to return to Main Menu >>");
        input.nextLine();

    }

}

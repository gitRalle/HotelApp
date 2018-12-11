import java.util.ArrayList;

public class Customers {
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();

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

}

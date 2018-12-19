public class Booking {
    private static int idCounter = 0;
    private final int bookingId = ++idCounter;
    private Customer customer;
    private Room room;
    private Date checkIn = new Date(2018, 11, 29);
    private Date checkOut;
    private double totalPrice;

    public Booking(Customer customer, Room room, Date checkOut, double totalPrice) {
        this.customer = customer;
        this.room = room;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public int getBookingId() {
        return bookingId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Room getRoom() {
        return room;
    }
    public Date getCheckIn() {
        return checkIn;
    }
    public Date getCheckOut() {
        return checkOut;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}
}

public class Bookings {
    private ArrayList<Booking> listOfBookings = new ArrayList<>();

    public Booking get(int index) {
        return listOfBookings.get(index);
    }
    public void remove(int index) {
        listOfBookings.remove(index)
    }
    public void add(Booking b) {
        listOfBookings.add(b);
    }
    public int size() {
        return listOfBookings.size();
    }
}

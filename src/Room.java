public class Room {
    private static int idCounter = 0;
    private final int roomId = ++idCounter;
    private int roomNumber;
    private int numberOfBeds;
    private boolean hasBalcony;
    private double pricePerNight;
    private boolean isBooked = false;

    public Room(int roomNumber, int numberOfBeds, boolean hasBalcony,
                double pricePerNight) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.hasBalcony = hasBalcony;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomId() {return roomId;}
    public int getRoomNumber() {return roomNumber;}
    public void setRoomNumber(int roomNumber) {this.roomNumber = roomNumber;}
    public int getNumberOfBeds() {return numberOfBeds;}
    public void setNumberOfBeds(int numberOfBeds) {this.numberOfBeds = numberOfBeds;}
    public boolean isHasBalcony() {return hasBalcony;}
    public void setHasBalcony(boolean hasBalcony) {this.hasBalcony = hasBalcony;}
    public double getPricePerNight() {return pricePerNight;}
    public void setPricePerNight(double pricePerNight) {this.pricePerNight = pricePerNight;}
    public boolean isBooked() {return isBooked;}
    public void setBooked(boolean isBooked) {this.isBooked = isBooked;}

}

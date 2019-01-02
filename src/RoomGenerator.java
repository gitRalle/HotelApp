import java.security.SecureRandom;
import java.util.ArrayList;

public class RoomGenerator {
    private static SecureRandom rand = new SecureRandom();
    private static int[] beds = {1, 2};
    private static double[] price = {1000, 1250, 1500, 2000};
    private static boolean[] balcony = {true, false};
    private static final int NUMBER_OF_ROOMS = 25;

    private ArrayList<Room> listOfRooms = new ArrayList<>();

    public RoomGenerator() {
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            listOfRooms.add(new Room(i + 1, beds[rand.nextInt(2)], balcony[rand.nextInt(2)],
                    price[rand.nextInt(4)]));
        }
    }

    public Room get(int index) {
        return listOfRooms.get(index);
    }
    public void remove(int index) {
        listOfRooms.remove(index);
    }
    public void add(Room r) {
        listOfRooms.add(r);
    }
    public int size() {
        return listOfRooms.size();
    }
}

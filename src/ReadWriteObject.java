import com.sun.jdi.event.ExceptionEvent;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteObject {

    public static void writeObject(ArrayList<Object> myObjects) {
        File file = new File("c:/users/wne-/dev/git/HotelApp/bookings.data");

        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < myObjects.size(); i++) {
                oOut.writeObject(myObjects.get(i));
            }
        } catch (Exception ex) {
            System.out.println("failed to write objects to file");
        }
    }

    public static ArrayList<Object> readObject() {
        Object objectFromFile;
        File file = new File("c:/users/wne-/dev/git/HotelApp/bookings.data");
        boolean cont = true;
        ArrayList<Object> al = new ArrayList<>();

        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file))) {
            while (cont) {
                objectFromFile = oIn.readObject();
                if(objectFromFile != null)
                    al.add(objectFromFile);
                else
                    cont = false;
            }
        } catch (Exception ex) {
            // do nothing
        }
        return al;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private Rooms roomDirectory = new Rooms();
    private Customers customerDirectory = new Customers();

    private String userName = "";

    private enum Access {ADMIN, GUEST}

    public enum Filter {
        ALL, AVAILABLE, BOOKED,
        NAME, SSN, PHONE, ADDRESS,
        ADD, EDIT, REMOVE,
        RNR, BEDS, BALCONY, PRICE
    }

    private boolean cont;
    private boolean stayLoggedIn = true;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main hotelApp = new Main();
        hotelApp.listOfUsers.add(new User("admin", "1234", true));
        hotelApp.listOfUsers.add(new User("gitRalle", "1234", true));
        hotelApp.listOfUsers.add(new User("guest", "1234", false));

        Customer obj1 = new Customer("Anna", "Anka", "500422-1639", "0787555555", "Main Str 17",
                "Anka1", "1234", false);
        Customer obj2 = new Customer("Harry", "Karlsson", "670422-2020", "0786344555", "Abbey Rd 2",
                "Karlsson1", "1234", false);
        hotelApp.listOfUsers.add(obj1);
        hotelApp.listOfUsers.add(obj2);
        hotelApp.customerDirectory.add(obj1);
        hotelApp.customerDirectory.add(obj2);
        hotelApp.roomDirectory.get(0).setBooked(true); hotelApp.roomDirectory.get(24).setBooked(true);
        hotelApp.roomDirectory.get(3).setBooked(true); hotelApp.roomDirectory.get(17).setBooked(true);
        hotelApp.roomDirectory.get(8).setBooked(true);
        hotelApp.roomDirectory.get(12).setBooked(true);


        Access user;
        int choice;
        String stringChoice;

        while (hotelApp.stayLoggedIn) {
            user = hotelApp.login();
            hotelApp.cont = true;

            while (hotelApp.cont) {
                hotelApp.printMenuStart(user);
                System.out.print("Enter your choice >>");
                stringChoice = hotelApp.input.next();
                if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
                        !stringChoice.equals("4") && !stringChoice.equals("5") && !stringChoice.equals("6") &&
                        !stringChoice.equals("7") && user == Access.ADMIN) {
                    hotelApp.runCmd(stringChoice);
                } else {
                    choice = Integer.valueOf(stringChoice);

                    switch (choice) {
                        case 1:
                            hotelApp.printMenuI(user);
                            break;

                        case 2:
                            hotelApp.printMenuII(user);
                            break;

                        case 3:
                            hotelApp.printMenuIII(user);
                            break;

                        case 4:
                            hotelApp.printMenuIV(user);
                            break;

                        case 5:
                            hotelApp.printMenuV(user);
                            break;

                        case 6:
                            hotelApp.input.nextLine();
                            System.out.println("");
                            hotelApp.cont = false;
                            break;

                        case 7:
                            System.out.print("\nExiting Hotel App");
                            hotelApp.cont = false;
                            hotelApp.stayLoggedIn = false;
                            break;
                    }
                }
            }
        }
    }

    private Access login() {
        Access user = null;
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("username: ");
            String username = input.nextLine();
            System.out.print("password: ");
            String password = input.nextLine();
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i).getUserName().equals(username) && listOfUsers.get(i).getPassWord().equals(password) && listOfUsers.get(i).getFullAccess()) {
                    System.out.println("\nLogged in as " + listOfUsers.get(i).getUserName());
                    user = Access.ADMIN;
                    userName = listOfUsers.get(i).getUserName();
                    i = listOfUsers.size();
                    attempts = 3;
                } else if (listOfUsers.get(i).getUserName().equals(username) && listOfUsers.get(i).getPassWord().equals(password) && !listOfUsers.get(i).getFullAccess()) {
                    System.out.println("\nLogged in as " + listOfUsers.get(i).getUserName());
                    user = Access.GUEST;
                    userName = listOfUsers.get(i).getUserName();
                    i = listOfUsers.size();
                    attempts = 3;
                }
            }
            if (attempts == 2) {
                System.exit(0);
            }

            if (attempts != 3) {
                System.out.println("incorrect user or pw\n");
            }
        }
        return user;
    }

    private void printMenuStart(Access type) {
        if (type == Access.ADMIN) {
            System.out.println("\n" + userName);
            System.out.println(type);
            System.out.println("START MENU                            [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. New Booking                                 |  NOT IMPLEMENTED");
            System.out.println("| 2. Manage Bookings                             |");
            System.out.println("| 3. Manage Accommodations                       |");
            System.out.println("| 4. Manage Customers                            |");
            System.out.println("| 5. Check Out Customer                          |  NOT IMPLEMENTED");
            System.out.println("| 6. Log Out                                     |");
            System.out.println("| 7. Exit                                        |");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("\n" + userName);
            System.out.println(type);
            System.out.println("START MENU                            [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Make A New Booking                          |  NOT IMPLEMENTED");
            System.out.println("| 2. View Available Rooms                        |");
            System.out.println("| 3. View Your Booking History                   |  NOT IMPLEMENTED");
            System.out.println("| 4. Edit Your Personal Information              |");
            System.out.println("| 5. Edit Bookings                               |  NOT IMPLEMENTED");
            System.out.println("| 6. Log Out                                     |");
            System.out.println("| 7. Exit                                        |");
            System.out.println("-------------------------------------------------");
        }
    }

    // Menu 1 and it's respective methods
    private void printMenuI(Access type) {
        if (type == Access.ADMIN) {
            // New Booking
        } else {
            // Make A New Booking
        }
    }

    // Menu 2 and it's respective methods
    private void printMenuII(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            // Mange Bookings
            System.out.println("\n" + userName);
            System.out.println("MANAGE BOOKINGS                       [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Edit Booking                                |  NOT IMPLEMENTED");
            System.out.println("| 2. Display All Bookings                        |  NOT IMPLEMENTED");
            System.out.println("| 3. Search For Booking(s)                       |  NOT IMPLEMENTED");
            System.out.println("| 4. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
                    !stringChoice.equals("4")) {
                runCmd(stringChoice);
            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        } else {
            // View Available Rooms
            System.out.println("\n" + userName);
            System.out.println("VIEW AVAILABLE ROOMS                  [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. View All Currently Available Rooms          |");
            System.out.println("| 2. View Available Rooms Within Specified Dates |  NOT IMPLEMENTED");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                // Back to startMenu
            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        viewRooms(Access.GUEST, Filter.AVAILABLE);
                        break;
                }
            }
        }
    }

    // Menu 3 and it's respective methods
    private void printMenuIII(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + userName);
            System.out.println("MANAGE ACCOMMODATIONS                 [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. View Available Rooms                        |");
            System.out.println("| 2. View All Rooms                              |");
            System.out.println("| 3. Edit Room                                   |");
            System.out.println("| 4. Add Room                                    |");
            System.out.println("| 5. Remove Room                                 |");
            System.out.println("| 6. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") &&
                    !stringChoice.equals("3") && !stringChoice.equals("4") &&
                    !stringChoice.equals("5") && !stringChoice.equals("6")) {
                runCmd(stringChoice);
            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        viewRooms(Access.ADMIN, Filter.AVAILABLE);
                        break;
                    case 2:
                        viewRooms(Access.ADMIN, Filter.ALL);
                        break;
                    case 3:
                        printMenuEditRoom();
                        break;
                    case 4:
                        addRemoveRoom(Filter.ADD);
                        break;
                    case 5:
                        addRemoveRoom(Filter.REMOVE);
                        break;
                }
            }
        } else {
            // View Your Booking History
        }
    }

    private void viewRooms(Access type, Filter filt) {
        String stringChoice;
        int counter = 0;
        System.out.println("");

        if (filt == Filter.ALL) {
            if (roomDirectory.size() > 1) {
                System.out.println("VIEW ROOMS");
                System.out.println("FILTER.ALL                                          [2018/11/29]");
                System.out.printf("%s %5s %7s %11s", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.printf("%8s %6s %7s %7s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-----------------------------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    System.out.printf("%2d %5d %6s %10.2f    |    ", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                            roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                    counter++;
                    if (counter % 2 == 0) {
                        System.out.println("");
                    }
                }
                if (counter % 2 != 0) {
                    System.out.println("                              |");
                }
                System.out.println("-----------------------------------------------------------------");
                System.out.print("Press any key >>");

            } else {
                System.out.println("VIEW ROOMS");
                System.out.println("FILTER.ALL                    [2018/11/29]");
                System.out.printf("%s %9s %12s %15s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    System.out.printf("%2d %8d %12s %14.2f    |%n", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                            roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                }
                System.out.println("-------------------------------------------");
                System.out.print("Press any key >>");

            }

        } else if (filt == Filter.AVAILABLE) {
            if (roomDirectory.size() > 1) {
                System.out.println("VIEW ROOMS\nFILTER.AVAILABLE                                    [2018/11/29]");
                System.out.printf("%s %5s %7s %11s", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.printf("%8s %6s %7s %7s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-----------------------------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (!roomDirectory.get(i).isBooked()) {
                        System.out.printf("%2d %5d %6s %10.2f    |    ", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                                roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                        counter++;
                        if (counter % 2 == 0) {
                            System.out.println("");
                        }
                    }
                }
                if (counter % 2 != 0) {
                    System.out.println("                              |");
                }
                System.out.println("-----------------------------------------------------------------");
                System.out.print("Press any key >>");

            } else {
                System.out.println("VIEW ROOMS");
                System.out.println("FILTER.AVAILABLE              [2018/11/29]");
                System.out.printf("%s %9s %12s %15s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (!roomDirectory.get(i).isBooked()) {
                        System.out.printf("%2d %8d %12s %14.2f    |%n", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                                roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                    }
                }
                System.out.println("-------------------------------------------");
                System.out.print("Press any key >>");
            }
        }
        input.nextLine();
        stringChoice = input.next();
        if (type == Access.ADMIN && stringChoice.equals(".")) {
            printMenuIII(Access.ADMIN);
        } else if (type == Access.ADMIN && stringChoice.equals("..")) {

        } else if (type == Access.ADMIN && !stringChoice.equals(".") && !stringChoice.equals("..")) {
            runCmd(stringChoice);
        } else if (type == Access.GUEST && stringChoice.equals(".")) {
            printMenuII(Access.GUEST);
        }
    }


    private void addRemoveRoom(Filter filt) {
        int roomNumber, numberOfBeds, counter, index;
        boolean hasBalcony = false; // ... variable won't ever use it's default value
        double pricePerNight;
        String stringChoice, temp;

        System.out.println("");
        if (filt == Filter.ADD) {
            do {
                counter = 0;
                System.out.print("Enter room's room number: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() == roomNumber || roomNumber <= 0 || roomNumber >= 100) {
                        counter++;
                    }
                }
                if (counter != 0) {
                    System.out.println("\nRNR [" + roomNumber + "] is already in use or out-of-bounds");
                }
            } while (counter != 0);

            do {
                counter = 0;
                System.out.print("Enter room's number of beds: ");
                numberOfBeds = input.nextInt();
                if (numberOfBeds != 1 && numberOfBeds != 2 && numberOfBeds != 3 && numberOfBeds != 4) {
                    counter++;
                }
                if (counter != 0) {
                    System.out.println("\nRoom can hold one, two, three or four beds");
                }
            } while (counter != 0);

            do {
                counter = 0;
                System.out.print("Enter room's balcony status (y/n): ");
                temp = input.next();
                if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                    hasBalcony = true;
                } else if (temp.equalsIgnoreCase("n") || temp.equalsIgnoreCase("no")) {
                    hasBalcony = false;
                } else {
                    counter++;
                }
                if (counter != 0) {
                    System.out.println("\nAnswer with: (y/n) or (yes/no)");
                }
            } while (counter != 0);

            do {
                counter = 0;
                System.out.print("Enter room's price per night: ");
                pricePerNight = input.nextDouble();
                if (pricePerNight < 1000 || pricePerNight > 2500) {
                    counter++;
                }
                if (counter != 0) {
                    System.out.println("\nHotel's price range for rooms are (1000 - 2500 SEK) / per night");
                }
            } while (counter != 0);
            roomDirectory.add(new Room(roomNumber, numberOfBeds, hasBalcony, pricePerNight));
            System.out.println("Room successfully added to room directory");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIII(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        } else if (filt == Filter.EDIT) {
        } else if (filt == Filter.REMOVE) {
            index = 99; // variable won't ever take on default value
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nREMOVE ROOM\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            System.out.print("Are you sure? (y/n): ");
            temp = input.next();
            if (temp.equals("y") || temp.equals("yes")) {
                roomDirectory.remove(index);
                System.out.println("Room successfully removed");
            }
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIII(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        }
    }

    private void printMenuEditRoom() {
        int choice;
        String stringChoice;

        System.out.println("\n" + userName);
        System.out.println("EDIT ROOM                             [2018/11/29]");
        System.out.println("-------------------------------------------------");
        System.out.println("| 1. Edit Room Number                            |");
        System.out.println("| 2. Edit Number Of Beds                         |");
        System.out.println("| 3. Edit Price                                  |");
        System.out.println("| 4. Edit Balcony Status                         |");
        System.out.println("| 5. Edit All Room Attributes                    |");
        System.out.println("| 6. Back To Main Menu                           |");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice >>");
        stringChoice = input.next();
        if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
                !stringChoice.equals("4") && !stringChoice.equals("5") && !stringChoice.equals("6")) {
            if (stringChoice.equals(".")) {
                printMenuIII(Access.ADMIN);
            }
            runCmd(stringChoice);
        } else {
            choice = Integer.valueOf(stringChoice);

            switch (choice) {
                case 1:
                    editRoom(Filter.RNR);
                    break;
                case 2:
                    editRoom(Filter.BEDS);
                    break;
                case 3:
                    editRoom(Filter.PRICE);
                    break;
                case 4:
                    editRoom(Filter.BALCONY);
                    break;
                case 5:
                    editRoom(Filter.ALL);
                    break;
                case 6: // Back to startMenu
                    break;
            }
        }
    }

    private void editRoom(Filter filt) {
        String stringChoice;
        int roomNumber, numberOfBeds, counter, index = 99;
        boolean hasBalcony;
        double pricePerNight;

        System.out.println("");
        if (filt == Filter.ALL) {
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nEDIT ALL ATTRIBUTES\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            do {
                counter = 0;
                System.out.print("New RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() == roomNumber && i != index || roomNumber <= 0 || roomNumber >= 100) {
                        counter++;
                    }
                }
                if (counter != 0) {
                    System.out.println("\nRNR [" + roomNumber + "] is already in use or out-of-bounds");
                }
            } while (counter != 0);

            do {
                counter = 0;
                System.out.print("New Number of beds: ");
                numberOfBeds = input.nextInt();
                if (numberOfBeds != 1 && numberOfBeds != 2 && numberOfBeds != 3 && numberOfBeds != 4) {
                    System.out.println("\nRoom can hold one, two, three or four beds");
                }
            } while (numberOfBeds != 1 && numberOfBeds != 2 && numberOfBeds != 3 && numberOfBeds != 4);

            System.out.print("New has Balcony status (true/false): ");
            hasBalcony = input.nextBoolean();

            do {
                System.out.print("New Price per night: ");
                pricePerNight = input.nextDouble();
                if (pricePerNight < 1000 || pricePerNight > 2500) {
                    System.out.println("\nHotel's price range for rooms are (1000 - 2500 SEK) / per night");
                }
            } while (pricePerNight < 1000 || pricePerNight > 2500);
            roomDirectory.get(index).setRoomNumber(roomNumber);
            roomDirectory.get(index).setNumberOfBeds(numberOfBeds);
            roomDirectory.get(index).setHasBalcony(hasBalcony);
            roomDirectory.get(index).setPricePerNight(pricePerNight);
            System.out.println("Room successfully updated");
        } else if (filt == Filter.RNR) {
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nEDIT RNR\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            do {
                counter = 0;
                System.out.print("New RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() == roomNumber || roomNumber <= 0 || roomNumber >= 100) {
                        counter++;
                    }
                }
                if (counter != 0) {
                    System.out.println("\nRNR [" + roomNumber + "] is already in use or out-of-bounds");
                }
            } while (counter != 0);
            roomDirectory.get(index).setRoomNumber(roomNumber);
            System.out.println("Room successfully updated");
        } else if (filt == Filter.BEDS) {
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nEDIT NUMBER OF BEDS\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            do {
                System.out.print("New Number of beds: ");
                numberOfBeds = input.nextInt();
                if (numberOfBeds != 1 && numberOfBeds != 2 && numberOfBeds != 3 && numberOfBeds != 4) {
                    System.out.println("\nRoom can hold one, two, three or four beds");
                }
            } while (numberOfBeds != 1 && numberOfBeds != 2 && numberOfBeds != 3 && numberOfBeds != 4);
            roomDirectory.get(index).setNumberOfBeds(numberOfBeds);
            System.out.println("Room successfully updated");
        } else if (filt == Filter.BALCONY) {
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nEDIT HAS BALCONY\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            System.out.print("New has Balcony status (true/false): ");
            hasBalcony = input.nextBoolean();
            roomDirectory.get(index).setHasBalcony(hasBalcony);
            System.out.println("Room successfully updated");
        } else if (filt == Filter.PRICE) {
            do {
                counter = 0;
                System.out.print("Enter RNR: ");
                roomNumber = input.nextInt();
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() != roomNumber) {
                        counter++;
                    }
                }
                if (counter == roomDirectory.size()) {
                    System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                }
            } while (counter == roomDirectory.size());

            for (int i = 0; i < roomDirectory.size(); i++) {
                if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                    index = i;
                    break;
                }
            }
            System.out.println("\nEDIT RNR\n-------------------------------");
            System.out.printf("%s %5s %7s %11s |%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%2d %5d %6s %10.2f    |%n",
                    roomDirectory.get(index).getRoomNumber(), roomDirectory.get(index).getNumberOfBeds(),
                    roomDirectory.get(index).isHasBalcony() ? "Yes" : "No", roomDirectory.get(index).getPricePerNight());
            System.out.println("-------------------------------");
            do {
                System.out.print("New Price per night: ");
                pricePerNight = input.nextDouble();
                if (pricePerNight < 1000 || pricePerNight > 2500) {
                    System.out.println("\nHotel's price range for rooms are (1000 - 2500 SEK) / per night");
                }
            } while (pricePerNight < 1000 || pricePerNight > 2500);
            roomDirectory.get(index).setPricePerNight(pricePerNight);
            System.out.println("Room successfully updated");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuEditRoom();
        } else if (stringChoice.equals("..")) {
            printMenuIII(Access.ADMIN);
        } else if (stringChoice.equals("...")) {
            printMenuStart(Access.ADMIN);
        } else if (!stringChoice.equals(".") && !stringChoice.equals("..") && !stringChoice.equals("...")) {
            runCmd(stringChoice);
        }
    }

    // Menu 4 and its respective methods
    private void printMenuIV(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + userName);
            System.out.println("MANAGE CUSTOMERS                      [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Add New Customer                            |");
            System.out.println("| 2. Remove Customer                             |");
            System.out.println("| 3. Display Customers                           |");
            System.out.println("| 4. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
                    !stringChoice.equals("4")) {
                runCmd(stringChoice);
            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        removeCustomer();
                        break;
                    case 3:
                        viewCustomers();
                        break;
                    case 4:
                        break;
                    default:
                        // ?
                        System.out.println(choice + " is not a valid option");
                        break;
                }
            }
        } else {
            System.out.println("\n" + userName + "\nEDIT INFORMATION                      [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Update Your Address                         |");
            System.out.println("| 2. Update Your Phone Number                    |");
            System.out.println("| 3. View Your Personal Information              |");
            System.out.println("| 4. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                // Back to main
            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        updateCustomerInfo(Filter.ADDRESS);
                        break;
                    case 2:
                        updateCustomerInfo(Filter.PHONE);
                        break;
                    case 3:
                        viewCustomerInfo();
                        break;
                    case 4:
                        break;
                    default:
                        // ?
                        System.out.println(choice + " is not a valid option");
                        break;
                }
            }
        }
    }

    private void viewCustomerInfo() {
        int index = 99; // will never actually take on value 99
        String stringChoice;
        input.nextLine();

        System.out.println("");
        for (int i = 0; i < customerDirectory.size(); i++) {
            if (customerDirectory.get(i).getUserName().equals(userName)) {
                index = i;
            }
        }
        System.out.println("CUSTOMER DETAILS          [2018/11/29]\n-------------------------------------");
        System.out.printf("Name: %s %s%nSSN: %s%nPhone: %s%nAddress: %s%n",
                customerDirectory.get(index).getFirstName(), customerDirectory.get(index).getLastName(), customerDirectory.get(index).getSSN(),
                customerDirectory.get(index).getTelephoneNumber(), customerDirectory.get(index).getAddress());
        System.out.println("-------------------------------------");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equalsIgnoreCase(".")) {
            printMenuIV(Access.GUEST);
        }

    }

    private void updateCustomerInfo(Filter type) {
        int index = 99; // will never actually take on value 99
        int counter = 0;
        String string;
        String stringChoice;
        input.nextLine();

        System.out.println("");
        for (int i = 0; i < customerDirectory.size(); i++) {
            if (customerDirectory.get(i).getUserName().equals(userName)) {
                index = i;
            }
        }
        if (type == Filter.ADDRESS) {
            do {
                counter = 0;
                System.out.printf("Old Address: %s%nNew Address: ",
                        customerDirectory.get(index).getAddress());
                string = input.nextLine();
                counter = validateString(string, Filter.ADDRESS);
                if (counter != 0) {
                    System.out.println("\nAddress should not contain any illegal characters");
                }
            } while (counter != 0);
            customerDirectory.get(index).setAddress(string);
        } else if (type == Filter.PHONE) {
            do {
                counter = 0;
                System.out.printf("Old Phone Number: %s%nNew Phone Number: ",
                        customerDirectory.get(index).getTelephoneNumber());
                string = input.nextLine();
                counter = validateString(string, Filter.PHONE);
                if (string.length() != 10 || counter != 0) {
                    System.out.println("\nPhone number must consist of ten digits without any hyphenate");
                }
            } while (counter != 0);
            customerDirectory.get(index).setTelephoneNumber(string);
        }
        System.out.print("Information successfully updated\nPress any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuIV(Access.GUEST);
        }
    }

    private void addCustomer() {
        String firstName, lastName, ssn, phoneNumber, address;
        int counter = 0;
        String stringChoice;
        input.nextLine();

        System.out.println("");
        do {
            counter = 0;
            System.out.print("Enter customer's first name: ");
            firstName = input.nextLine();
            counter = validateString(firstName, Filter.NAME);
            if (firstName.length() <= 2 || counter != 0) {
                System.out.println("\nName must consist of a minimum of three characters ranging from A - Ö.");
            }
        } while (firstName.length() <= 2 || counter != 0);

        do {
            counter = 0;
            System.out.print("Enter customer's last name: ");
            lastName = input.nextLine();
            counter = validateString(lastName, Filter.NAME);
            if (lastName.length() <= 2 || counter != 0) {
                System.out.println("\nName must consist of a minimum of three characters ranging from A - Ö.");
            }
        } while (lastName.length() <= 2 || counter != 0);

        do {
            counter = 0;
            System.out.print("Enter customer's ten digit social security number (YYMMDD-XXXX): "); // validate months/days ?
            ssn = input.nextLine();
            counter = validateString(ssn, Filter.SSN);
            if (!String.valueOf(ssn.charAt(6)).equals("-") || counter != 0 || ssn.length() != 11) {
                System.out.println("\nSocial security number must consist of ten digits with a hyphenate");
            }
        } while (!String.valueOf(ssn.charAt(6)).equals("-") || counter != 0 || ssn.length() != 11);

        do {
            counter = 0;
            System.out.print("Enter customer's phone number: ");
            phoneNumber = input.nextLine();
            counter = validateString(phoneNumber, Filter.PHONE);
            if (phoneNumber.length() != 10 || counter != 0) {
                System.out.println("\nPhone number must consist of ten digits without any hyphenate");
            }
        } while (phoneNumber.length() != 10 || counter != 0);

        do {
            counter = 0;
            System.out.print("Enter customer's address: ");
            address = input.nextLine();
            counter = validateString(address, Filter.ADDRESS);
            if (counter != 0) {
                System.out.println("\nAddress should not contain any illegal characters");
            }
        } while (counter != 0);

        // Set customer userName
        int userNameCounter = 1;
        for (int i = 0; i < customerDirectory.size(); i++) {
            if (customerDirectory.get(i).getLastName().equalsIgnoreCase(lastName)) {
                userNameCounter++;
            }
        }
        Customer temp = new Customer(firstName, lastName, ssn, phoneNumber, address,
                lastName + String.valueOf(userNameCounter), "1234", false);
        customerDirectory.add(temp);
        listOfUsers.add(temp);
        System.out.printf("Auto-generated username: %s%nAuto-generated password: %s%n",
                temp.getUserName(), temp.getPassWord());
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuIV(Access.ADMIN);
        } else if (stringChoice.equals("..")) {

        } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
            runCmd(stringChoice);
        }
    }

    private void removeCustomer() {
        int id;
        int index = 99;
        int counter = 0;
        String stringChoice;

        System.out.println("");
        if (customerDirectory.size() == 0) {
            System.out.print("Customer Directory Is Empty\n");
            System.out.print("Press any key >>");
            input.nextLine();
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIV(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        } else {
            System.out.println("REMOVE CUSTOMER                                                            [2018/11/29]" +
                    "\n---------------------------------------------------------------------------------------");
            for (int i = 0; i < customerDirectory.size(); i++) {
                System.out.printf("[%s] [NAME: %s %s] [SSN: %s] [PHONE: %s] [ADDRESS: %s]%n", customerDirectory.get(i).getCustomerId(),
                        customerDirectory.get(i).getFirstName(), customerDirectory.get(i).getLastName(),
                        customerDirectory.get(i).getSSN(), customerDirectory.get(i).getTelephoneNumber(),
                        customerDirectory.get(i).getAddress());
            }
            System.out.println("---------------------------------------------------------------------------------------");
            do {
                counter = 0;
                System.out.print("Select Customer's ID: ");
                id = input.nextInt();
                for (int i = 0; i < customerDirectory.size(); i++) {
                    if (customerDirectory.get(i).getCustomerId() != id) {
                        counter++;
                    }
                }
                if (counter == customerDirectory.size()) {
                    System.out.println("\nInvalid ID");
                }
            } while (counter == customerDirectory.size());
            // Swaps ID for Index
            for (int i = 0; i < customerDirectory.size(); i++) {
                if (customerDirectory.get(i).getCustomerId() == id) {
                    index = i;
                }
            }
            customerDirectory.remove(index);
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i) instanceof Customer && ((Customer) listOfUsers.get(i)).getCustomerId() == id) {
                    index = i;
                }
            }
            listOfUsers.remove(index);
            System.out.println("\nCustomer successfully removed");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIV(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        }
    }

    private void viewCustomers() {
        String stringChoice;

        System.out.println("");
        if (customerDirectory.size() == 0) {
            System.out.print("Customer Directory Is Empty\n" +
                    "Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIV(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        } else {
            System.out.println("DISPLAY CUSTOMERS                                                          [2018/11/29]" +
                    "\n---------------------------------------------------------------------------------------");
            for (int i = 0; i < customerDirectory.size(); i++) {
                System.out.printf("[%s] [NAME: %s %s] [SSN: %s] [PHONE: %s] [ADDRESS: %s]%n", customerDirectory.get(i).getCustomerId(),
                        customerDirectory.get(i).getFirstName(), customerDirectory.get(i).getLastName(),
                        customerDirectory.get(i).getSSN(), customerDirectory.get(i).getTelephoneNumber(),
                        customerDirectory.get(i).getAddress());
            }
            System.out.print("---------------------------------------------------------------------------------------\n" +
                    "Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printMenuIV(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
                runCmd(stringChoice);
            }
        }
    }

    // Menu 5 and it's respective methods
    private void printMenuV(Access type) {
        if (type == Access.ADMIN) {
            // Check Out
        } else {
            // Edit Bookings
        }
    }

    private int validateString(String string, Filter type) {
        int counter = 0;
        String[] charsOnly = {"!", "@", "#", "£", "¤", "$", "%", "&", "/", "{", "(", "[", ")", "]", "=", "}", "?", "+", "´", "`",
                "¨", "^", "~", "'", "*", "<", ">", "|", ",", ";", ".", ":", "-", "_", "§", "½"};
        String[] charsPlusNumbers = {"!", "@", "#", "£", "¤", "$", "%", "&", "/", "{", "(", "[", ")", "]", "=", "}", "?", "+", "´", "`",
                "¨", "^", "~", "'", "*", "<", ">", "|", ",", ";", ".", ":", "-", "_", "§", "½",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] charsPlusLetters = {"!", "@", "#", "£", "¤", "$", "%", "&", "/", "{", "(", "[", ")", "]", "=", "}", "?", "+", "´", "`",
                "¨", "^", "~", "'", "*", "<", ">", "|", ",", ";", ".", ":", "-", "_", "§", "½",
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "å", "a", "s", "d", "f", "g", "h", "j", "k", "l",
                "l", "ö", "ä", "z", "x", "c", "v", "b", "n", "m"};

        if (type == Filter.NAME) {
            for (int i = 0; i < string.length(); i++) {
                for (int x = 0; x < charsPlusNumbers.length; x++) {
                    if (String.valueOf(string.charAt(i)).equalsIgnoreCase(charsPlusNumbers[x])) {
                        counter++;
                    }
                }
            }
        }
        if (type == Filter.SSN) {
            for (int i = 0; i < string.length(); i++) {
                for (int x = 0; x < charsPlusLetters.length; x++) {
                    if (String.valueOf(string.charAt(i)).equalsIgnoreCase(charsPlusLetters[x]) && i != 6) {
                        counter++;
                    }
                }
            }
        }
        if (type == Filter.PHONE) {
            for (int i = 0; i < string.length(); i++) {
                for (int x = 0; x < charsPlusLetters.length; x++) {
                    if (String.valueOf(string.charAt(i)).equalsIgnoreCase(charsPlusLetters[x])) {
                        counter++;
                    }
                }
            }
        }
        if (type == Filter.ADDRESS) {
            for (int i = 0; i < string.length(); i++) {
                for (int x = 0; x < charsOnly.length; x++) {
                    if (String.valueOf(string.charAt(i)).equals(charsOnly[x])) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private void validateDate() { // Unnecessary method: include in addBooking?
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = 2018;
        int month, day, hour;
        int counter = 0;

        //    do {
        //        System.out.print("year: ");
        //        year = input.nextInt();
        //        if (year < 2018) {
        //            System.out.println("\nYear is out-of-range");
        //            counter++;
        //        }
        //    } while (counter != 0);

        do {
            counter = 0;
            System.out.print("(month / day): ");
            month = input.nextInt();
            day = input.nextInt();

            if (month <= 0 || month > 12) {
                System.out.println("\nMonth must be 1-12");
                counter++;
            }

            if (day <= 0 || day > daysPerMonth[month] && !(month == 2 && day == 29)) {
                System.out.println("\nday (" + day + ") out-of-range for specified month");
                counter++;
            }

            if (month == 2 & day == 29 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
                System.out.println("\nday ( " + day + ") out of range for the specified month and year");
                counter++;
            }
        } while (counter != 0);
        System.out.println("\n" + month + "/" + day);

        //    do {
        //        System.out.print("hour: ");
        //        hour = input.nextInt();
        //        if (hour < 0 || hour > 24) {
        //            System.out.println("\n Hour is out-of-range");
        //            counter++;
        //        }
        //    } while (counter != 0);

        System.out.println("\nCheckIn date successfully validated!");
    }

    // 2018-02-05 14:00 // checkIn
    // 2018-03-10 12:00 // bookedUntil

    private void runCmd(String cmd) {
        switch (cmd) {
            case ".cmd":
                printCmdHelper();
                break;
            case ".":
                break;
            case "..":
                break;
            case ".viewc":
                viewCustomers();
                break;
            case ".rmc":
                removeCustomer();
                break;
            case ".addc":
                addCustomer();
                break;
            case ".viewallr":
                viewRooms(Access.ADMIN, Filter.ALL);
                break;
            case ".viewr":
                viewRooms(Access.ADMIN, Filter.AVAILABLE);
                break;
            case ".addr":
                addRemoveRoom(Filter.ADD);
                break;
            case ".rmr":
                addRemoveRoom(Filter.REMOVE);
                break;
            case ".edr":
                printMenuEditRoom();
                break;
            case ".mnb":
                printMenuII(Access.ADMIN);
                break;
            case ".mnr":
                printMenuIII(Access.ADMIN);
                break;
            case ".mnc":
                printMenuIV(Access.ADMIN);
                break;
            case ".logout":
                input.nextLine();
                System.out.println("");
                cont = false;
                break;
            case ".exit":
                System.out.print("\nExiting Hotel App");
                cont = false;
                stayLoggedIn = false;
                break;
            case "...":
                break;
            case ".edrall":
                editRoom(Filter.ALL);
                break;
            case ".edrrnr":
                editRoom(Filter.RNR);
                break;
            case ".edrnob":
                editRoom(Filter.BEDS);
                break;
            case ".edrp":
                editRoom(Filter.PRICE);
                break;
            case ".edrb":
                editRoom(Filter.BALCONY);
                break;
        }
    }

    private void printCmdHelper() {
        String stringChoice;

        System.out.println("\n" + Access.ADMIN + " COMMANDS                                                                        " +
                "                                      [2018/11/29]");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[.cmd]      --  to display commands     | [.mnb]    --  to goto manage bookings menu  | [...]     --  to go back three steps           |");
        System.out.println("[.]         --  to go back one step     | [.mnr]    --  to goto manage rooms menu     | [.edrall] --  to goto edit all room attributes |");
        System.out.println("[..]        --  to go back two steps    | [.mnc]    --  to goto manage customers menu | [.edrrnr] --  to goto edit room number         |");
        System.out.println("[.viewc]    --  to view all customers   | [.logout] --  to logout current user        | [.edrnob] --  to goto edit number of beds      |");
        System.out.println("[.rmc]      --  to remove a customer    | [.exit]   --  to exit program               | [.edrp]   --  to goto edit price               |");
        System.out.println("[.addc]     --  to add a customer       | [.addr]   --  to add a room                 | [.edrb]   --  to goto edit balcony             |");
        System.out.println("[.viewr]    --  to view available rooms | [.rmr]    --  to remove a room              |                                                |");
        System.out.println("[.viewallr] --  to view all rooms       | [.edr]    --  to goto edit room             | [>>]      --  program is open to commands      |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        runCmd(stringChoice);
    }
}

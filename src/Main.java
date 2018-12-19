import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private Rooms roomDirectory = new Rooms();
    private Customers customerDirectory = new Customers();
    private Bookings bookingDirectory = new Bookings();
    private ArrayList<Booking> bookingHistory = new ArrayList<>();

    private String userName = "";
    private Date currentDate = new Date(2018, 11, 29);

    private enum Access {ADMIN, GUEST}

    private enum Filter {
        ALL, AVAILABLE, BOOKED,
        NAME, SSN, PHONE, ADDRESS,
        ADD, EDIT, REMOVE,
        RNR, BEDS, BALCONY, PRICE,
        ID, CHECKOUT
    }

    private enum Motive {
        VIEW, BOOKING, REMOVE,
        CHECKOUT, NULL
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
        hotelApp.roomDirectory.get(0).setBooked(true);
        hotelApp.roomDirectory.get(24).setBooked(true);
        hotelApp.roomDirectory.get(3).setBooked(true);
        hotelApp.roomDirectory.get(17).setBooked(true);
        hotelApp.roomDirectory.get(8).setBooked(true);
        hotelApp.roomDirectory.get(12).setBooked(true);
        Booking obj3 = new Booking(hotelApp.customerDirectory.get(0), hotelApp.roomDirectory.get(5), new Date(2018, 12, 5),
                hotelApp.roomDirectory.get(5).getPricePerNight() * 6);
        Booking obj4 = new Booking(hotelApp.customerDirectory.get(1), hotelApp.roomDirectory.get(11), new Date(2018, 12, 24),
                hotelApp.roomDirectory.get(11).getPricePerNight() * 25);
        hotelApp.bookingDirectory.add(obj3);
        hotelApp.bookingDirectory.add(obj4);
        hotelApp.roomDirectory.get(5).setBooked(true);
        hotelApp.roomDirectory.get(11).setBooked(true);
        hotelApp.bookingHistory.add(obj3);
        hotelApp.bookingHistory.add(obj4);


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
                            hotelApp.printMenuVI(user);
                            break;

                        case 7:
                            hotelApp.printMenuVII(user);
                            break;

                        case 8:
                            hotelApp.printMenuVIII(user);
                            break;

                        case 9:
                            hotelApp.printMenuIX(user);
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
            System.out.println("START MENU                            [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Help Me!                                    |  INCOMPLETE");
            System.out.println("| 2. Manage Bookings                             |");
            System.out.println("| 3. Manage Accommodations                       |");
            System.out.println("| 4. Manage Customers                            |");
            System.out.println("| 5. Account Settings                            |  NOT IMPLEMENTED");
            System.out.println("| 6. Log Out                                     |");
            System.out.println("| 7. Exit                                        |");
            System.out.println("-------------------------------------------------");
        } else { // Customer needs to be able to checkout - not admin
            System.out.println("\n" + userName);
            System.out.println(type);
            System.out.println("START MENU                            [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Make A New Booking                          |");
            System.out.println("| 2. View Available Rooms                        |");
            System.out.println("| 3. View Your Active Bookings                   |");
            System.out.println("| 4. Edit Your Personal Information              |");
            System.out.println("| 5. Manage Bookings                             |");
            System.out.println("| 6. Check Out                                   |");
            System.out.println("| 7. Help Me!                                    |  INCOMPLETE");
            System.out.println("| 8. Log Out                                     |");
            System.out.println("| 9. Exit                                        |");
            System.out.println("-------------------------------------------------");
        }
    }

    // Menu 1 and it's respective methods
    private void printMenuI(Access type) {

        if (type == Access.ADMIN) {
            helpMe(Access.ADMIN);
        }
        else if (type == Access.GUEST) {
            addBooking(Access.GUEST);
        }
    }
    private void helpMe(Access type) {
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type);
            System.out.println("-------------------------------------------------");
            System.out.println("| Help me text coming later                      |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            runCmd(stringChoice);
        } else if (type == Access.GUEST) {
            System.out.println("\n" + type);
            System.out.println("-------------------------------------------------");
            System.out.println("| Help me text coming later                      |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();
        }
    }


    private void addBooking(Access type) {
        int id, customerIndex = 99, roomNumber, roomIndex = 99;
        String stringChoice;
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = 2018, month = 11, day = 29;
        int counter = 0;
        double totalPrice;

        if (type == Access.ADMIN) {
            viewCustomers(Motive.BOOKING);
            System.out.print("Select Customer's ID: ");
            id = input.nextInt();
            for (int i = 0; i < customerDirectory.size(); i++) {
                if (customerDirectory.get(i).getCustomerId() == id) {
                    customerIndex = i;
                    break;
                }
            }
        } else if (type == Access.GUEST) {
            for (int i = 0; i < customerDirectory.size(); i++) {
                if (customerDirectory.get(i).getUserName().equals(userName)) {
                    customerIndex = i;
                    break;
                }
            }
        }
        viewRooms(Access.ADMIN, Filter.AVAILABLE, Motive.BOOKING);
        System.out.print("Select Room's RNR: ");
        roomNumber = input.nextInt();
        for (int i = 0; i < roomDirectory.size(); i++) {
            if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                roomIndex = i;
                break;
            }
        }
        // Date - currently only validates for year 2018
        System.out.println("");
        System.out.println(" ---------------     ----------------");
        System.out.println("| CHECK-IN DATE |   | CHECK-OUT DATE |");
        System.out.println("| ------------- |   | ---------------  ");
        do {
            counter = 0;
            System.out.println("| YEAR:  2018   |   | YEAR:  2018");
            System.out.print("| MONTH: 11     |   | MONTH: ");
            month = input.nextInt();
            System.out.print("| DAY:   29     |   | DAY:   ");
            day = input.nextInt();
            System.out.println("--------------------------------------");
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
            if (month < currentDate.getMonth() || month == currentDate.getMonth() && day <= currentDate.getDay()) {
                System.out.println("\nout-of-range");
                counter++;
            }
        } while (counter != 0);

        Date checkIn = currentDate; // No reservations currently
        Date checkOut = new Date(year, month, day);
        totalPrice = calculateTotalPrice(checkIn, checkOut, roomDirectory.get(roomIndex).getPricePerNight());
        Booking temp = new Booking(customerDirectory.get(customerIndex), roomDirectory.get(roomIndex), new Date(year, month, day), totalPrice);
        bookingDirectory.add(temp);
        roomDirectory.get(roomIndex).setBooked(true);
        System.out.println("\nBooking successfully added");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (type == Access.ADMIN && stringChoice.equals(".")) {
            // menu no longer exists
        } else if (stringChoice.equals("..")) {
            // Back to start
        } else if (type == Access.ADMIN && !stringChoice.equals(".") && !stringChoice.equals("..")) {
            runCmd(stringChoice);
        }
    }


    // Menu 2 and it's respective methods
    private void printMenuII(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + userName);
            System.out.println("MANAGE BOOKINGS                       [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Search For Booking                          |");
            System.out.println("| 2. Display All Bookings                        |");
            System.out.println("| 3. Edit Booking                                |  NOT IMPLEMENTED");
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
                        printMenuSearchForBookings();
                        break;
                    case 2:
                        viewBookings(Access.ADMIN, Motive.VIEW);
                        break;
                    case 3:
                        break;
                }
            }
        } else {
            // View Available Rooms
            System.out.println("\n" + userName);
            System.out.println("VIEW AVAILABLE ROOMS                  [" + currentDate.toString() + "]");
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
                        viewRooms(Access.GUEST, Filter.AVAILABLE, Motive.VIEW);
                        break;
                }
            }
        }
    }

    private void printMenuSearchForBookings() {
        int choice;
        String stringChoice;

        System.out.println("\nSEARCH FOR BOOKING(S) BY:             [" + currentDate.toString() + "]");
        System.out.println("-------------------------------------------------");
        System.out.println("| 1. Name                                        |");
        System.out.println("| 2. Booking ID                                  |");
        System.out.println("| 3. Check-Out Date                              |");
        System.out.println("| 4. Back To Main Menu                           |");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice>>");
        stringChoice = input.next();
        if (!stringChoice.equals("1") && !stringChoice.equals("2") &&
                !stringChoice.equals("3") && !stringChoice.equals("4")) {
            if (stringChoice.equals(".")) {
                printMenuII(Access.ADMIN);
            }
            runCmd(stringChoice);
        } else {
            choice = Integer.valueOf(stringChoice);

            switch (choice) {
                case 1:
                    searchForBookings(Filter.NAME);
                    break;
                case 2:
                    searchForBookings(Filter.ID);
                    break;
                case 3:
                    searchForBookings(Filter.CHECKOUT);
                    break;
                case 4:
                    break;
            }
        }


    }

    private void searchForBookings(Filter filt) {
        int choice;
        String stringChoice;
        String firstName = "default", lastName = "default";
        ArrayList<Integer> indexes = new ArrayList<>();
        int index = 0; // default value
        int id;
        int year = 2018, month, day;

        System.out.println("");
        if (filt == Filter.NAME) { // Identify by name
            System.out.print("Enter customer's name: ");
            firstName = input.next();
            lastName = input.next();

            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCustomer().getFirstName().equalsIgnoreCase(firstName) &&
                        bookingDirectory.get(i).getCustomer().getLastName().equalsIgnoreCase(lastName)) {
                    indexes.add(i);
                }
            }

        } else if (filt == Filter.ID) { // Identify by bookingId
            System.out.print("Enter booking-ID: ");
            id = input.nextInt();
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getBookingId() == id) {
                    indexes.add(i);
                    break;
                }
            }
        } else if (filt == Filter.CHECKOUT) { // Identify by check-out-date
            System.out.println("Specify check-out date");
            System.out.println("YEAR:  2018");
            System.out.print("MONTH: ");
            month = input.nextInt();
            System.out.print("DAY:   ");
            day = input.nextInt();
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCheckOut().getYear() == year &&
                        bookingDirectory.get(i).getCheckOut().getMonth() == month &&
                        bookingDirectory.get(i).getCheckOut().getDay() == day) {
                    indexes.add(i);
                }
            }
        }

        if (indexes.size() == 0) {
            System.out.println("\nThere are no booking(s) which match given search criteria");
        } else {
            System.out.println("\nSEARCH FOR BOOKING(S)                                          [" + currentDate.toString() + "]");
            System.out.println("---------------------------------------------------------------------------");
            for (int x = 0; x < indexes.size(); x++) {
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    if (i == indexes.get(x)) {
                        System.out.printf("[%d] [NAME: %s %s] [RNR: %d] [CHECK-OUT: %s] [SEK: %.2f]%n",
                                bookingDirectory.get(i).getBookingId(), bookingDirectory.get(i).getCustomer().getFirstName(),
                                bookingDirectory.get(i).getCustomer().getLastName(), bookingDirectory.get(i).getRoom().getRoomNumber(),
                                bookingDirectory.get(i).getCheckOut().toString(), bookingDirectory.get(i).getTotalPrice());
                    }
                }
            }
            System.out.println("---------------------------------------------------------------------------");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuSearchForBookings();
        } else if (stringChoice.equals("..")) {
            printMenuII(Access.ADMIN);
        } else if (stringChoice.equals("...")) {
            //  Back to startMenu
        } else if (!stringChoice.equals(".") && !stringChoice.equals("..") && !stringChoice.equals("...")) {
            runCmd(stringChoice);
        }

    }


    private void viewBookings(Access type, Motive point) {
        String stringChoice;
        int counter = 0, id = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        System.out.println("");
        if (type == Access.ADMIN) {
            if (bookingDirectory.size() >= 1) {
                System.out.println("DISPLAY BOOKINGS                                                        [" + currentDate.toString() + "]");
                System.out.println("------------------------------------------------------------------------------------");
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    System.out.printf("[%d] [NAME: %s %s] [RNR: %d] [FROM: %s - %s] [SEK: %.2f]%n",
                            bookingDirectory.get(i).getBookingId(), bookingDirectory.get(i).getCustomer().getFirstName(),
                            bookingDirectory.get(i).getCustomer().getLastName(), bookingDirectory.get(i).getRoom().getRoomNumber(),
                            bookingDirectory.get(i).getCheckIn().toString(),
                            bookingDirectory.get(i).getCheckOut().toString(), bookingDirectory.get(i).getTotalPrice());

                }
                System.out.println("------------------------------------------------------------------------------------");
            } else {
                System.out.println("Booking directory is empty");
            }

        } else if (type == Access.GUEST) {
            for (int i = 0; i < customerDirectory.size(); i++) {
                if (customerDirectory.get(i).getUserName().equals(userName)) {
                    id = customerDirectory.get(i).getCustomerId();
                    break;
                }
            }

            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCustomer().getCustomerId() == id) {
                    indexes.add(i);
                } else {
                    counter++;
                }
            }
            if (counter == bookingDirectory.size()) {
                System.out.println("You have no current bookings");
            } else {
                System.out.println("ACTIVE BOOKINGS                                                         [" + currentDate.toString() + "]");
                System.out.println("------------------------------------------------------------------------------------");
                for (int x = 0; x < indexes.size(); x++) {
                    for (int i = 0; i < bookingDirectory.size(); i++) {
                        if (i == indexes.get(x)) {
                            System.out.printf("[%d] [NAME: %s %s] [RNR: %d] [FROM: %s - %s] [SEK: %.2f]%n",
                                    bookingDirectory.get(i).getBookingId(), bookingDirectory.get(i).getCustomer().getFirstName(),
                                    bookingDirectory.get(i).getCustomer().getLastName(), bookingDirectory.get(i).getRoom().getRoomNumber(),
                                    bookingDirectory.get(i).getCheckIn().toString(), bookingDirectory.get(i).getCheckOut().toString(),
                                    bookingDirectory.get(i).getTotalPrice());
                        }
                    }
                }
                System.out.println("------------------------------------------------------------------------------------");
            }
        }
        if (point == Motive.VIEW) {
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".") && type == Access.ADMIN) {
                printMenuII(Access.ADMIN);
            } else if (stringChoice.equals("..")) {

            } else if (!stringChoice.equals(".") && !stringChoice.equals("..") && type == Access.ADMIN) {
                runCmd(stringChoice);
            }
        }
    }

    // Menu 3 and it's respective methods
    private void printMenuIII(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + userName);
            System.out.println("MANAGE ACCOMMODATIONS                 [" + currentDate.toString() + "]");
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
                        viewRooms(Access.ADMIN, Filter.AVAILABLE, Motive.VIEW);
                        break;
                    case 2:
                        viewRooms(Access.ADMIN, Filter.ALL, Motive.VIEW);
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
        } else { // View Active Bookings
            viewBookings(Access.GUEST, Motive.VIEW);
        }
    }

    private void viewRooms(Access type, Filter filt, Motive point) {
        String stringChoice;
        int counter = 0;
        int availableRoomCounter = 0;
        System.out.println("");

        if (filt == Filter.ALL) {
            if (roomDirectory.size() > 1) {
                System.out.println("VIEW ROOMS");
                System.out.println("FILTER.ALL                                          [" + currentDate.toString() + "]");
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

            } else {
                System.out.println("VIEW ROOMS");
                System.out.println("FILTER.ALL                    [" + currentDate.toString() + "]");
                System.out.printf("%s %9s %12s %15s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    System.out.printf("%2d %8d %12s %14.2f    |%n", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                            roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                }
                System.out.println("-------------------------------------------");
            }

        } else if (filt == Filter.AVAILABLE) {
            for (int i = 0; i < roomDirectory.size(); i++) {
                if (!roomDirectory.get(i).isBooked()) {
                    availableRoomCounter++;
                }
            }
            if (availableRoomCounter > 1) {
                if (point == Motive.VIEW) {
                    System.out.println("VIEW ROOMS\nFILTER.AVAILABLE                                    [" + currentDate.toString() + "]");
                } else if (point == Motive.BOOKING) {
                    System.out.println("NEW BOOKING\nSELECT ROOM                                         [" + currentDate.toString() + "]");
                }
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

            } else {
                if (point == Motive.VIEW) {
                    System.out.println("VIEW ROOMS");
                    System.out.println("FILTER.AVAILABLE              [" + currentDate.toString() + "]");
                } else if (point == Motive.BOOKING) {
                    System.out.println("ADD BOOKING                   [" + currentDate.toString() + "]");

                }
                System.out.printf("%s %9s %12s %15s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
                System.out.println("-------------------------------------------");
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (!roomDirectory.get(i).isBooked()) {
                        System.out.printf("%2d %8d %12s %14.2f    |%n", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                                roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
                    }
                }
                System.out.println("-------------------------------------------");
            }
        }
        if (point == Motive.VIEW) {
            System.out.print("Press any key >>");
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
        System.out.println("EDIT ROOM                             [" + currentDate.toString() + "]");
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
                    if (roomDirectory.get(i).getRoomNumber() == roomNumber && i != index || roomNumber <= 0 || roomNumber >= 100) {
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
            System.out.println("MANAGE CUSTOMERS                      [" + currentDate.toString() + "]");
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
                        viewCustomers(Motive.VIEW);
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
            System.out.println("\n" + userName + "\nEDIT INFORMATION                      [" + currentDate.toString() + "]");
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
                break;
            }
        }
        System.out.println("CUSTOMER DETAILS          [" + currentDate.toString() + "]");
        System.out.println("-------------------------------------");
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
            System.out.println("REMOVE CUSTOMER                                                            [" + currentDate.toString() + "]" +
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

    private void viewCustomers(Motive point) {
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
        } else if (customerDirectory.size() > 0) {
            if (point == Motive.BOOKING) {
                System.out.println("NEW BOOKING\nSELECT CUSTOMER                                                            [" + currentDate.toString() + "]");
            } else if (point == Motive.VIEW) {
                System.out.println("DISPLAY CUSTOMERS                                                          [" + currentDate.toString() + "]");
            }
            System.out.println("---------------------------------------------------------------------------------------");
            for (int i = 0; i < customerDirectory.size(); i++) {
                System.out.printf("[%s] [NAME: %s %s] [SSN: %s] [PHONE: %s] [ADDRESS: %s]%n", customerDirectory.get(i).getCustomerId(),
                        customerDirectory.get(i).getFirstName(), customerDirectory.get(i).getLastName(),
                        customerDirectory.get(i).getSSN(), customerDirectory.get(i).getTelephoneNumber(),
                        customerDirectory.get(i).getAddress());
            }
            System.out.println("---------------------------------------------------------------------------------------");
            if (point == Motive.BOOKING) {
                // Move to booking method
            } else if (point == Motive.VIEW) {
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
    }

    // Menu 5 and it's respective methods
    private void printMenuV(Access type) {
        String stringChoice;
        int choice;

        if (type == Access.ADMIN) { // Account Settings

        } else if (type == Access.GUEST) { // Edit Booking

            System.out.println("\nMANAGE BOOKINGS                       [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Edit Booking                                |  NOT IMPLEMENTED");
            System.out.println("| 2. View Your Booking History                   |  NOT IMPLEMENTED");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();

            switch (stringChoice) {
                case "1": // Edit Booking
                    break;
                case "2": // View Booking History / RD/LD to Binary File
                    break;
                case "3":
                    break;
            }

        }
    }
    private void editBooking() {

    }
    private void accountSettings() {

    }

    private void printMenuVI(Access type) { // LOGOUT / CHECKOUT
        String stringChoice;

        if (type == Access.ADMIN) { // Logout
            logout();

        } else if (type == Access.GUEST) { // Check Out
            checkout();

        }
    }
    private void checkout() {
        int bookingId, index = 0, counter = 0;
        String stringChoice;
        viewBookings(Access.GUEST, Motive.BOOKING);
        for (int i = 0; i < bookingDirectory.size(); i++) {
            if (bookingDirectory.get(i).getCustomer().getUserName().equals(userName)) {
                counter++;
            }
        }
        if (counter >= 1) {

            System.out.print("Select Booking ID: ");
            bookingId = input.nextInt();
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getBookingId() == bookingId) {
                    index = i;
                    break;
                }
            }
            bookingDirectory.remove(index);
            System.out.println("\nYou have successfully been checked out");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();

    }

    private void logout() {
        System.out.println("");
        input.nextLine();
        cont = false;
    }

    private void exit() {
        System.out.print("Exiting Hotel App");
        cont = false;
        stayLoggedIn = false;
    }


    private void printMenuVII(Access type) { // EXIT /

        if (type == Access.ADMIN) {
            exit();
        } else if (type == Access.GUEST) {
            helpMe(Access.GUEST);
        }
    }

    private void printMenuVIII(Access type) {

        if (type == Access.ADMIN) {

        } else if (type == Access.GUEST) {
            logout();
        }
    }

    private void printMenuIX(Access type) {

        System.out.println("");
        if (type == Access.ADMIN) {

        }
        if (type == Access.GUEST) {
            exit();
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

    private double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight) {
        int[] daysPerMonth =
                {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totalDays = 0;
        int remainingDaysInMonth;

        if (checkIn.getMonth() == checkOut.getMonth()) {
            totalDays = checkOut.getDay() - checkIn.getDay();
        }

        if (checkIn.getMonth() == checkOut.getMonth() - 1) {
            remainingDaysInMonth = daysPerMonth[checkIn.getMonth()] - checkIn.getDay(); // integer value of number of remaining days in the month
            totalDays = remainingDaysInMonth + checkOut.getDay();
        }

        if (checkIn.getMonth() == checkOut.getMonth() - 2) {
            remainingDaysInMonth = daysPerMonth[checkIn.getMonth()] - checkIn.getMonth();
            totalDays = remainingDaysInMonth + daysPerMonth[checkIn.getMonth() + 1] + checkOut.getDay();
        }
        System.out.printf("%nTotal Stay: %s day(s)%nTotal Price: %.2f SEK%n", totalDays, (totalDays * pricePerNight));

        return totalDays * pricePerNight;
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
                viewCustomers(Motive.VIEW);
                break;
            case ".rmc":
                removeCustomer();
                break;
            case ".addc":
                addCustomer();
                break;
            case ".viewallr":
                viewRooms(Access.ADMIN, Filter.ALL, Motive.VIEW);
                break;
            case ".viewr":
                viewRooms(Access.ADMIN, Filter.AVAILABLE, Motive.VIEW);
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
                logout();
                break;
            case ".exit":
                exit();
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
            case ".viewb":
                viewBookings(Access.ADMIN, Motive.VIEW);
                break;
            case ".sfb":
                printMenuSearchForBookings();
                break;
        }
    }

    private void printCmdHelper() {
        String stringChoice;

        System.out.print("\n" + Access.ADMIN);
        System.out.println(" COMMANDS                                                                                                                 [" + currentDate.toString() + "]");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[.viewc]    --  to view all customers      | [.nb]          --  to goto new booking           | [>>]         --  commands are usable      |");
        System.out.println("[.addc]     --  to add a customer          | [.mnb]         --  to goto manage bookings menu  | [.]          --  to go back one step      |");
        System.out.println("[.viewr]    --  to view available rooms    | [.mnr]         --  to goto manage rooms menu     | [..]         --  to go back two steps     |");
        System.out.println("[.viewallr] --  to view all rooms          | [.mnc]         --  to goto manage customers menu | [...]        --  to go back three steps   |");
        System.out.println("[.addr]     --  to add a room              | [.viewb]       --  to view  bookings             | [.exit]      --  to exit program          |");
        System.out.println("[.rmr]      --  to remove a room           |                                                  | [.edrb]      --  to edit balcony          |");
        System.out.println("[.edall]    --  to edit all attributes     | [.edrp]        --  to edit a rooms price         | [.edrnob]    --  to edit number of beds   |");
        System.out.println("[.edrrnr]   --  to edit RNR                |                                                  | [.logout]    --  to logout current user   |");
        System.out.println("[.sfb]      --  to goto search for booking |                                                  |                                           |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        runCmd(stringChoice);
    }
}

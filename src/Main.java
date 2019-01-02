import java.util.*;

public class Main {
    private RoomGenerator roomDirectory = new RoomGenerator();
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private ArrayList<Customer> customerDirectory = new ArrayList<>();
    private ArrayList<Booking> bookingDirectory = new ArrayList<>();
    private ArrayList<Object> bookingHistory = new ArrayList<>();
    private String userName = "";
    private Date currentDate = new Date(2018, 11, 29);

    private enum Access {ADMIN, GUEST}

    private enum Filter {
        ALL, AVAILABLE, BOOKED,
        NAME, SSN, PHONE, ADDRESS,
        ADD, EDIT, REMOVE,
        RNR, BEDS, BALCONY, PRICE,
        ID, CHECKOUT,
        ROOM,
        USERNAME, PASSWORD
    }

    private enum Motive {
        VIEW, BOOKING, REMOVE,
        CHECKOUT, NULL,
        EDIT
    }

    private boolean cont;
    private boolean stayLoggedIn = true;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main hotelApp = new Main();
        hotelApp.listOfUsers.add(new User("admin", "admin", true));
        hotelApp.listOfUsers.add(new User("gitRalle", "1234", true));
        hotelApp.listOfUsers.add(new User("afgsuna", "1234", true));

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
            System.out.println("\n" + type + " " + userName);
            System.out.println("START MENU                            [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Help Me!                                    |");
            System.out.println("| 2. Manage Bookings                             |");
            System.out.println("| 3. Manage Accommodations                       |");
            System.out.println("| 4. Manage Customers                            |");
            System.out.println("| 5. Manage Accounts                             |");
            System.out.println("| 6. Log Out                                     |");
            System.out.println("| 7. Exit                                        |");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("\n" + type + " " + userName);
            System.out.println("START MENU                            [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Make A New Booking                          |");
            System.out.println("| 2. View Available Rooms                        |");
            System.out.println("| 3. View Your Active Bookings                   |");
            System.out.println("| 4. Edit Your Personal Information              |");
            System.out.println("| 5. Manage Bookings                             |");
            System.out.println("| 6. Check Out                                   |");
            System.out.println("| 7. Help Me!                                    |");
            System.out.println("| 8. Log Out                                     |");
            System.out.println("| 9. Exit                                        |");
            System.out.println("-------------------------------------------------");
        }
    }
    private void printMenuI(Access type) {

        if (type == Access.ADMIN) {
            printHelpMenuStart(Access.ADMIN);
        } else if (type == Access.GUEST) {
            addBooking(Access.GUEST, Motive.BOOKING);
        }
    }

    private void printHelpMenuStart(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU                             [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Description of General Hotel Features       |");
            System.out.println("| 2. Description of Booking Features             | ");
            System.out.println("| 3. Description of Room Features                |");
            System.out.println("| 4. Description of Customer Features            |");
            System.out.println("| 5. Description of Account/User Features        |");
            System.out.println("| 6. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();

            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
            !stringChoice.equals("4") && !stringChoice.equals("5") && !stringChoice.equals("6")) {
                runCmd(stringChoice);

            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        printHelpMenuGeneral(Access.ADMIN);
                        break;
                    case 2:
                        printHelpMenuBooking(Access.ADMIN);
                        break;
                    case 3:
                        printHelpMenuAccommodations(Access.ADMIN);
                        break;
                    case 4:
                        printHelpMenuCustomer(Access.ADMIN);
                        break;
                    case 5:
                        printHelpMenuAccount(Access.ADMIN);
                        break;
                    case 6:
                        break;
                }
            }
        }

        else if (type == Access.GUEST) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU                             [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Description of General Hotel Features       |");
            System.out.println("| 2. Description of Booking Features             | ");
            System.out.println("| 3. Description of Room Features                |");
            System.out.println("| 4. Description of Customer Features            |");
            System.out.println("| 5. Description of Account/User Features        |");
            System.out.println("| 6. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();

            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3") &&
            !stringChoice.equals("4") && !stringChoice.equals("5") && !stringChoice.equals("6")) {
                // check for ./../...

            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        printHelpMenuGeneral(Access.GUEST);
                        break;
                    case 2:
                        printHelpMenuBooking(Access.GUEST);
                        break;
                    case 3:
                        printHelpMenuAccommodations(Access.GUEST);
                        break;
                    case 4:
                        printHelpMenuCustomer(Access.GUEST);
                        break;
                    case 5:
                        printHelpMenuAccount(Access.GUEST);
                        break;
                    case 6:
                        break;
                }
            }
        }
    }

    private void addBooking(Access type, Motive point) {
        int id, customerIndex = 99, roomNumber, roomIndex = 99, howManyBookedRoomsCounter = 0;
        String stringChoice;
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = 2018, month = 11, day = 29;
        int counter = 0;
        double totalPrice;

        if (type == Access.ADMIN) { // Is no longer a presented user option
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
        for (int i = 0; i < roomDirectory.size(); i++) {
            if (roomDirectory.get(i).isBooked()) {
                howManyBookedRoomsCounter++;
            }
        }
        if (howManyBookedRoomsCounter == roomDirectory.size()) {

        } else {

            do {
                do {
                    System.out.print("Select Room's RNR: ");
                    roomNumber = input.nextInt();
                    if (roomNumber <= 0 || roomNumber > roomDirectory.size()) {
                        System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                    }
                } while (roomNumber <= 0 || roomNumber > roomDirectory.size());
                for (int i = 0; i < roomDirectory.size(); i++) {
                    if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                        roomIndex = i;
                        break;
                    }
                }
                if (roomDirectory.get(roomIndex).isBooked()) {
                    System.out.println("\nRNR [" + roomNumber + "] is not available for booking");
                }
            } while (roomDirectory.get(roomIndex).isBooked());
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

            // readWrite
            bookingHistory = ReadWriteObject.readObject();
            bookingHistory.add(temp);
            ReadWriteObject.writeObject(bookingHistory);
        }
        if (point == Motive.BOOKING) {
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
    }


    // Menu 2 and it's respective methods
    private void printMenuII(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("MANAGE BOOKINGS                       [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Display All Bookings                        |");
            System.out.println("| 2. Search For Booking                          |");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3")) {
                runCmd(stringChoice);
            } else {
                    choice = Integer.valueOf(stringChoice);


                switch (choice) {
                    case 1:
                        viewBookings(Access.ADMIN, Motive.VIEW);
                        break;
                    case 2:
                        printMenuSearchForBookings();
                        break;
                    case 3:
                        break;
                }
                }
            }
         else {
            // View Available Rooms
            System.out.println("\n" + type + " " + userName);
            System.out.println("VIEW AVAILABLE ROOMS                  [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. View All Currently Available Rooms          |");
            System.out.println("| 2. View Available Rooms Within Time Period     |");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") && !stringChoice.equals("3")) {
                // back to startMenu
            } else {
                    choice = Integer.valueOf(stringChoice);

                    switch (choice) {
                        case 1:
                            viewRooms(Access.GUEST, Filter.AVAILABLE, Motive.VIEW);
                            break;
                        case 2:
                            viewRoomsWithinDates();
                            break;
                    }
                }
            }
        }


    private void printMenuSearchForBookings() {
        int choice;
        String stringChoice;

        System.out.println("\n" + Access.ADMIN + " " + userName);
        System.out.println("SEARCH FOR BOOKINGS BY:               [" + currentDate.toString() + "]");
        System.out.println("-------------------------------------------------");
        System.out.println("| 1. Name                                        |");
        System.out.println("| 2. Booking ID                                  |");
        System.out.println("| 3. Check-Out Date                              |");
        System.out.println("| 4. Back To Main Menu                           |");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice >>");
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
            System.out.println("\nVIEW BOOKINGS");
            System.out.println("MOTIVE.SEARCH                                                              [" + currentDate.toString() + "]");
            System.out.println("---------------------------------------------------------------------------------------");
            for (int x = 0; x < indexes.size(); x++) {
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    if (i == indexes.get(x)) {
                        System.out.printf("[%d] [NAME: %s %s] [RNR: %d] [FROM: %s - %s] [SEK: %.2f]%n",
                                bookingDirectory.get(i).getBookingId(), bookingDirectory.get(i).getCustomer().getFirstName(),
                                bookingDirectory.get(i).getCustomer().getLastName(), bookingDirectory.get(i).getRoom().getRoomNumber(),
                                bookingDirectory.get(i).getCheckIn().toString(),
                                bookingDirectory.get(i).getCheckOut().toString(), bookingDirectory.get(i).getTotalPrice());
                    }
                }
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuSearchForBookings();
        } else if (stringChoice.equals("..")) {
            printMenuII(Access.ADMIN);
        } else if (stringChoice.equals("...")) {
            //  Back to startMenu
        } else if (stringChoice.equals(".r") && filt == Filter.CHECKOUT) {
            searchForBookings(Filter.CHECKOUT);
        } else if (stringChoice.equals(".r") && filt == Filter.NAME) {
            searchForBookings(Filter.NAME);
        } else if (stringChoice.equals(".r") && filt == Filter.ID) {
            searchForBookings(Filter.ID);
        } else if (!stringChoice.equals(".") && !stringChoice.equals("..") && !stringChoice.equals("...") && !stringChoice.equals("r")) {
            runCmd(stringChoice);
        }

    }


    private void viewBookings(Access type, Motive point) {
        String stringChoice;
        int counter = 0, id = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        String[] array = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ",
        "           ", "            ", "             ", "              ", "               "};
        int nameLength = 0;
        int largetBookingId = 0;

        System.out.println("");
        if (type == Access.ADMIN) {
            if (bookingDirectory.size() >= 1) {
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    if (bookingDirectory.get(i).getCustomer().getFirstName().length() +
                        bookingDirectory.get(i).getCustomer().getLastName().length() > nameLength) {
                        nameLength = bookingDirectory.get(i).getCustomer().getFirstName().length() + bookingDirectory.get(i).getCustomer().getLastName().length();
                    }
                }
                nameLength = nameLength + 1;
                System.out.println("VIEW BOOKINGS                                                              [" + currentDate.toString() + "]");
                System.out.println("---------------------------------------------------------------------------------------");
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    System.out.printf("[%d]%s[NAME: %s %s]%s[RNR: %d]%s[FROM: %s - %s] [SEK: %.2f]%n",
                            bookingDirectory.get(i).getBookingId(), bookingDirectory.get(bookingDirectory.size() - 1).getBookingId() >= 10 &&
                                    bookingDirectory.get(i).getBookingId() < 10 ? "  " : " ",
                            bookingDirectory.get(i).getCustomer().getFirstName(), bookingDirectory.get(i).getCustomer().getLastName(),
                            array[nameLength - (bookingDirectory.get(i).getCustomer().getFirstName().length() + bookingDirectory.get(i).getCustomer().getLastName().length())],
                            bookingDirectory.get(i).getRoom().getRoomNumber(), bookingDirectory.get(i).getRoom().getRoomNumber() >= 10 ? " " : "  ",
                            bookingDirectory.get(i).getCheckIn().toString(), bookingDirectory.get(i).getCheckOut().toString(),
                            bookingDirectory.get(i).getTotalPrice());

                }
                System.out.println("---------------------------------------------------------------------------------------");
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

            for (int i = 0; i < indexes.size(); i++) {
                if (bookingDirectory.get(indexes.get(i)).getBookingId() > largetBookingId) {
                    largetBookingId = bookingDirectory.get(indexes.get(i)).getBookingId();
                }
            }
            if (counter == bookingDirectory.size()) {
                System.out.println("You have no current bookings");
            } else {
                if (point == Motive.VIEW) {
                    System.out.println("VIEW ACTIVE BOOKINGS\nMOTIVE." + point + "                                                             [" + currentDate.toString() + "]");
                } else if (point == Motive.EDIT) {
                    System.out.println("VIEW ACTIVE BOOKINGS\nMOTIVE." + point + "                                                             [" + currentDate.toString() + "]");
                } else if (point == Motive.CHECKOUT) {
                    System.out.println("VIEW ACTIVE BOOKINGS\nMOTIVE." + point + "                                                         [" + currentDate.toString() + "]");
                }
                System.out.println("------------------------------------------------------------------------------------");
                for (int x = 0; x < indexes.size(); x++) {
                    for (int i = 0; i < bookingDirectory.size(); i++) {
                        if (i == indexes.get(x)) {
                            System.out.printf("[%d]%s[NAME: %s %s] [RNR: %d]%s[FROM: %s - %s] [SEK: %.2f]%n",
                                    bookingDirectory.get(i).getBookingId(), largetBookingId >= 10 &&
                                    bookingDirectory.get(i).getBookingId() < 10 ? "  " : " ",
                                    bookingDirectory.get(i).getCustomer().getFirstName(),
                                    bookingDirectory.get(i).getCustomer().getLastName(), bookingDirectory.get(i).getRoom().getRoomNumber(),
                                    bookingDirectory.get(i).getRoom().getRoomNumber() >= 10 ? " " : "  ",
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
            System.out.println("\n" + type + " " + userName);
            System.out.println("MANAGE ACCOMMODATIONS                 [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. View Available Rooms                        |");
            System.out.println("| 2. View All Rooms                              |");
            System.out.println("| 3. Add Room                                    |");
            System.out.println("| 4. Edit Room                                   |");
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
                        addRemoveRoom(Filter.ADD);
                        break;
                    case 4:
                        printMenuEditRoom();
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

    private void viewRoomsWithinDates() {
        String stringChoice;
        int counter = 0;
        int checkInYear = 2018, checkOutYear = 2018;
        int checkInMonth, checkOutMonth;
        int checkInDay, checkOutDay;
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        System.out.println("");

        do {
            System.out.println(" --------------- ");
            System.out.println("| CHECK-IN DATE |");
            System.out.println("| ------------- |");
            counter = 0;
            System.out.println("| YEAR:  2018");
              System.out.print("| MONTH: ");
            checkInMonth = input.nextInt();
              System.out.print("| DAY:   ");
            checkInDay = input.nextInt();
            System.out.println(" --------------- ");
            if (checkInMonth <= 0 || checkInMonth > 12) {
                System.out.println("\n Month must be 1-12");
                counter++;
            }
            if (checkInDay <= 0 || checkInDay > daysPerMonth[checkInMonth] && !(checkInMonth == 2 && checkInDay == 29)) {
                System.out.println("\n day (" + checkInDay + ") out-of-range for specified month");
                counter++;
            }
            if (checkInMonth == 2 & checkInDay == 29 && !(checkInYear % 400 == 0 || (checkInYear % 4 == 0 && checkInYear % 100 != 0))) {
                System.out.println("\n day ( " + checkInDay + ") out of range for the specified month and year");
                counter++;
            }
            if (checkInMonth < currentDate.getMonth() || checkInMonth == currentDate.getMonth() && checkInDay <= currentDate.getDay()) {
                System.out.println("\n out-of-range");
                counter++;
            }
        } while (counter != 0);

        // Irrelevant seeing as we don't allow for any reservations.
        do {
            System.out.println(" ---------------- ");
            System.out.println("| CHECK-OUT DATE |");
            System.out.println("| -------------- |");
            counter = 0;
            System.out.println("| YEAR:  2018");
              System.out.print("| MONTH: ");
            checkOutMonth = input.nextInt();
              System.out.print("| DAY:   ");
            checkOutDay = input.nextInt();
            System.out.println(" ---------------- ");
            if (checkOutMonth <= 0 || checkOutMonth > 12) {
                System.out.println("\nMonth must be 1-12");
                counter++;
            }
            if (checkOutDay <= 0 || checkOutDay > daysPerMonth[checkOutMonth] && !(checkOutMonth == 2 && checkOutDay == 29)) {
                System.out.println("\nday (" + checkOutDay + ") out-of-range for specified month");
                counter++;
            }
            if (checkOutMonth == 2 & checkOutDay == 29 && !(checkOutYear % 400 == 0 || (checkOutYear % 4 == 0 && checkOutYear % 100 != 0))) {
                System.out.println("\nday ( " + checkOutDay + ") out of range for the specified month and year");
                counter++;
            }
            if (checkOutMonth < checkInMonth || checkInMonth == checkOutMonth && checkInDay > checkOutDay) {
                System.out.println("\nout-of-range");
                counter++;
            }
        } while (counter != 0);

        Room[] sortByRnr = new Room[100];
        Date fromThisDate = new Date(checkInYear, checkInMonth, checkInDay);
        Date toThisDate = new Date(checkOutYear, checkOutMonth, checkOutDay);

        // 100 %
        for (int i = 0; i < roomDirectory.size(); i++) {
            if (!roomDirectory.get(i).isBooked()) {
                sortByRnr[roomDirectory.get(i).getRoomNumber()] = roomDirectory.get(i);
            }
        }

        //
        for (int i = 0; i < bookingDirectory.size(); i++) {
            if (bookingDirectory.get(i).getCheckOut().getMonth() == fromThisDate.getMonth() && bookingDirectory.get(i).getCheckOut().getDay() <= fromThisDate.getDay() ||
                bookingDirectory.get(i).getCheckOut().getMonth() == fromThisDate.getMonth() - 1) {
                sortByRnr[bookingDirectory.get(i).getRoom().getRoomNumber()] = bookingDirectory.get(i).getRoom();
            }
        }

        // Output!
        System.out.println("\nVIEW ROOMS");
        System.out.println("FILTER.AVAILABLE FROM");
        System.out.println(fromThisDate.toString() + " - " + toThisDate.toString() + "                             [" + currentDate.toString() + "]");
        System.out.printf("%s %5s %7s %11s", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
        System.out.printf("%8s %6s %7s %7s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < sortByRnr.length; i++) {
            if (sortByRnr[i] != null) {
                System.out.printf("%2d %5d %6s %10.2f    |    ", sortByRnr[i].getRoomNumber(), sortByRnr[i].getNumberOfBeds(),
                        sortByRnr[i].isHasBalcony() ? "Yes" : "No", sortByRnr[i].getPricePerNight());
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
        // clear array
        for (int i = 0; i < sortByRnr.length; i++) {
            sortByRnr[i] = null;
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuII(Access.GUEST);
        }
    }

    private void viewRooms(Access type, Filter filt, Motive point) {
        String stringChoice;
        int counter = 0;
        int availableRoomCounter = 0;
        System.out.println("");

        if (filt == Filter.ALL) {
            if (roomDirectory.size() > 1) {
                if (point == Motive.VIEW) {
                    System.out.println("VIEW ROOMS");
                    System.out.println("FILTER.ALL                                          [" + currentDate.toString() + "]");
                } else if (point == Motive.EDIT) {
                    System.out.println("EDIT ROOM\nSELECT ROOM                                         [" + currentDate.toString() + "]");
                } else if (point == Motive.REMOVE) {
                    System.out.println("REMOVE ROOM\nSELECT ROOM                                         [" + currentDate.toString() + "]");
                }
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
                } else if (point == Motive.EDIT) {
                    System.out.println("EDIT BOOKING\nSELECT NEW ROOM                                     [" + currentDate.toString() + "]");
                } else if (point == Motive.REMOVE) {
                    System.out.println("REMOVE ROOM\nSELECT ROOM                                         [" + currentDate.toString() + "]");
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

            } else if (availableRoomCounter == 1) {
                if (point == Motive.VIEW) {
                    System.out.println("VIEW ROOMS");
                    System.out.println("FILTER.AVAILABLE              [" + currentDate.toString() + "]");
                } else if (point == Motive.BOOKING) {
                    System.out.println("ADD BOOKING                   [" + currentDate.toString() + "]");

                } else if (point == Motive.EDIT) {
                    System.out.println("SELECT NEW ROOM               [" + currentDate.toString() + "]");
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
            } else if (availableRoomCounter == 0) {
                System.out.println("There are no currently available rooms");
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

        if (filt == Filter.ADD) {
            System.out.println("");
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
            System.out.println("\nRoom successfully added to room directory");

        } else if (filt == Filter.REMOVE) {
            index = 99; // variable won't ever take on default value
            viewRooms(Access.ADMIN, Filter.ALL, Motive.REMOVE);
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
            if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                roomDirectory.remove(index);
                System.out.println("\nRoom successfully removed");
            }
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuIII(Access.ADMIN);
        } else if (stringChoice.equals("..")) {
            // back to startMenu
        } else if (stringChoice.equals("...")) {

        } else if (stringChoice.equals(".r") && filt == Filter.ADD) {
            addRemoveRoom(Filter.ADD);
        } else if (stringChoice.equals(".r") && filt == Filter.REMOVE) {
            addRemoveRoom(Filter.REMOVE);
        } else {
            runCmd(stringChoice);
        }
    }

    private void printMenuEditRoom() {
        int choice;
        String stringChoice;

        System.out.println("\n" + Access.ADMIN + " " + userName);
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
        String stringChoice, temp;
        int roomNumber, numberOfBeds, counter, index = 99;
        boolean hasBalcony = false;
        double pricePerNight;

        viewRooms(Access.ADMIN, Filter.ALL, Motive.EDIT);
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
            temp = input.next();
            if (temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("true")) {
                hasBalcony = true;
            }

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
            System.out.println("\nRoom successfully updated");
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
            System.out.println("\nRoom successfully updated");
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
            System.out.println("\nRoom successfully updated");
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
            temp = input.next();
            if (temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("true")) {
                hasBalcony = true;
            }
            roomDirectory.get(index).setHasBalcony(hasBalcony);
            System.out.println("\nRoom successfully updated");
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
            System.out.println("\nRoom successfully updated");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuEditRoom();
        } else if (stringChoice.equals("..")) {
            printMenuIII(Access.ADMIN);
        } else if (stringChoice.equals("...")) {
            // back to startMenu
        } else if (stringChoice.equals(".r") && filt == Filter.RNR) {
            editRoom(Filter.RNR);
        } else if (stringChoice.equals(".r") && filt == Filter.BEDS) {
            editRoom(Filter.BEDS);
        } else if (stringChoice.equals(".r") && filt == Filter.BALCONY) {
            editRoom(Filter.BALCONY);
        } else if (stringChoice.equals(".r") && filt == Filter.PRICE) {
            editRoom(Filter.PRICE);
        } else if (stringChoice.equals(".r") && filt == Filter.ALL) {
            editRoom(Filter.ALL);
        } else {
            runCmd(stringChoice);
        }
    }

    // Menu 4 and its respective methods
    private void printMenuIV(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("MANAGE CUSTOMERS                      [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Display All Customers                       |");
            System.out.println("| 2. Add Customer                                |");
            System.out.println("| 3. Remove Customer                             |");
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
                        viewCustomers(Motive.VIEW);
                        break;
                    case 2:
                        addCustomer();
                        break;
                    case 3:
                        removeCustomer();
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
            System.out.println("\n" + type + " " + userName);
            System.out.println("EDIT INFORMATION                     [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Display Your Personal Information           |");
            System.out.println("| 2. Update Your Phone Number                    |");
            System.out.println("| 3. Update Your Your Address                    |");
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
                        viewCustomerInfo();
                        break;
                    case 2:
                        updateCustomerInfo(Filter.PHONE);
                        break;
                    case 3:
                        updateCustomerInfo(Filter.ADDRESS);
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
        System.out.print("\nPersonal information successfully updated\nPress any key >>");
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
        System.out.println("\nCustomer successfully added");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuIV(Access.ADMIN);
        } else if (stringChoice.equals("..")) {

        } else if (stringChoice.equals(".r")) {
            addCustomer();
        } else {
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
                //  back to startMenu
            } else if (stringChoice.equals(".r")) {
                removeCustomer();
            } else {
                runCmd(stringChoice);
            }
        }
    }

    private void viewCustomers(Motive point) {
        String stringChoice;
        String[] array = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ",
                "           ", "            ", "             ", "              ", "               "};
        int nameLength = 0;

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

            for (int i = 0; i < customerDirectory.size(); i++) {
                if (customerDirectory.get(i).getFirstName().length() + customerDirectory.get(i).getLastName().length() > nameLength) {
                    nameLength = customerDirectory.get(i).getFirstName().length() + customerDirectory.get(i).getLastName().length();
                }
            }
            nameLength = nameLength + 1;
            if (point == Motive.BOOKING) {
                System.out.println("NEW BOOKING\nSELECT CUSTOMER                                                            [" + currentDate.toString() + "]");
            } else if (point == Motive.VIEW) {
                System.out.println("VIEW CUSTOMERS                                                             [" + currentDate.toString() + "]");
            }
            System.out.println("---------------------------------------------------------------------------------------");
            for (int i = 0; i < customerDirectory.size(); i++) {
                System.out.printf("[%d]%s[NAME: %s %s]%s[SSN: %s] [PHONE: %s] [ADDRESS: %s]%n",
                        customerDirectory.get(i).getCustomerId(), customerDirectory.get(customerDirectory.size() - 1).getCustomerId() >= 10 &&
                                customerDirectory.get(i).getCustomerId() < 10 ? "  " : " ",
                        customerDirectory.get(i).getFirstName(), customerDirectory.get(i).getLastName(),
                        array[nameLength - (customerDirectory.get(i).getFirstName().length() + customerDirectory.get(i).getLastName().length())],
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
            System.out.println("\n" + type + " " + userName);
            System.out.println("MANAGE ACCOUNTS                       [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Display All Accounts                        |");
            System.out.println("| 2. Change Your Password                        |");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();
            if (!stringChoice.equals("1") && !stringChoice.equals("2") &&
                    !stringChoice.equals("3")) {
                runCmd(stringChoice);

            } else {
                choice = Integer.valueOf(stringChoice);

                switch (choice) {
                    case 1:
                        viewAccounts();
                        break;
                    case 2:
                        configureAccount(Filter.PASSWORD);
                        break;
                    case 3:
                        break;
                }
            }

        } else if (type == Access.GUEST) { // Edit Booking
            System.out.println("\n" + type + " " + userName);
            System.out.println("MANAGE BOOKINGS                       [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Display Your Booking History                |");
            System.out.println("| 2. Edit Booking                                |");
            System.out.println("| 3. Back To Main Menu                           |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            stringChoice = input.next();

            switch (stringChoice) {
                case "1":
                    viewBookingHistory();
                    break;
                case "2":
                    printMenuEditBooking();
                    break;
                case "3":
                    break;
            }

        }
    }


    private void viewBookingHistory() {
        String stringChoice;

        System.out.println("");
        bookingHistory = ReadWriteObject.readObject();
        if (bookingHistory.size() != 0) {
            System.out.println("VIEW BOOKING HISTORY\nREAD FROM FILE                                                          [" + currentDate.toString() + "]");
            System.out.println("------------------------------------------------------------------------------------");
            for (int i = 0; i < bookingHistory.size(); i++) {
                if (bookingHistory.get(i) instanceof Booking && ((Booking) bookingHistory.get(i)).getCustomer().getUserName().equals(userName)) {
                    System.out.printf("[NAME: %s %s] [RNR: %d]%s[FROM: %s - %s] [SEK: %.2f]%n",
                            ((Booking) bookingHistory.get(i)).getCustomer().getFirstName(), ((Booking) bookingHistory.get(i)).getCustomer().getLastName(),
                            ((Booking) bookingHistory.get(i)).getRoom().getRoomNumber(), ((Booking) bookingHistory.get(i)).getRoom().getRoomNumber() >= 10 ? " " : "  ",
                            ((Booking) bookingHistory.get(i)).getCheckIn().toString(),
                            ((Booking) bookingHistory.get(i)).getCheckOut().toString(), ((Booking) bookingHistory.get(i)).getTotalPrice());
                }
            }
            System.out.println("------------------------------------------------------------------------------------");
        } else {
            System.out.println("You have no history of any bookings");
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuV(Access.GUEST);
        }
    }

    private void printMenuEditBooking() {
        String stringChoice;
        int choice;

        System.out.println("\n" + Access.GUEST + " " + userName);
        System.out.println("EDIT BOOKING                          [" + currentDate.toString() + "]");
        System.out.println("-------------------------------------------------");
        System.out.println("| 1. Change Room                                 |");
        System.out.println("| 2. Change Check-Out Date                       |");
        System.out.println("| 3. Discard Booking And Add a New One           |");
        System.out.println("| 4. Back To Main Menu                           |");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice >>");
        stringChoice = input.next();
        if (!stringChoice.equals("1") && !stringChoice.equals("2") &&
                !stringChoice.equals("3") && !stringChoice.equals("4")) {
            if (stringChoice.equals(".")) {
                printMenuV(Access.GUEST);
            }
            if (stringChoice.equals("..")) {
                // Back to startMenu
            }
        } else {
            choice = Integer.valueOf(stringChoice);

            switch (choice) {
                case 1: // Change Room
                    editBooking(Filter.ROOM);
                    break;
                case 2: // Change Check-Out Date
                    editBooking(Filter.CHECKOUT);
                    break;
                case 3: // New Booking
                    editBooking(Filter.ALL);
                    break;
                case 4:
                    break;
            }
        }
    }

    private void editBooking(Filter filt) {
        String stringChoice;
        int roomNumber, roomIndex = 99, counter = 0;
        int bookingId, numberOfBookings = 0, bookingIndex = 99;
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = 2018, month, day;
        double pricePerNight, totalPrice;

        if (filt == Filter.ROOM) {
            viewBookings(Access.GUEST, Motive.EDIT);
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCustomer().getUserName().equals(userName)) {
                    numberOfBookings++;
                }
            }
            if (numberOfBookings >= 1) {
                do {
                    System.out.print("Select Booking ID: ");
                    bookingId = input.nextInt();
                    for (int i = 0; i < bookingDirectory.size(); i++) {
                        if (bookingDirectory.get(i).getBookingId() == bookingId) {
                            bookingIndex = i;
                            break;
                        }
                    }
                    if (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName)) {
                        System.out.println("\nbID [" + bookingId + "] does not belong to user [" + userName + "]");
                    }
                } while (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName));
                viewRooms(Access.GUEST, Filter.AVAILABLE, Motive.EDIT);
                do {
                    do {
                        System.out.print("Select Room's RNR: ");
                        roomNumber = input.nextInt();
                        if (roomNumber <= 0 || roomNumber > roomDirectory.size()) {
                            System.out.println("\nRNR [" + roomNumber + "] does not exist in room directory");
                        }
                    } while (roomNumber <= 0 || roomNumber > roomDirectory.size());
                    for (int i = 0; i < roomDirectory.size(); i++) {
                        if (roomDirectory.get(i).getRoomNumber() == roomNumber) {
                            roomIndex = i;
                            break;
                        }
                    }
                    if (roomDirectory.get(roomIndex).isBooked()) {
                        System.out.println("\nRNR [" + roomNumber + "] is not available for booking");
                    }
                } while (roomDirectory.get(roomIndex).isBooked());
                // roomIndex
                // bookingIndex
                bookingDirectory.get(bookingIndex).getRoom().setBooked(false);
                bookingDirectory.get(bookingIndex).setRoom(roomDirectory.get(roomIndex));
                totalPrice = calculateTotalPrice(bookingDirectory.get(bookingIndex).getCheckIn(), bookingDirectory.get(bookingIndex).getCheckOut(),
                        bookingDirectory.get(bookingIndex).getRoom().getPricePerNight());
                bookingDirectory.get(bookingIndex).setTotalPrice(totalPrice);
                System.out.println("\nBooking successfully edited");
            }
        } else if (filt == Filter.CHECKOUT) {
            viewBookings(Access.GUEST, Motive.EDIT);
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCustomer().getUserName().equals(userName)) {
                    numberOfBookings++;
                }
            }
            if (numberOfBookings >= 1) {
                do {
                    System.out.print("Select Booking ID: ");
                    bookingId = input.nextInt();
                    for (int i = 0; i < bookingDirectory.size(); i++) {
                        if (bookingDirectory.get(i).getBookingId() == bookingId) {
                            bookingIndex = i;
                            break;
                        }
                    }
                    if (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName)) {
                        System.out.println("\nbID [" + bookingId + "] does not belong to user [" + userName + "]");
                    }
                } while (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName));
                // Date
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

                Date checkIn = currentDate;
                Date checkOut = new Date(year, month, day);
                totalPrice = calculateTotalPrice(checkIn, checkOut, bookingDirectory.get(bookingIndex).getRoom().getPricePerNight());
                bookingDirectory.get(bookingIndex).setCheckOut(checkOut);
                bookingDirectory.get(bookingIndex).setTotalPrice(totalPrice);
                System.out.println("\nBooking successfully edited");
            }
        } else if (filt == Filter.ALL) {
            viewBookings(Access.GUEST, Motive.EDIT);
            for (int i = 0; i < bookingDirectory.size(); i++) {
                if (bookingDirectory.get(i).getCustomer().getUserName().equals(userName)) {
                    numberOfBookings++;
                }
            }
            if (numberOfBookings >= 1) {
                do {
                    System.out.print("Select Booking ID: ");
                    bookingId = input.nextInt();
                    for (int i = 0; i < bookingDirectory.size(); i++) {
                        if (bookingDirectory.get(i).getBookingId() == bookingId) {
                            bookingIndex = i;
                            break;
                        }
                    }
                    if (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName)) {
                        System.out.println("\nbID [" + bookingId + "] does not belong to user [" + userName + "]");
                    }
                } while (!bookingDirectory.get(bookingIndex).getCustomer().getUserName().equals(userName));
                for (int i = 0; i < bookingDirectory.size(); i++) {
                    if (bookingDirectory.get(i).getBookingId() == bookingId) {
                        bookingIndex = i;
                    }
                }
                addBooking(Access.GUEST, Motive.EDIT);
                bookingDirectory.remove(bookingIndex);
            }
        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuEditBooking();
        } else if (stringChoice.equals("..")) {
            printMenuV(Access.GUEST);
        }
    }

    private void configureAccount(Filter filt) {
        int choice, index = 0;
        String stringChoice;
        String passWord, newPassWord = "", verifyPassWord = ".";
        boolean acceptable = true;

        System.out.println("");
        if (filt == Filter.USERNAME) {

        } else if (filt == Filter.PASSWORD) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (listOfUsers.get(i).getUserName().equals(userName)) {
                    index = i;
                    break;
                }
            }
            do {
                System.out.print("Enter old password: ");
                passWord = input.next();
                if (!listOfUsers.get(index).getPassWord().equals(passWord)) {
                    System.out.println("\nIncorrect password");
                }
            } while (!listOfUsers.get(index).getPassWord().equals(passWord));

            for (int attempts = 2; attempts > 0; attempts--) {
                System.out.print("Enter new password: ");
                newPassWord = input.next();
                System.out.print("Verify new password: ");
                verifyPassWord = input.next();
                if (!verifyPassWord.equals(newPassWord)) {
                    System.out.println("\nPasswords don't match. [" + attempts + "] tries left");
                } else {
                    attempts = 0;
                }
            }
            if (listOfUsers.get(index).getPassWord().equals(passWord) && newPassWord.equals(verifyPassWord) && !verifyPassWord.equals("")) {
                listOfUsers.get(index).setPassWord(newPassWord);
                System.out.println("\nPassword successfully changed");
            }

        }
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuV(Access.ADMIN);
        } else if (stringChoice.equals("..")) {
            // back to startMenu
        } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
            runCmd(stringChoice);
        }
    }

    private void viewAccounts() {
        String stringChoice;

        System.out.println("");
        System.out.println("VIEW ACCOUNTS                                                   [" + currentDate.toString() + "]");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i) instanceof Customer) {
                System.out.printf("[Access: %s] [User: %s] [Pwd: %s] [ID [%s] Name: %s %s]%n",
                        listOfUsers.get(i).getFullAccess() ? "ADMIN" : "GUEST", listOfUsers.get(i).getUserName(), listOfUsers.get(i).getPassWord(),
                        ((Customer) listOfUsers.get(i)).getCustomerId(), ((Customer) listOfUsers.get(i)).getFirstName(),
                        ((Customer) listOfUsers.get(i)).getLastName());
            } else {
                System.out.printf("[Access: %s] [User: %s] [Pwd: %s]%n",
                        listOfUsers.get(i).getFullAccess() ? "ADMIN" : "GUEST",
                        listOfUsers.get(i).getUserName(), listOfUsers.get(i).getPassWord());
            }
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        if (stringChoice.equals(".")) {
            printMenuV(Access.ADMIN);
        } else if (stringChoice.equals("..")) {
            //  back to startMenu
        } else if (!stringChoice.equals(".") && !stringChoice.equals("..")) {
            runCmd(stringChoice);
        }
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
        viewBookings(Access.GUEST, Motive.CHECKOUT);
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
            printHelpMenuStart(Access.GUEST);
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

    private void printHelpMenuGeneral(Access type) {
        int choice;
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + "[" + userName + " - username]");
            System.out.println("[" + type + " - user access]");
            System.out.println("[HELP MENU - menu name]               [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| [>>] indicates that the program is open to     |");
            System.out.println("| receiving commands. Type in .cmd for more info |");
            System.out.println("| on which commands are available.               |");
            System.out.println("| Only the admin user type is able to use cmds.  |");
            System.out.println("| You can navigate the menus easier by entering: |");
            System.out.println("| . / .. / ... : To go one, two or three steps   |");
            System.out.println("| backwards (if possible).                       |");
            System.out.println("| .r is sometimes avaiable and will 'recall' the |");
            System.out.println("| method that you're currently in. Ex, after     | ");
            System.out.println("| adding a customer you can enter in .r to add   |");
            System.out.println("| another customer.                              |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.ADMIN);
            } else {
                runCmd(stringChoice);
            }
        }

        else if (type == Access.GUEST) {
            System.out.println("\n" + "[" + userName + " - username]");
            System.out.println("[" + type + " - user access]");
            System.out.println("[HELP MENU - menu name]               [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| [>>] indicates that you can enter:             |");
            System.out.println("| . / .. / ... : To go one, two or three steps   |");
            System.out.println("| backwards (if possible). The guest user can    |");
            System.out.println("| not use any commands.                          |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.GUEST);
            }
        }
    }
    private void printHelpMenuBooking(Access type) {
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU BOOKINGS                     " + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| The admin user can not make any bookings, but  |");
            System.out.println("| can view, search for and edit any bookings in  |");
            System.out.println("| the system. Each booking requires and has one  |");
            System.out.println("| customer, one room and one check-out date.     |");
            System.out.println("| All bookings are made on the same              |");
            System.out.println("| date - [2018/11/29 with no reservations.       |");
            System.out.println("| The latest possible check-out date for a       |");
            System.out.println("| booking is on [2018/12/31].                    |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.ADMIN);
            } else {
                runCmd(stringChoice);
            }
        }

        else if (type == Access.GUEST) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU BOOKINGS                     " + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| A customer can make a booking by viewing a     |");
            System.out.println("| list of currently available rooms and          |");
            System.out.println("| selecting a check-out date no later than       |");
            System.out.println("| [2018/12/31]. Check-in date is always set      |");
            System.out.println("| to equal the current date. No reservations.    |");
            System.out.println("| A customer can checkout by viewing a list of   | ");
            System.out.println("| all their bookings and selecting one.          |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.GUEST);
            }
        }
    }

    private void printHelpMenuAccommodations(Access type) {
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU ACCOMMODATIONS              [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| The program starts with 25 hotel rooms, which  |");
            System.out.println("| all have the following attributes:             |");
            System.out.println("| (roomNumber, numberOfBeds, hasBalcony, price,  |");
            System.out.println("| isBooked)                                      |");
            System.out.println("| And the following domains:                     |");
            System.out.println("| roomNumber:    (0 < rN < 100)                  |");
            System.out.println("| numberOfBeds:  (0 < nOB < 5)                   |");
            System.out.println("| hasBalcony:    (true/false)                    |");
            System.out.println("| pricePerNight: (1000 <= pPN <= 2500)           |");
            System.out.println("| isBooked:      (true/false)                    |");
            System.out.println("| The admin user type can add, remove and        |");
            System.out.println("| edit all of the hotels rooms and all of        |");
            System.out.println("| their attributes.                              |");
            System.out.println("| The admin user can also view a list of all     |");
            System.out.println("| rooms and a list of all currently available    |");
            System.out.println("| rooms.                                         |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();
            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.ADMIN);
            } else {
                runCmd(stringChoice);
            }
        }

        else if (type == Access.GUEST) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU ACCOMMODATIONS              [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| A customer can only view a list of all         |");
            System.out.println("| currently available rooms.                     |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.GUEST);
            }
        }
    }

    private void printHelpMenuCustomer(Access type) {
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU CUSTOMERS                   [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| The admin user can add and remove customers to |");
            System.out.println("| and from the system. Only individual customers |");
            System.out.println("| can edit their information via their personal  |");
            System.out.println("| account.                                       |");
            System.out.println("| The admin user can also view all of the        |");
            System.out.println("| customers currently in the system.             |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.ADMIN);
            } else {
                runCmd(stringChoice);
            }
        }

        else if (type == Access.GUEST) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU CUSTOMERS                   [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| A customer can only view and edit their own    |");
            System.out.println("| personal information.                          |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.GUEST);
            }
        }
    }

    private void printHelpMenuAccount(Access type) {
        String stringChoice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU ACCOUNTS                    [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| When a customer is added to the system,        |");
            System.out.println("| a user is automatically also created and added.|");
            System.out.println("| The admin user type can view all user accounts |");
            System.out.println("| and can change their own user login information|");
            System.out.println("| When a customer is removed they will no longer |");
            System.out.println("| be able to login into the system.              |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.ADMIN);
            } else {
                runCmd(stringChoice);
            }
        }

        if (type == Access.GUEST) {
            System.out.println("\n" + type + " " + userName);
            System.out.println("HELP MENU ACCOUNTS                    [" + currentDate.toString() + "]");
            System.out.println("-------------------------------------------------");
            System.out.println("| A customer's login username is always set as   |");
            System.out.println("| their lastname + a number representing how many|");
            System.out.println("| users are in the system with that particular   |");
            System.out.println("| lastname. Login password is always '1234'.     |");
            System.out.println("-------------------------------------------------");
            System.out.print("Press any key >>");
            stringChoice = input.next();

            if (stringChoice.equals(".")) {
                printHelpMenuStart(Access.GUEST);
            }
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
            case ".sfbname":
                searchForBookings(Filter.NAME);
                break;
            case ".sfbid":
                searchForBookings(Filter.ID);
                break;
            case ".sfbout":
                searchForBookings(Filter.CHECKOUT);
                break;
            case ".viewa":
                viewAccounts();
                break;
            case ".help":
                printHelpMenuStart(Access.ADMIN);
                break;
            case ".changepwd":
                configureAccount(Filter.PASSWORD);
                break;
            case ".mna":
                printMenuV(Access.ADMIN);
                break;
        }
    }

    private void printCmdHelper() {
        String stringChoice;

        System.out.println("\n" + Access.ADMIN + " " + userName);
        System.out.println("VIEW COMMANDS                                                                                                                    [" + currentDate.toString() + "]");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[.viewc]    --  to view all customers        | [.nb]          --  to goto new booking           | [>>]         --  commands are usable      |");
        System.out.println("[.addc]     --  to add a customer            | [.mnb]         --  to goto manage bookings menu  | [.]          --  to go back one step      |");
        System.out.println("[.viewr]    --  to view available rooms      | [.mnr]         --  to goto manage rooms menu     | [..]         --  to go back two steps     |");
        System.out.println("[.viewallr] --  to view all rooms            | [.mnc]         --  to goto manage customers menu | [...]        --  to go back three steps   |");
        System.out.println("[.addr]     --  to add a room                | [.viewb]       --  to view bookings              | [.exit]      --  to exit program          |");
        System.out.println("[.rmr]      --  to remove a room             | [.sfb]         --  to goto search for booking    | [.logout]    --  to logout current user   |");
        System.out.println("[.edall]    --  to edit all attributes       | [.sfbout]      --  to search for booking w date  | [.cmd]       --  to print cmd menu        |");
        System.out.println("[.edrrnr]   --  to edit RNR                  | [.sfbname]     --  to search for booking w name  | [.help]      --  to print help menu       |");
        System.out.println("[.edrp]     --  to edit a rooms price        | [.sfbid]       --  to search for booking w id    | [.start]     --  to goto start menu       |");
        System.out.println("[.edrb]     --  to edit balcony              | [.rmc]         --  to remove a customer          | [.r]         --  retry/recall             |");
        System.out.println("[.edrnob]   --  to edit number of beds       | [.viewa]       --  to view all accounts          | [.changepwd] --  to change pwd (temp)     |");
        System.out.println("[.mna]      --  to goto manage accounts      |                                                  |                                           |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Press any key >>");
        stringChoice = input.next();
        runCmd(stringChoice);
    }
}

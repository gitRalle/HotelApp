import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private Rooms listOfRooms = new Rooms();

    private enum Access {ADMIN, GUEST}
    private enum Filter {ALL, AVAILABLE, BOOKED,
                         NAME, SSN, PHONE, ADDRESS}

    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main hotelApp = new Main();
        hotelApp.listOfUsers.add(new User("admin", "1234", true));
        hotelApp.listOfUsers.add(new User("gitRalle", "1234", true));
        hotelApp.listOfUsers.add(new User("guest", "1234", false));

        Access user;
        boolean cont;
        boolean stayLoggedIn = true;
        int choice;

        while (stayLoggedIn) {
            user = hotelApp.login();
            cont = true;

            while (cont) {
                hotelApp.printMenuStart(user);
                System.out.print("Enter your choice >>");
                choice = hotelApp.input.nextInt();

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
                        cont = false;
                        break;

                    case 7:
                        System.out.print("\nExiting Hotel App");
                        cont = false;
                        stayLoggedIn = false;
                        break;
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
                    System.out.println("\nLogged in as " + username + "\n");
                    i = listOfUsers.size();
                    attempts = 3;
                    user = Access.ADMIN;
                } else if (listOfUsers.get(i).getUserName().equals(username) && listOfUsers.get(i).getPassWord().equals(password) && !listOfUsers.get(i).getFullAccess()) {
                    System.out.println("\nLogged in as " + username + "\n");
                    i = listOfUsers.size();
                    attempts = 3;
                    user = Access.GUEST;
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
            System.out.println(type + "         2018/11/29 12:00");
            System.out.println("-----------------------------");
            System.out.println("| 1. New Booking             |  NOT DONE");
            System.out.println("| 2. Manage Bookings         |  DONE");
            System.out.println("| 3. Manage Rooms            |  DONE");
            System.out.println("| 4. Manage Customers        |  DONE");
            System.out.println("| 5. Check Out Customer      |  NOT DONE");
            System.out.println("| 6. Log Out                 |  DONE");
            System.out.println("| 7. Exit                    |  DONE");
            System.out.println("-----------------------------");
        } else {
            System.out.println(type + "                2018/11/29 12:00");
            System.out.println("------------------------------------");
            System.out.println("1. Make A New Booking               |  NOT DONE");
            System.out.println("2. View Available Rooms             |  DONE");
            System.out.println("3. View Your Booking History        |  NOT DONE");
            System.out.println("4. Edit Your Personal Information   |  DONE");
            System.out.println("5. Edit Bookings                    |  NOT DONE");
            System.out.println("6. Log Out                          |  DONE");
            System.out.println("7. Exit                             |  DONE");
            System.out.println("------------------------------------");
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

        if (type == Access.ADMIN) {
            // Mange Bookings
            System.out.println("\n" + type + "          2018/11/29");
            System.out.println("-------------------------");
            System.out.println("| 1. Edit Booking        |  NOT DONE");
            System.out.println("| 2. Search For Booking  |  NOT DONE");
            System.out.println("| 3. Back To Main Menu   |  DONE");
            System.out.println("-------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");
        } else {
            // View Available Rooms
            System.out.println("\n" + type + "                                  2018/11/29");
            System.out.println("-------------------------------------------------");
            System.out.println("1. View All Currently Available Rooms            |  DONE");
            System.out.println("2. View Available Rooms Within Specified Dates   |  NOT DONE");
            System.out.println("3. Back To Main Menu                             |  DONE");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    viewRooms(Filter.AVAILABLE);
                    break;
            }
        }
    }

    // Menu 3 and it's respective methods
    private void printMenuIII(Access type) {
        int choice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type);
            System.out.println("MANAGE ROOMS      2018/11/29");
            System.out.println("---------------------------");
            System.out.println("| 1. View Available Rooms  |  DONE");
            System.out.println("| 2. View All Rooms        |  DONE");
            System.out.println("| 3. Edit Room             |  NOT DONE");
            System.out.println("| 4. Add Room              |  NOT DONE");
            System.out.println("| 5. Remove Room           |  NOT DONE");
            System.out.println("| 6. Back To Main Menu     |  DONE");
            System.out.println("---------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    viewRooms(Filter.AVAILABLE);
                    break;
                case 2:
                    viewRooms(Filter.ALL);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } else {
            // View Your Booking History
        }
    }

    private void viewRooms(Filter type) {
        if (type == Filter.ALL) {
            System.out.println("Filter.ALL");
            System.out.printf("%s %5s %7s %11s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.println("-------------------------------");
            for (int i = 0; i < listOfRooms.size(); i++) {
                System.out.printf("%2d %5d %6s %10.2f    |%n", listOfRooms.get(i).getRoomNumber(), listOfRooms.get(i).getNumberOfBeds(),
                        listOfRooms.get(i).isHasBalcony() ? "Yes" : "No", listOfRooms.get(i).getPricePerNight());

            }
            System.out.println("-------------------------------");
            System.out.print("Press any key to return to Main Menu >>");
            input.nextLine();
            input.nextLine();
            System.out.println("");

        } else if (type == Filter.AVAILABLE) {
            int counter = 0;
            System.out.println("Filter.AVAILABLE                                     2018/11/29");
            System.out.printf("%s %5s %7s %11s", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%8s %6s %7s %7s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.println("-----------------------------------------------------------------");
            for (int i = 0; i < listOfRooms.size(); i++) {
                if (!listOfRooms.get(i).isBooked()) {
                    System.out.printf("%2d %5d %6s %10.2f    |    ", listOfRooms.get(i).getRoomNumber(), listOfRooms.get(i).getNumberOfBeds(),
                            listOfRooms.get(i).isHasBalcony() ? "Yes" : "No", listOfRooms.get(i).getPricePerNight());
                    counter++;
                }
                if ((i + 1) % 2 == 0) {
                    System.out.println("");
                }
            }
            if (counter % 2 != 0) {
                System.out.println("");
            }
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Press any key to return to Main Menu >>");
            input.nextLine();
            input.nextLine();
            System.out.println("");
        }
    }

    // Menu 4 and its respective methods
    private void printMenuIV(Access type) {
        int choice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + "             2018/11/29 12:00");
            System.out.println("---------------------------------");
            System.out.println("| 1. Add New Customer            |  DONE 99%");
            System.out.println("| 2. Remove Customer             |  NOT DONE");
            System.out.println("| 3. View List Of All Customers  |  NOT DONE");
            System.out.println("| 4. Back To Main Menu           |  DONE");
            System.out.println("---------------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    addCustomer(); // Method call to add a new customer (incomplete)
                    break;

                case 2:
                    // Method call to 'Remove customer'
                    System.out.println("Remove customer coming soon!\n");
                    break;

                case 3:
                    // Method call to 'View list of all customers'
                    System.out.println("View list of all customers coming soon!\n");
                    break;

                case 4:
                    // Back To Main Menu
                    break;

                default:
                    // ?
                    System.out.println("Default statement: ADMIN\n");
                    break;
            }
        } else {
            System.out.println("\n" + type + "                2018/11/29 12:00");
            System.out.println("------------------------------------");
            System.out.println("1. Update Your Address              |  NOT DONE");
            System.out.println("2. Update Your Phone Number         |  NOT DONE");
            System.out.println("3. View Your Personal Information   |  NOT DONE");
            System.out.println("4. Back To Main Menu                |  DONE");
            System.out.println("------------------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    // Method call to 'Update address'
                    System.out.println("Update your address coming soon!\n");
                    break;

                case 2:
                    // Method call to 'Update your phone number'
                    System.out.println("Update your phone number coming soon!\n");
                    break;

                case 3:
                    // Method call to 'View personal information'
                    System.out.println("View your personal information coming soon!\n");
                    break;

                case 4:
                    break;

                default:
                    // ?
                    System.out.println("Default statement: GUEST\n");
                    break;
            }
        }
    }

    private void addCustomer() {
        String firstName, lastName, ssn, phoneNumber, address;
        int counter = 0;
        input.nextLine();

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
            System.out.print("Enter customer's ten digit social security number (YYMMDD-XXXX): ");
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
        System.out.println("Customer attributes successfully validated!\n");
        // Create customer object
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
                    if (String.valueOf(string.charAt(i)).equalsIgnoreCase(charsPlusLetters[x])) {
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

    // Menu 5 and it's respective methods
    private void printMenuV(Access type) {
        if (type == Access.ADMIN) {
            // Check Out
        } else {
            // Edit Bookings
        }
    }
}

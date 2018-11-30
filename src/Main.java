import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<User> listOfUsers = new ArrayList<>();

    private enum Access {ADMIN, GUEST}

    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main hotelApp = new Main();
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
            System.out.println("| 1. New Booking             |");
            System.out.println("| 2. Manage Bookings         |");
            System.out.println("| 3. Manage Rooms            |");
            System.out.println("| 4. Manage Customers        |");
            System.out.println("| 5. Check Out Customer      |");
            System.out.println("| 6. Log Out                 |");
            System.out.println("| 7. Exit                    |");
            System.out.println("-----------------------------");
        } else {
            System.out.println(type + "                2018/11/29 12:00");
            System.out.println("------------------------------------");
            System.out.println("1. Make A New Booking               |");
            System.out.println("2. View Available Rooms             |");
            System.out.println("3. View Your Booking History        |");
            System.out.println("4. Edit Your Personal Information   |");
            System.out.println("5. Edit Bookings                    |");
            System.out.println("6. Log Out                          |");
            System.out.println("7. Exit                             |");
            System.out.println("------------------------------------");
        }
    }

    private void printMenuI(Access type) {
        if (type == Access.ADMIN) {
            // New Booking
        }
        else {
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
            System.out.println("| 1. Edit Booking        |");
            System.out.println("| 2. Search For Booking  |");
            System.out.println("| 3. Back To Main Menu   |");
            System.out.println("-------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");
        }
        else {
            // View Available Rooms
            System.out.println("\n" + type + "                                  2018/11/29");
            System.out.println("-------------------------------------------------");
            System.out.println("1. View All Currently Available Rooms            |");
            System.out.println("2. View Available Rooms Within Specified Dates   |");
            System.out.println("3. Back To Main Menu                             |");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");
        }
    }

    // Menu 3 and its respective methods
    private void printMenuIII(Access type) {
        int choice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type);
            System.out.println("MANAGE ROOMS      2018/11/29");
            System.out.println("---------------------------");
            System.out.println("| 1. View Available Rooms  |");
            System.out.println("| 2. View All Rooms        |");
            System.out.println("| 3. Edit Room             |");
            System.out.println("| 4. Add Room              |");
            System.out.println("| 5. Remove Room           |");
            System.out.println("| 6. Back To Main Menu     |");
            System.out.println("---------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }

        else {
            // View Your Booking History
        }
    }

    // Menu 4 and its respective methods
    private void printMenuIV(Access type) {
        int choice;

        if (type == Access.ADMIN) {
            System.out.println("\n" + type + "             2018/11/29 12:00");
            System.out.println("---------------------------------");
            System.out.println("| 1. Add New Customer            |");
            System.out.println("| 2. Remove Customer             |");
            System.out.println("| 3. View List Of All Customers  |");
            System.out.println("| 4. Back To Main Menu           |");
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
            System.out.println("1. Update Your Address              |");
            System.out.println("2. Update Your Phone Number         |");
            System.out.println("3. View Your Personal Information   |");
            System.out.println("4. Back To Main Menu                |");
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
            for (int i = 0; i < firstName.length(); i++) {
                if (String.valueOf(firstName.charAt(i)).equalsIgnoreCase("!") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("@") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("#") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("£") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("¤") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("$") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("%") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("&") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("/") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("{") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("(") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("[") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase(")") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("]") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("=") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("}") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("?") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("´") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("`") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("^") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("¨") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("~") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("*") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("'") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("<") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase(">") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("|") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase(",") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase(";") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase(".") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase(":") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("-") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("_") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("§") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("½") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("1") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("2") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("3") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("4") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("5") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("6") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("7") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase("8") ||
                        String.valueOf(firstName.charAt(i)).equalsIgnoreCase("9") || String.valueOf(firstName.charAt(i)).equalsIgnoreCase(" ")) {
                    counter++;
                }
            }
            if (firstName.length() <= 2 || counter != 0) {
                System.out.println("\nName must consist of a minimum of three characters ranging from A - Ö.");
            }
        } while (firstName.length() <= 2 || counter != 0);

        do {
            counter = 0;
            System.out.print("Enter customer's last name: ");
            lastName = input.nextLine();
            for (int i = 0; i < lastName.length(); i++) {
                if (String.valueOf(lastName.charAt(i)).equalsIgnoreCase("!") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("@") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("#") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("£") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("¤") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("$") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("%") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("&") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("/") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("{") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("(") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("[") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase(")") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("]") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("=") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("}") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("?") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("´") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("`") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("^") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("¨") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("~") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("*") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("'") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("<") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase(">") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("|") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase(",") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase(";") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase(".") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase(":") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase(" ") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("_") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("§") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("½") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("1") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("2") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("3") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("4") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("5") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("6") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("7") || String.valueOf(lastName.charAt(i)).equalsIgnoreCase("8") ||
                        String.valueOf(lastName.charAt(i)).equalsIgnoreCase("9")) {
                    counter++;
                }
            }
            if (lastName.length() <= 2 || counter != 0) {
                System.out.println("\nName must consist of a minimum of three characters ranging from A - Ö.");
            }
        } while (lastName.length() <= 2 || counter != 0);

        do {
            counter = 0;
            System.out.print("Enter customer's ten digit social security number (YYMMDD-XXXX): ");
            ssn = input.nextLine();
            for (int i = 0; i < 5; i++) {
                if (!String.valueOf(ssn.charAt(i)).equals("9") && !String.valueOf(ssn.charAt(i)).equals("8") && !String.valueOf(ssn.charAt(i)).equals("7") &&
                        !String.valueOf(ssn.charAt(i)).equals("6") && !String.valueOf(ssn.charAt(i)).equals("5") && !String.valueOf(ssn.charAt(i)).equals("4") &&
                        !String.valueOf(ssn.charAt(i)).equals("3") && !String.valueOf(ssn.charAt(i)).equals("2") && !String.valueOf(ssn.charAt(i)).equals("1") &&
                        !String.valueOf(ssn.charAt(i)).equals("0")) {
                    counter++;
                }
            }
            for (int i = 7; i < ssn.length(); i++) {
                if (!String.valueOf(ssn.charAt(i)).equals("9") && !String.valueOf(ssn.charAt(i)).equals("8") && !String.valueOf(ssn.charAt(i)).equals("7") &&
                        !String.valueOf(ssn.charAt(i)).equals("6") && !String.valueOf(ssn.charAt(i)).equals("5") && !String.valueOf(ssn.charAt(i)).equals("4") &&
                        !String.valueOf(ssn.charAt(i)).equals("3") && !String.valueOf(ssn.charAt(i)).equals("2") && !String.valueOf(ssn.charAt(i)).equals("1") &&
                        !String.valueOf(ssn.charAt(i)).equals("0")) {
                    counter++;
                }
            }

            if (!String.valueOf(ssn.charAt(6)).equals("-") || counter != 0 || ssn.length() != 11) {
                System.out.println("\nSocial security number must consist of ten digits with a hyphenate");
            }

        } while (!String.valueOf(ssn.charAt(6)).equals("-") || counter != 0 || ssn.length() != 11);

        do {
            counter = 0;
            System.out.print("Enter customer's phone number: ");
            phoneNumber = input.nextLine();
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!String.valueOf(phoneNumber.charAt(i)).equals("9") && !String.valueOf(phoneNumber.charAt(i)).equals("8") && !String.valueOf(phoneNumber.charAt(i)).equals("7")
                && !String.valueOf(phoneNumber.charAt(i)).equals("6") && !String.valueOf(phoneNumber.charAt(i)).equals("5") && !String.valueOf(phoneNumber.charAt(i)).equals("4")
                && !String.valueOf(phoneNumber.charAt(i)).equals("3") && !String.valueOf(phoneNumber.charAt(i)).equals("2") && !String.valueOf(phoneNumber.charAt(i)).equals("1")
                && !String.valueOf(phoneNumber.charAt(i)).equals("0")) {
                    counter++;
                }
            }
            if (phoneNumber.length() != 10 || counter != 0) {
                System.out.println("\nPhone number must consist of ten digits without any hyphenate");
            }
        } while(phoneNumber.length() != 10 || counter != 0);

        System.out.print("Enter customer's address: ");
        address = input.nextLine();
        System.out.println("Customer attributes successfully validated!\n");
        // Create customer object
    }

    private void printMenuV(Access type) {
        if (type == Access.ADMIN) {
            // Check Out
        }
        else {
            // Edit Bookings
        }
    }
}

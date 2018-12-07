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
        NAME, SSN, PHONE, ADDRESS
    }

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
                    user = Access.ADMIN;
                    userName = listOfUsers.get(i).getUserName();
                    i = listOfUsers.size();
                    attempts = 3;
                } else if (listOfUsers.get(i).getUserName().equals(username) && listOfUsers.get(i).getPassWord().equals(password) && !listOfUsers.get(i).getFullAccess()) {
                    System.out.println("\nLogged in as " + username + "\n");
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
            System.out.println(userName + "\n" + type + "             [2018/11/29]");
            System.out.println("-----------------------------");
            System.out.println("| 1. New Booking             |  NOT DONE");
            System.out.println("| 2. Manage Bookings         |");
            System.out.println("| 3. Manage Rooms            |");
            System.out.println("| 4. Manage Customers        |");
            System.out.println("| 5. Check Out Customer      |  NOT DONE");
            System.out.println("| 6. Log Out                 |");
            System.out.println("| 7. Exit                    |");
            System.out.println("-----------------------------");
        } else {
            System.out.println(userName + "\n" + type + "                                 [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. Make A New Booking                          |  NOT DONE");
            System.out.println("| 2. View Available Rooms                        |");
            System.out.println("| 3. View Your Booking History                   |  NOT DONE");
            System.out.println("| 4. Edit Your Personal Information              |");
            System.out.println("| 5. Edit Bookings                               |  NOT DONE");
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

        if (type == Access.ADMIN) {
            // Mange Bookings
            System.out.println("\n" + userName + "\n" + "MANAGE BOOKINGS" + "   [2018/11/29]");
            System.out.println("-----------------------------");
            System.out.println("| 1. Edit Booking            |  NOT DONE");
            System.out.println("| 2. Search For Booking      |  NOT DONE");
            System.out.println("| 3. Back To Main Menu       |");
            System.out.println("-----------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");
        } else {
            // View Available Rooms
            System.out.println("\n" + userName + "\n" + "SELECT VIEW ROOMS                     [2018/11/29]");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1. View All Currently Available Rooms          |");
            System.out.println("| 2. View Available Rooms Within Specified Dates |  NOT DONE");
            System.out.println("| 3. Back To Main Menu                           |");
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
            System.out.println("\n" + userName );
            System.out.println("MANAGE ROOMS      [2018/11/29]");
            System.out.println("-----------------------------");
            System.out.println("| 1. View Available Rooms    |");
            System.out.println("| 2. View All Rooms          |");
            System.out.println("| 3. Edit Room               |  NOT DONE");
            System.out.println("| 4. Add Room                |  NOT DONE");
            System.out.println("| 5. Remove Room             |  NOT DONE");
            System.out.println("| 6. Back To Main Menu       |");
            System.out.println("-----------------------------");
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
            System.out.println("VIEW ROOMS\nFILTER.ALL       [2018/11/29]");
            System.out.printf("%s %5s %7s %11s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.println("-------------------------------");
            for (int i = 0; i < roomDirectory.size(); i++) {
                System.out.printf("%2d %5d %6s %10.2f    |%n", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                        roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());

            }
            System.out.println("-------------------------------");
            System.out.print("Press any key to return to Main Menu >>");
            input.nextLine();
            input.nextLine();
            System.out.println("");

        } else if (type == Filter.AVAILABLE) {
            int counter = 0;
            System.out.println("VIEW ROOMS\nFILTER.AVAILABLE                                    [2018/11/29]");
            System.out.printf("%s %5s %7s %11s", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.printf("%8s %6s %7s %7s%n", "RNR", "BEDS", "BALCONY", "PRICE (SEK)");
            System.out.println("-----------------------------------------------------------------");
            for (int i = 0; i < roomDirectory.size(); i++) {
                if (!roomDirectory.get(i).isBooked()) {
                    System.out.printf("%2d %5d %6s %10.2f    |    ", roomDirectory.get(i).getRoomNumber(), roomDirectory.get(i).getNumberOfBeds(),
                            roomDirectory.get(i).isHasBalcony() ? "Yes" : "No", roomDirectory.get(i).getPricePerNight());
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
            System.out.println("\n" + userName + "\nMANAGE CUSTOMERS  [2018/11/29]");
            System.out.println("-----------------------------");
            System.out.println("| 1. Add New Customer        |");
            System.out.println("| 2. Remove Customer         |");
            System.out.println("| 3. Display Customers       |");
            System.out.println("| 4. Back To Main Menu       |");
            System.out.println("-----------------------------");
            System.out.print("Enter your choice >>");
            choice = input.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                    addCustomer();
                    break;

                case 2:
                    removeCustomer();
                    break;

                case 3:
                    customerDirectory.viewCustomers();
                    break;

                default:
                    // ?
                    System.out.println("Is not a valid option\n");
                    break;
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
            choice = input.nextInt();
            System.out.println("");

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
                    System.out.println("\n");
                    break;
            }
        }
    }

    private void viewCustomerInfo() {
        int index = 99; // will never actually take on value 99
        input.nextLine();

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
        System.out.print("Press any key to return to Main Menu >>");
        input.nextLine();
        System.out.println("");
    }

    private void updateCustomerInfo(Filter type) {
        int index = 99; // will never actually take on value 99
        int counter = 0;
        String string;
        input.nextLine();

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
        System.out.println("Information successfully updated\n");
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
        System.out.print("Press any key to return to Main Menu >>");
        input.nextLine();
        System.out.println("");

    }

    private void removeCustomer() {
        int id;
        int counter = 0;
        if (customerDirectory.size() == 0) {
            System.out.print("Customer Directory Is Empty\n");
            System.out.print("Press any key to return to Main Menu >>");
            input.nextLine();
            input.nextLine();
            System.out.println("");
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
                    id = i;
                }
            }
            customerDirectory.remove(id);
            System.out.println("Customer successfully removed\n");
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

    private void checkRoomAvailability(Date checkIn, Date bookedUntil) {
        int deltaYear, deltaMonth, deltaDay, deltaHour, deltaMinute;
        int total;
        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


        // if (bookedUntil.getMonth() > checkIn.getMonth()) {return room not available;}
        // if (bookedUntil.getMonth() < checkIn.getMonth()) {return room is available;}
        // else if (bookedUntil.getMonth() == checkIn.getMonth()) {
        // if (bookedUntil.getDay() > checkIn.getDay()) {return room not available;}
        // if (bookedUntil.getDay() < checkIn.getDay()) {return room is available;}
        // if (bookedUntil.getDay() == checkIn.getDay()) {return rooms is available;}
        // }


    }


}

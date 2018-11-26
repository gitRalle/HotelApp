import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<User> listOfUsers = new ArrayList<>();
    private enum Access {ADMIN, GUEST}
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main hotelApp = new Main();
        hotelApp.listOfUsers.add(new User("gitRalle", "1234", true));
        Access user;
        user = hotelApp.login();

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
}

import java.io.*;
import java.util.Scanner;

/**
 * @author Yara
 */
public class Main {
    public static void main(String[] args) {
        User user = new User();
        boolean running = true, mainMenu = true, register = false, login = false, player = false, playgroundOwner = false, admin = false;
        Scanner scan = new Scanner(System.in);
        while (running) {
            if (mainMenu) {
                System.out.println("----------Main menu----------\n1- Register\n2- Login\n3- Exit");
                switch (scan.nextInt()) {
                    case 1:
                        register = true;
                        mainMenu = false;
                        break;
                    case 2:
                        login = true;
                        mainMenu = false;
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("You entered a wrong choice");
                }
            }
            if (register) {
                System.out.println("----------Registration menu----------\nWhat's your role?\n1- Player\n2- Playground Owner\n3- Admin");
                int choice = scan.nextInt();
                String role = choice == 1 ? "Player" : choice == 2 ? "Playground Owner" : choice == 3 ? "Admin" : "";
                System.out.println("Enter your username, password, email, national ID, phone, default location");
                user = choice == 1 ? new Player(scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next())
                        : choice == 2 ? new PlaygroundOwner(scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next())
                        : choice == 3 ? new Administrator(scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next())
                        : null;
                user.register();
                register = false;
                mainMenu = true;
            }
            if (login) {
                System.out.println("----------Login menu----------\nEnter your user name and password");
                user.setName(scan.next());
                user.setPassword(scan.next());
                if (user.login()) {
                    System.out.println("Logged in successfully!");
                    String role = user.getUserRole();
                    user = role.equalsIgnoreCase("Player") ? new Player(user.getName(), user.getPassword())
                            : role.equalsIgnoreCase("Playground Owner") ? new PlaygroundOwner(user.getName(), user.getPassword())
                            : role.equalsIgnoreCase("Admin") ? new Administrator(user.getName(), user.getPassword())
                            : null;
                    login = false;
                    player = user instanceof Player;
                    playgroundOwner = user instanceof PlaygroundOwner;
                    admin = user instanceof Administrator;
                } else {
                    System.out.println("You have entered wrong username or password");
                    mainMenu = true;
                    login = false;
                }
            }
            if (player) {
                System.out.println("----------Player menu----------\n1- View playgrounds\n2- Book a playground\n3- View my requests\n4- Logout");
                switch (scan.nextInt()) {
                    case 1:
                        ((Player) user).displayPlayground();
                        break;
                    case 2:
                        System.out.println("Enter playground name, the initial booking time, end booking time and the booking day");
                        Playground playground = fetchPlayground(scan.next());
                        BookingRequest request = new BookingRequest((Player) user, playground, scan.nextInt(), scan.nextInt(), scan.next());
                        ((Player) user).book(request);
                        break;
                    case 3:
                        ((Player) user).viewMyRequests();
                        break;
                    case 4:
                        player = false;
                        mainMenu = true;
                        break;
                }
            }
            if (playgroundOwner) {
                System.out.println("----------Playground owner menu----------\n1- Register playground\n2- Show my requests\n3- Accept requests\n4- Display my playgrounds\n5- Logout");
                switch (scan.nextInt()) {
                    case 1:
                        System.out.println("Enter playground name, location, size, available hours, price per hour and cancellation period");
                        Playground playground = new Playground(scan.next(), user.getName(), scan.next(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble());
                        System.out.println("---------- Enter seven days and their time slots ----------");
                        System.out.println("Enter the available times of the seven days of your playground in the following pattern, 'DD/MM:' then space separated time slot");
                        try {
                            FileOutputStream slots = new FileOutputStream("PlaygroundsTimeSlots.txt", true);
                            slots.write(String.format("Playground name: %s\n", playground.getName()).getBytes());
                            for (int i = 0; i < 7; i++) {
                                String day = scan.next().split(":")[0];
                                playground.enterTimeSlots(day);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ((PlaygroundOwner) user).registerPlayground(playground);
                        break;
                    case 2:
                        ((PlaygroundOwner) user).displayMyRequests();
                        break;
                    case 3:
                        System.out.println("Enter the ID of the request.");
                        ((PlaygroundOwner) user).updateRequestStatus(scan.nextInt());
                        break;
                    case 4:
                        ((PlaygroundOwner) user).displayMyPlaygrounds();
                    case 5:
                        playgroundOwner = false;
                        mainMenu = true;
                        break;
                }
            }
            if (admin) {
                System.out.println("----------Administrator  menu----------\n1- View playground requests\n2- Accept playground request\n3- Deny playground request\n4- Logout");
                switch (scan.nextInt()) {
                    case 1:
                        ((Administrator) user).displayPlaygrounds();
                        break;
                    case 2:
                        System.out.println("Enter the playground name and the owner name in order");
                        ((Administrator) user).updatePlaygroundStatus(scan.next(), scan.next(), "Accepted");
                        break;
                    case 3:
                        System.out.println("Enter the playground name and the owner name in order");
                        ((Administrator) user).updatePlaygroundStatus(scan.next(), scan.next(), "Denied");
                        break;
                    case 4:
                        admin = false;
                        mainMenu = true;
                }
            }
        }
    }

    public static Playground fetchPlayground(String name) {
        try {
            BufferedReader playgrounds = new BufferedReader(new FileReader("Playgrounds.txt"));
            String line;
            while ((line = playgrounds.readLine()) != null) {
                if (line.equals(String.format("Playground name: %s", name))) {
                    return new Playground(name, playgrounds.readLine().split(": ")[1], playgrounds.readLine().split(": ")[1], Double.parseDouble(playgrounds.readLine().split(": ")[1]), Double.parseDouble(playgrounds.readLine().split(": ")[1]), Double.parseDouble(playgrounds.readLine().split(": ")[1]), Double.parseDouble(playgrounds.readLine().split(": ")[1]));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

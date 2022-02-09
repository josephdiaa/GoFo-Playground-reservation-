import java.io.*;
import java.util.ArrayList;

/**
 * @author Arwa
 */
public class PlaygroundOwner extends User {
    ArrayList<Playground> myPlaygroundList = new ArrayList<>();

    /**
     * Default constructor to Create new playground owner
     */
    public PlaygroundOwner() {

    }

    /**
     * parameterized constructor to Create new playground owner with his (name,password,email,nationalId,phone,defaultLocation)
     * @param name This is the name of playground owner
     * @param password This is the password of playground owner
     * @param email This is the email of playground owner
     * @param nationalId This is the National Id of playground owner
     * @param phone This is the phone of playground owner
     * @param defaultLocation This is the Default Location of playground owner
     */
    public PlaygroundOwner(String name, String password, String email, String nationalId, String phone, String defaultLocation) {
        super(name, password, email, nationalId, phone, defaultLocation, "Playground Owner");
    }

    /**
     * parameterized constructor to Create new playground owner with his (name,password)
     * @param name This is the name of playground owner
     * @param password This is the password of playground owner
     */
    public PlaygroundOwner(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * This function to add(Register) New playground into System
     * @param playground This playground object which will be Register
     */
    public void registerPlayground(Playground playground) {
        if (!playground.exists()) {
            myPlaygroundList.add(playground);
            try {
                FileOutputStream input = new FileOutputStream("Playgrounds.txt", true);
                input.write(String.format("Playground name: %s\nOwner name: %s\nLocation: %s\nSize: %g\nAvailable hours: %g\nPrice per hour: %g\ncancellation period: %g\nStatus: Pending\n\n", playground.getName(), playground.getOwnerName(), playground.getLocation(), playground.getSize(), playground.getAvailableHours(), playground.getPricePerHour(), playground.getCancellationPeriod()).getBytes());
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("You have entered a used playground name, please enter another name");

    }

    /**
     * This function is to display all the playgrounds owned by the playground owner
     */
    public void displayMyPlaygrounds() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Playgrounds.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) continue;
                String playgroundName = line;
                String owner = reader.readLine();
                String location = reader.readLine();
                String size = reader.readLine();
                String available = reader.readLine();
                String price = reader.readLine();
                String cancellation = reader.readLine();
                String state = reader.readLine();
                if (owner.equals(String.format("Owner name: %s", name))) {
                    System.out.println(String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n\n", line, name, location, size, available, price, cancellation, state));
                    break;
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This function is to display all requests related to the playground owner
     */
    public void displayMyRequests() {
        try {
            BufferedReader requests = new BufferedReader(new FileReader("Requests.txt"));
            String line;
            while ((line = requests.readLine()) != null) {
                if (line.equals("")) continue;
                String id = line.split(": ")[1];
                String playerName = requests.readLine().split(": ")[1];
                String playgroundOwnerName = requests.readLine().split(": ")[1];
                String playgroundName = requests.readLine().split(": ")[1];
                String startTime = requests.readLine().split(": ")[1];
                String endTime = requests.readLine().split(": ")[1];
                String day = requests.readLine().split(": ")[1];
                String state = requests.readLine().split(": ")[1];
                if (playgroundOwnerName.equals(name))
                    System.out.format("ID: %s\nPlayer name: %s\nPlayground Owner name: %s\nPlayground name: %s\nInitial booking time: %s\nEnd booking time: %s\nBooking day: %s\nState: %s\n\n%n", id, playerName, name, playgroundName, startTime, endTime, day, state);
            }
            requests.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function to search for a request with id and update his Status
     * @param id this is the id of Request
     */
    public void updateRequestStatus(int id) {
        try {
            BufferedReader requests = new BufferedReader(new FileReader("Requests.txt"));
            String line;
            int counter = 0;
            while ((line = requests.readLine()) != null) {
                counter++;
                if (line.equals(String.format("ID: %s", id))) {
                    for (int i = 0; i < 7; i++)
                        counter++;
                    requests.close();
                    modifyRequest(counter);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function to Modify Request
     * @param lineNumber the index of state that will be changed
     */
    public void modifyRequest(int lineNumber) {
        try {

            File input = new File("Requests.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader("Requests.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                if (counter != lineNumber) {

                    writer.println(line);
                    writer.flush();
                } else {
                    writer.println("State: Accepted");
                    writer.flush();
                }
            }
            writer.close();
            reader.close();

            if (!input.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(input))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


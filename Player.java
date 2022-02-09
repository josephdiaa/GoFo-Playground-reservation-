import java.io.*;
/**
 * @author Yara
 */
public class Player extends User {
    /**
     * default constructor to Create New player into System
     */
    public Player() {

    }

    /**
     * parameterized constructor to creat New player with(name,password,email,nationalId,phone,defaultLocation) using parent constructor
     * @param name This is the name of player
     * @param password This is the password of player
     * @param email This is the email of player
     * @param nationalId This is the National Id of player
     * @param phone This is the phone of player
     * @param defaultLocation This is the Default Location of player
     */
    public Player(String name, String password, String email, String nationalId, String phone, String defaultLocation) {
        super(name, password, email, nationalId, phone, defaultLocation, "Player");
    }

    /**
     * parameterized constructor to creat New player with(name,password) using parent constructor
     * @param name This is the name of player
     * @param password This is the password of player
     */
    public Player(String name, String password) {
        super(name, password);
    }

    /**
     * This function to Creat booking request
     * @param request This is request of booking
     */
    public void book(BookingRequest request) {
        try {
            BufferedReader input = new BufferedReader(new FileReader("PlaygroundsTimeSlots.txt"));
            String lines;
            String slot;
            int counter = 0;
            int initial = 0, end = 0;
            while ((lines = input.readLine()) != null) {
                counter++;
                if (lines.equals(String.format("Playground name: %s", request.getPlayground().getName()))) {
                    if (input.readLine().equals(request.getBookingDay() + ":")) {
                        counter++;
                        while (!(slot = input.readLine()).equals("End of the day")) {
                            counter++;
                            if (Integer.parseInt(slot) == request.getInitialBookingTime()) {
                                initial = counter;
                            }
                            if (Integer.parseInt(slot) == request.getEndBookingTime()) {
                                end = counter;
                            }
                        }
                        if (initial != 0 && end != 0) {
                            System.out.println("You've chosen a correct time slot.");
                            request.setState("Pending");
                            request.addToRequests();
                            input.close();
                            removeLineFromFile("PlaygroundsTimeSlots.txt", initial, end);
                            break;
                        }
                        else{
                            System.out.println("You have chosen an occupied time slot, please try again.");
                            break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This function to remove line from file
     * @param file This is the file from which we will delete the line
     * @param initial this is the number of first line
     * @param end this is the number of last line
     */
    public void removeLineFromFile(String file, int initial, int end) {

        try {

            File input = new File(file);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                if (!(counter >= initial && counter <= end)) {

                    writer.println(line);
                    writer.flush();
                }
            }

            writer.close();
            reader.close();
            input.delete();
            tempFile.renameTo(input);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This function to display all available playgrounds
     */
    public void displayPlayground() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Playgrounds.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewMyRequests() {
        try {
            BufferedReader requests = new BufferedReader(new FileReader("Requests.txt"));
            String line;
            while ((line = requests.readLine()) != null) {
                if (line.equals("")) continue;
                String id = line;
                String playerName = requests.readLine();
                String owner = requests.readLine();
                String playgroundName = requests.readLine();
                String start = requests.readLine();
                String end = requests.readLine();
                String day = requests.readLine();
                String state = requests.readLine();
                if (playerName.equals(String.format("Player name: %s", name))) {
                    System.out.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n\n", line, playerName, owner, playgroundName, start, end, day, state);
                    break;
                }
            }
            requests.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


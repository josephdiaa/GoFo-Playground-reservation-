import java.io.*;

/**
 * @author Arwa
 */

public class Administrator extends User {
    /**
     * default constructor to creat new Administrator
     */
    public Administrator() {

    }

    /**
     * Parameterized constructor to  Creat Administrator with with(name,password,email,nationalId,phone,defaultLocation) using parent constructor
     * @param name this the name of Administrator
     * @param password this the password of Administrator
     * @param email this the email of Administrator
     * @param nationalId this the National Id of Administrator
     * @param phone this the phone of Administrator
     * @param defaultLocation this the Default Location of Administrator
     */
    public Administrator(String name, String password, String email, String nationalId, String phone, String defaultLocation) {
        super(name, password, email, nationalId, phone, defaultLocation, "Admin");
    }

    /**
     * Parameterized constructor to  Creat Administrator with with(name,password)
     * @param name this the name of Administrator
     * @param password this the password of Administrator
     */
    public Administrator(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * this function to display all Playgrounds
     */
    public void displayPlaygrounds() {
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

    /**
     * this function to update Playground Status
     * @param playgroundName this is the name of playground
     * @param ownerName this is the name of playground owner
     * @param state this is the state of playground
     */
    public void updatePlaygroundStatus(String playgroundName, String ownerName, String state) {
        try {
            BufferedReader requests = new BufferedReader(new FileReader("Playgrounds.txt"));
            String line;
            int counter = 0;
            while ((line = requests.readLine()) != null) {
                if (line.equals(String.format("Playground name: %s", playgroundName))) {
                    counter++;
                    if (requests.readLine().equals(String.format("Owner name: %s", ownerName))) {
                        counter++;
                        for (int i = 0; i < 6; i++) {
                            counter++;
                        }
                        requests.close();
                        modifyPlaygroundRequest(counter, state);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * THis function to modify Playground Request
     * @param lineNumber this is the number of line in file
     * @param state this is the state of playground
     */
    public void modifyPlaygroundRequest(int lineNumber, String state) {
        try {

            File input = new File("Playgrounds.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader("Playgrounds.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                if (counter != lineNumber) {

                    writer.println(line);
                    writer.flush();
                } else {
                    writer.println("State: " + state);
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
}

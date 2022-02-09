import java.io.*;
import java.util.*;


/**
 *@author joseph
 */
public class Playground {

    private String name;
    private String ownerName;
    private String location;
    private double size;
    private double availableHours;
    private double pricePerHour;
    private double cancellationPeriod;
    private String Id;
    private ArrayList<Integer> time = new ArrayList<>();


    /**
     * This function to get the name of playground
     * @return name of playground
     */
    public String getName() {
        return name;
    }

    /**
     * This function to set the name of playground
     * @param name the name of playground
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Parameterized constructor to Creat new playground with(name,ownerName,location,size,availableHours,pricePerHour,cancellationPeriod)
     * @param name the name of playground
     * @param ownerName the name of playground owner
     * @param location the location of playground
     * @param size the size of playground
     * @param availableHours the Available Hours of playground
     * @param pricePerHour the Price Per Hour of playground
     * @param cancellationPeriod the Cancellation Period of playground
     */
    public Playground(String name, String ownerName, String location, double size, double availableHours, double pricePerHour, double cancellationPeriod) {
        this.name = name;
        this.location = location;
        this.size = size;
        this.availableHours = availableHours;
        this.pricePerHour = pricePerHour;
        this.ownerName = ownerName;
        this.cancellationPeriod = cancellationPeriod;
    }

    /**
     * This function to check if the name of playground is used or not
     * @return true if name of playground is used
     * @return false if name of playground is not used
     */
    public boolean exists() {
        try {
            BufferedReader users = new BufferedReader(new FileReader("Playgrounds.txt"));
            String line;
            while ((line = users.readLine()) != null) {
                if (line.equals(String.format("Playground name: %s", name)))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This function to book time slot in day
     * @param day This is the day we will book a time slot
     */
    public void enterTimeSlots(String day) {
        Scanner scan = new Scanner(System.in);
        int initial, end;
        while (true) {
            initial = scan.nextInt();
            end = scan.nextInt();
            time.add(initial);
            time.add(end);
            System.out.println("Do you want to enter another time slot?(y/n)");
            if (scan.next().equals("n")) {
                int size = time.size();
                for (int i = 0; i < size; i += 2) {
                    for (int j = time.get(i) + 1; j < time.get(i + 1); j++)
                        time.add(j);
                }
                Collections.sort(time);
                try {
                    FileOutputStream times = new FileOutputStream("PlaygroundsTimeSlots.txt", true);
                    times.write(String.format("%s: \n", day).getBytes());
                    for (int j = 0; j < time.size(); j++) {
                        times.write(String.format("%d\n", time.get(j)).getBytes());
                    }
                    times.write("End of the day\n".getBytes());
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    /**
     * This function to get Available Hours of playground
     * @return Available Hours of playground
     */
    public double getAvailableHours() {
        return availableHours;
    }
    /**
     * This function to get Price Per Hour of playground
     * @return Price Per Hour of playground
     */
    public double getPricePerHour() {
        return pricePerHour;
    }
    /**
     * This function to get Cancellation Period of playground
     * @return Cancellation Period of playground
     */
    public double getCancellationPeriod() {
        return cancellationPeriod;
    }
    /**
     * This function to get Size of playground
     * @return Size of playground
     */
    public double getSize() {
        return size;
    }

    /**
     * This function to get Location of playground
     * @return Location of playground
     */
    public String getLocation() {
        return location;
    }

    /**
     * This function to get Owner Name of playground
     * @return Owner Name of playground
     */
    public String getOwnerName() {
        return ownerName;
    }
}


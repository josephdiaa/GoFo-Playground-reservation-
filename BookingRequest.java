import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Yara
 */
public class BookingRequest {
    private Player player;
    private Playground playground;
    private int initialBookingTime, endBookingTime;
    private String bookingDay;
    private String state;
    private int id;
    private static int count = 0;

    /**
     * Parameterized constructor to  Creat Booking Request with (player,playground,initialBookingTime,endBookingTime,bookingDay)
     * @param player This is the player who wants to book the playground
     * @param playground This is the playground that the player wants to book
     * @param initialBookingTime This is the start time of booking
     * @param endBookingTime This is the end time of booking
     * @param bookingDay This is the day of booking
     */
    public BookingRequest(Player player, Playground playground, int initialBookingTime, int endBookingTime, String bookingDay) {
        if (initialBookingTime >= endBookingTime)
            throw new IllegalStateException("You've exceeded the day you entered.");
        this.player = player;
        this.playground = playground;
        this.initialBookingTime = initialBookingTime;
        this.endBookingTime = endBookingTime;
        this.bookingDay = bookingDay;
        this.id = count++;
    }

    /**
     * This function to add requests to file
     */
    public void addToRequests() {
        try {
            FileOutputStream requests = new FileOutputStream("Requests.txt", true);
            requests.write(String.format("ID: %d\nPlayer name: %s\nPlayground Owner name: %s\nPlayground name: %s\nInitial booking time: %d\nEnd booking time: %d\nBooking day: %s\nState: %s\n\n", id, player.getName(), playground.getOwnerName(), playground.getName(), initialBookingTime, endBookingTime, bookingDay, state).getBytes());
            requests.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function to get player
     * @return player who wants to book
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * this function to set player who wants to book
     * @param player this is the player who wants to book
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * This function to get a playground which will book
     * @return the playground which will book
     */
    public Playground getPlayground() {
        return playground;
    }
    /**
     * this function to set playground which will be booked
     * @param playground this is the playground which will be booked
     */
    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
    /**
     * This function to get Initial Booking Time of booking
     * @return the Initial Booking Time of booking
     */
    public int getInitialBookingTime() {
        return initialBookingTime;
    }

    /**
     * this function to set initial Time of booking
     * @param initialBookingTime this is the initial Time of booking
     */
    public void setInitialBookingTime(int initialBookingTime) {
        this.initialBookingTime = initialBookingTime;
    }
    /**
     * This function to get End Booking Time of booking
     * @return the End Booking Time of booking
     */
    public int getEndBookingTime() {
        return endBookingTime;
    }
    /**
     * this function to set end Time of booking
     * @param endBookingTime this is the end Time of booking
     */
    public void setEndBookingTime(int endBookingTime) {
        this.endBookingTime = endBookingTime;
    }
    /**
     * This function to get booking Day
     * @return the bookingDay
     */
    public String getBookingDay() {
        return bookingDay;
    }
    /**
     * this function to set day of booking
     * @param bookingDay this is the day of booking
     */
    public void setBookingDay(String bookingDay) {
        this.bookingDay = bookingDay;
    }
    /**
     * This function to get State of booking request
     * @return the State of booking request
     */
    public String getState() {
        return state;
    }
    /**
     * this function to set state of booking
     * @param state this is the state of booking
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * This function to get Id
     * @return the Id
     */
    public int getId() {
        return id;
    }

}


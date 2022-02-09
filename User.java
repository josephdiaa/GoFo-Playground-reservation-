import java.io.*;


/**
 * @author joseph
 */
public class User {
    protected String name;
    protected String email;
    protected String password;
    protected String nationalId;
    protected String phone;
    protected String defaultLocation;
    protected String role;

    //-----------------------------

    /**
     * parameterized constructor to Create user with (name,password,email,nationalId,phone,defaultLocation,role)
     * @param name This is the Name of user
     * @param password This is the Password of user
     * @param email This is the email of user
     * @param nationalId This is the National Id of user
     * @param phone This is the phone of user
     * @param defaultLocation This is the Default Location of user
     * @param role This is the role of user
     */
    public User(String name, String password, String email, String nationalId, String phone, String defaultLocation, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.nationalId = nationalId;
        this.phone = phone;
        this.defaultLocation = defaultLocation;
        this.role = role;
    }

    /**
     * Default constructor to Create User
     */
    public User() {
    }

    /**
     * parameterized constructor to Create user with(Name,password)
     * @param name This is the Name of user
     * @param password This is the Password of user
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * This function to Register New User in System and to check his name and if he or she is Administrator or not and to save his information into System file
     */
    public void register() {
        if (exists()) {
            System.out.println("Username is taken");
        } else {
            if (!checkAdministrator() && role.equalsIgnoreCase("Admin")) {
                System.out.println("This rule is not available for you.");
            } else {
                try {
                    FileOutputStream users = new FileOutputStream("Users.txt", true);
                    users.write(String.format("Username: %s\nPassword: %s\nEmail: %s\nNational ID: %s\nPhone: %s\nDefault location: %s\nRole: %s\n\n", name, password, email, nationalId, phone, defaultLocation, role).getBytes());
                    users.close();
                    System.out.println("You've registered successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This function is to make the user log in to the system after verifying that his name is stored in the system files before and that the password is correct
     * @return True if the username and password are correct
     * @return false if the username and password are incorrect
     */
    public boolean login() {
        try {
            BufferedReader users = new BufferedReader(new FileReader("Users.txt"));
            String line;
            while ((line = users.readLine()) != null) {
                if (line.equalsIgnoreCase(String.format("Username: %s", name))) {
                    if (users.readLine().equals(String.format("Password: %s", password))) {
                        users.close();
                        return true;
                    }
                }
            }
            users.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * this function check the pattern of name and password of Administrator
     * @return true if the username and password are identical or pattern
     * @return false if the username and password are not identical or pattern
     */
    public boolean checkAdministrator() {

        return name.endsWith("AD") && password.startsWith("A") && Character.isDigit(password.charAt(1)) && password.endsWith("ad");
    }

    /**
     * this function to check if User Name is used or not
     * @return true if User Name is used
     * @return false if User Name is not used
     */
    public boolean exists() {
        try {
            BufferedReader users = new BufferedReader(new FileReader("Users.txt"));
            String line;
            while ((line = users.readLine()) != null) {
                if (line.equals(String.format("Username: %s", name)))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This function to get User Role
     * @return The role of user
     */
    public String getUserRole() {
        try {
            BufferedReader users = new BufferedReader(new FileReader("Users.txt"));
            String line;
            while ((line = users.readLine()) != null) {
                if (line.equals(String.format("Username: %s", name))) {
                    for (int i = 0; i < 5; i++)
                        users.readLine();
                    return users.readLine().split(": ")[1];
                }
            }
            users.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This function to get Name of user
     * @return Name of User
     */
    public String getName() {
        return name;
    }

    /**
     * This function to set the name of user
     * @param name This is the name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function to get the Email of user
     * @return Email of user
     */
    public String getEmail() {
        return email;
    }
    /**
     * This function to set the email of user
     * @param email This is the email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * This function to get the Password of user
     * @return Password of user
     */
    public String getPassword() {
        return password;
    }
    /**
     * This function to set the password of user
     * @param password This is the password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * This function to get the National Id of user
     * @return National Id of user
     */
    public String getNationalId() {
        return nationalId;
    }
    /**
     * This function to set the National Id of user
     * @param nationalId This is the National Id of user
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    /**
     * This function to get the Phone of user
     * @return Phone of user
     */
    public String getPhone() {
        return phone;
    }
    /**
     * This function to set the phone of user
     * @param phone This is the phone of user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * This function to get the Default Location of user
     * @return Default Location of user
     */
    public String getDefaultLocation() {
        return defaultLocation;
    }
    /**
     * This function to set the Default Location of user
     * @param defaultLocation This is the Default Location of user
     */
    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }
    /**
     * This function to get the Role of user
     * @return Role of user
     */
    public String getRole() {
        return role;
    }
    /**
     * This function to set the Role of user
     * @param role This is the Role of user
     */
    public void setRole(String role) {
        this.role = role;
    }
}


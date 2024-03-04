public class Contact {
    // Variables to store contact details
    private String contactId; // Unique identifier for the contact
    private String firstName; // First name of the contact
    private String lastName;  // Last name of the contact
    private String phone;     // Phone number of the contact
    private String address;   // Address of the contact

    // Constructor to initialize a new Contact object
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validation for each input according to the requirements
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }
        
        // Assigning values to the variables
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters for each field
    // Note: No setter for contactId as it should not be changeable
    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Overriding equals method to compare Contact objects based on their fields
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact other = (Contact) obj;
        return contactId.equals(other.contactId) && firstName.equals(other.firstName) &&
               lastName.equals(other.lastName) && phone.equals(other.phone) &&
               address.equals(other.address);
    }
}

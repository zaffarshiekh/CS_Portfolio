import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private Map<String, Contact> contacts; // HashMap to store Contact objects

    public ContactService() {
        contacts = new HashMap<>();
    }

    // Method to add a new contact
    public void addContact(Contact contact) {
        if (contact != null && !contacts.containsKey(contact.getContactId())) {
            contacts.put(contact.getContactId(), contact);
        }
    }

    // Method to delete a contact based on contact ID
    public void deleteContact(String contactId) {
        contacts.remove(contactId);
    }

    // Method to update contact fields
    public void updateContactField(String contactId, String field, String value) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            switch (field) {
                case "firstName": contact.setFirstName(value); break;
                case "lastName": contact.setLastName(value); break;
                case "phone": contact.setPhone(value); break;
                case "address": contact.setAddress(value); break;
                default: throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
    }

    // Method to retrieve a contact based on contact ID
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}

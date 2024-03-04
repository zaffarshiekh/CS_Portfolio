import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService contactService;

    @BeforeEach
    public void setup() {
        contactService = new ContactService(); // Initialize ContactService before each test
    }

    @Test
    public void testContactService() {
        // Creating a new contact
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        
        // Testing addContact method
        contactService.addContact(contact);
        Contact retrievedContact = contactService.getContact("1234567890");
        Assertions.assertEquals(contact, retrievedContact);
        
        // Testing updateContactField method
        contactService.updateContactField("1234567890", "firstName", "Jane");
        retrievedContact = contactService.getContact("1234567890");
        Assertions.assertEquals("Jane", retrievedContact.getFirstName());
        
        // Testing deleteContact method
        contactService.deleteContact("1234567890");
        retrievedContact = contactService.getContact("1234567890");
        Assertions.assertNull(retrievedContact);
    }
}

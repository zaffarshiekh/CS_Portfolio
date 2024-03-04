import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testContactCreationValid() {
        // Test creating a contact with valid details
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        Assertions.assertNotNull(contact);
        Assertions.assertEquals("12345", contact.getContactId());
        Assertions.assertEquals("John", contact.getFirstName());
        Assertions.assertEquals("Doe", contact.getLastName());
        Assertions.assertEquals("1234567890", contact.getPhone());
        Assertions.assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testContactIdTooLong() {
        // Test creating a contact with a contact ID that's too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testFirstNameTooLong() {
        // Test creating a contact with a first name that's too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "Johnathan", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testLastNameTooLong() {
        // Test creating a contact with a last name that's too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "DoeSmith", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testPhoneNumberInvalidLength() {
        // Test creating a contact with a phone number of invalid length
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "12345678901", "123 Main St");
        });
    }

    @Test
    public void testAddressTooLong() {
        // Test creating a contact with an address that's too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", "123 Main Street, Springfield, State, Country");
        });
    }

    // Additional tests for null inputs can be added here
}

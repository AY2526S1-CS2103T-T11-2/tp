package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("peterjacku.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name
        assertFalse(Email.isValidEmail("@")); // missing local and domain

        // positive tests for local and u.nus.edu domain
        assertTrue(Email.isValidEmail("peterjack@u.nus.edu"));
        assertTrue(Email.isValidEmail("jothamwong@u.nus.edu"));

        // negative tests for local
        assertFalse(Email.isValidEmail(" jothamwong@u.nus.edu")); // leading space
        assertFalse(Email.isValidEmail("jotham______wong@u.nus.edu")); // no underscores
        assertFalse(Email.isValidEmail("jotham-wong@u.nus.edu")); // dash not allowed
        assertFalse(Email.isValidEmail("jothamwong@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("jotham+wong@u.nus.edu")); // + in local
        assertFalse(Email.isValidEmail("jotham@wong@u.nus.edu")); // additional @
        assertFalse(Email.isValidEmail("jotham//wong@u.nus.edu")); // // in local

        // negative tests for u.nus.edu domain
        assertFalse(Email.isValidEmail("peterjack@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@.u.nus.edu")); // not nus domain
        assertFalse(Email.isValidEmail("peterjack@example.c")); // not nus domain

        // valid email
        assertTrue(Email.isValidEmail("a@u.nus.edu")); // minimal
        assertTrue(Email.isValidEmail("test@u.nus.edu")); // alphabets only
        assertTrue(Email.isValidEmail("123@u.nus.edu")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("EDWARD@u.nus.edu")); // uppercase
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu"));
    }

    @Test
    public void equals() {
        Email email = new Email("valid@u.nus.edu");

        // same values -> returns true
        assertTrue(email.equals(new Email("valid@u.nus.edu")));

        // same values in uppercase -> returns true
        assertTrue(email.equals(new Email("VALID@u.nus.edu")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("othervalid@u.nus.edu")));
    }
}

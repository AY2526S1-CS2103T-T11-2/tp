package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class IdentifierTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Identifier(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidIdentifier = "";
        assertThrows(IllegalArgumentException.class, () -> new Identifier(invalidIdentifier));
    }

    @Test
    public void isValidIdentifier() {
        // null identifier
        assertThrows(NullPointerException.class, () -> Identifier.isValidIdentifier(null));

        // blank identifier
        assertFalse(Identifier.isValidIdentifier("")); // empty string
        assertFalse(Identifier.isValidIdentifier(" ")); // spaces only

        // invalid index
        assertFalse(Identifier.isValidIdentifier("0"));
        assertFalse(Identifier.isValidIdentifier("-1"));
        assertFalse(Identifier.isValidIdentifier("one"));

        // valid index
        assertTrue(Identifier.isValidIdentifier("1"));
        assertTrue(Identifier.isValidIdentifier("12345"));

        // invalid studentId
        assertFalse(Identifier.isValidIdentifier("a00000000z"));
        assertFalse(Identifier.isValidIdentifier("b0000000z"));

        // valid studentId
        assertTrue(Identifier.isValidIdentifier("a0000000z"));
        assertTrue(Identifier.isValidIdentifier("A0000000Z"));
        assertTrue(Identifier.isValidIdentifier("a0123456z"));
    }

    @Test
    public void getMessageIdentifierNotFound() {
        assertEquals(new Identifier("1").getMessageIdentifierNotFound(),
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertEquals(new Identifier("A0000000A").getMessageIdentifierNotFound(),
                MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX);

    }

    @Test
    public void retrievePerson() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // retrieve by index
        Identifier indexIdentifier;

        indexIdentifier = new Identifier("1");
        assertEquals(ALICE, indexIdentifier.retrievePerson(model));

        indexIdentifier = new Identifier("2");
        assertEquals(BENSON, indexIdentifier.retrievePerson(model));

        indexIdentifier = new Identifier("3");
        assertEquals(CARL, indexIdentifier.retrievePerson(model));

        // retrieve by StudentId
        Identifier studentIdIdentifier;

        studentIdIdentifier = new Identifier(DANIEL.getStudentId().toString());
        assertEquals(DANIEL, studentIdIdentifier.retrievePerson(model));

        studentIdIdentifier = new Identifier(ELLE.getStudentId().toString());
        assertEquals(ELLE, studentIdIdentifier.retrievePerson(model));

        studentIdIdentifier = new Identifier(FIONA.getStudentId().toString());
        assertEquals(FIONA, studentIdIdentifier.retrievePerson(model));

        // unable to retrieve Person
        Identifier nonExistantIndexIdentifier = new Identifier("10000000");
        assertThrows(PersonNotFoundException.class, () -> nonExistantIndexIdentifier.retrievePerson(model));

        Identifier nonExistantStudentIdIdentifier = new Identifier("A7214535Q");
        assertThrows(PersonNotFoundException.class, () -> nonExistantStudentIdIdentifier.retrievePerson(model));
    }


    @Test
    public void equals() {
        Identifier indexIdentifierA = new Identifier("1");
        Identifier indexIdentifierB = new Identifier("2");

        Identifier studentIdIdentifierA = new Identifier("a0000000z");
        Identifier studentIdIdentifierB = new Identifier("a0123456b");

        // same object -> returns true
        assertTrue(indexIdentifierA.equals(indexIdentifierA));

        // same values -> returns true
        assertTrue(indexIdentifierA.equals(new Identifier("1")));
        assertTrue(studentIdIdentifierA.equals(new Identifier("a0000000z")));

        // same studentId in upper case -> return true
        assertTrue(studentIdIdentifierA.equals(new Identifier("A0000000Z")));

        // null -> returns false
        assertFalse(indexIdentifierA.equals(null));

        // different types -> returns false
        assertFalse(indexIdentifierA.equals(new Object()));

        // different index -> return false
        assertFalse(indexIdentifierA.equals(indexIdentifierB));

        // different studentId -> return false
        assertFalse(studentIdIdentifierA.equals(studentIdIdentifierB));

        // index identifer vs studentId identifier -> return false
        assertFalse(indexIdentifierA.equals(studentIdIdentifierA));
        assertFalse(studentIdIdentifierB.equals(indexIdentifierB));
    }

}

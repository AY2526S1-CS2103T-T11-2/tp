package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TestPersonBuilder;

public class PersonTest {


    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = TestPersonBuilder.generateSamplePerson();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same student id, all other attributes different -> returns true
        Person editedAlice = new Person.PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same email, all other attributes different -> returns true
        editedAlice = new Person.PersonBuilder(ALICE)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different student id and email, all other attributes same -> returns false
        editedAlice = new Person.PersonBuilder(ALICE)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // student id differs in case, all other attributes same -> returns true
        Person editedBob = new Person.PersonBuilder(BOB)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .build();
        assertTrue(BOB.isSamePerson(editedBob));

        // telegram handle differs, all other attributes same -> returns true
        // as telegram handle not required for person equality
        editedBob = new Person.PersonBuilder(BOB)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
                .build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new Person.PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new Person.PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new Person.PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new Person.PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different attendance -> returns false
        editedAlice = new Person.PersonBuilder(ALICE).withAttendance(VALID_ATTENDANCE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different examScore -> returns false
        editedAlice = new Person.PersonBuilder(ALICE).withExamScores(VALID_EXAM_SCORES_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new Person.PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", studentId=" + ALICE.getStudentId()
                + ", telegramHandle=" + ALICE.getTelegramHandle()
                + ", attendance=" + ALICE.getAttendance() + ", exam scores=" + ALICE.getExamScores()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void hashCodeMethod() {
        // same values -> same hash code
        Person aliceCopy = new Person.PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // same object -> same hash code
        assertEquals(ALICE.hashCode(), ALICE.hashCode());

        // different person -> different hash code
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        // different name -> different hash code
        Person editedAlice = new Person.PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different phone -> different hash code
        editedAlice = new Person.PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> different hash code
        editedAlice = new Person.PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different attendance -> different hash code
        editedAlice = new Person.PersonBuilder(ALICE).withAttendance(VALID_ATTENDANCE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different examScore -> different hash code
        editedAlice = new Person.PersonBuilder(ALICE).withExamScores(VALID_EXAM_SCORES_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different tags -> different hash code
        editedAlice = new Person.PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());
    }
}

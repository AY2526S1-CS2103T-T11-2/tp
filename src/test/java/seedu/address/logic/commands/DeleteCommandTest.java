package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_A;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_B;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(IDENTIFIER_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStudentIdUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Identifier sidIdentifier = new Identifier(personToDelete.getStudentId().toString());
        DeleteCommand deleteCommand = new DeleteCommand(sidIdentifier);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Identifier indexIdentifier = new Identifier("" + model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(indexIdentifier);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_throwsCommandException() {
        StudentId nonExistentStudentId = new StudentId("A9999999A");
        Identifier sidIdentifier = new Identifier(nonExistentStudentId.toString());
        DeleteCommand deleteCommand = new DeleteCommand(sidIdentifier);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(IDENTIFIER_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Identifier outOfBoundIdentifier = new Identifier("2");
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIdentifier);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(IDENTIFIER_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(IDENTIFIER_SECOND_PERSON);

        Identifier sidAIdentifier = new Identifier(STUDENT_ID_A.toString());
        Identifier sidBIdentifier = new Identifier(STUDENT_ID_B.toString());
        DeleteCommand deleteThirdCommand = new DeleteCommand(sidAIdentifier);
        DeleteCommand deleteFourthCommand = new DeleteCommand(sidBIdentifier);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        assertTrue(deleteThirdCommand.equals(deleteThirdCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(IDENTIFIER_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        DeleteCommand deleteThirdCommandCopy = new DeleteCommand(sidAIdentifier);
        assertTrue(deleteThirdCommand.equals(deleteThirdCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        // different student id -> returns false
        assertFalse(deleteThirdCommand.equals(deleteFourthCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Identifier targetIdentifier = new Identifier("1");
        DeleteCommand deleteCommand = new DeleteCommand(targetIdentifier);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}

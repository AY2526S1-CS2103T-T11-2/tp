package systemtests;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB_STR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.ALICE_DEFAULT;
import static seedu.address.testutil.TypicalPersons.AMY_DEFAULT;
import static seedu.address.testutil.TypicalPersons.BOB_DEFAULT;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Person.PersonBuilder;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonUtil;

public class AddCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void add() {
        Model model = getModel();

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a person to a non-empty address book, command with leading spaces and trailing spaces
         * -> added
         */
        Person toAdd = AMY_DEFAULT;
        String command = "   " + AddCommand.COMMAND_WORD + "  " + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + TAG_DESC_FRIEND;
        assertCommandSuccess(command, toAdd);

        /* Case: add a person with all fields same as another person in the address book
        except student ID and email -> added */
        toAdd = new PersonBuilder(AMY_DEFAULT)
                .withStudentId(new StudentId(VALID_STUDENT_ID_BOB_STR)).withEmail(VALID_EMAIL_BOB).build();
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_BOB
                + TELEGRAM_HANDLE_DESC_AMY + TAG_DESC_FRIEND;
        assertCommandSuccess(command, toAdd);

        /* Case: add to empty address book -> added */
        deleteAllPersons();
        assertCommandSuccess(ALICE_DEFAULT);

        /* Case: add a person with tags, command with parameters in random order -> added */
        toAdd = BOB_DEFAULT;
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + PHONE_DESC_BOB + NAME_DESC_BOB
                + TAG_DESC_HUSBAND + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB;
        assertCommandSuccess(command, toAdd);

        /* Case: add a person, missing tags -> added */
        assertCommandSuccess(HOON);

        /* -------------------------- Perform add operation on the shown filtered list ------------------------------ */

        /* Case: filters the person list before adding -> added */
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        assertCommandSuccess(IDA);

        /* ----------------------------------- Perform invalid add operations --------------------------------------- */

        /* Case: add a duplicate person -> rejected */
        command = PersonUtil.getAddCommand(HOON);
        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: add a duplicate person except with different phone -> rejected */
        toAdd = new PersonBuilder(HOON).withPhone(VALID_PHONE_BOB).build();
        command = PersonUtil.getAddCommand(toAdd);
        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: add a duplicate person except with different email -> rejected */
        toAdd = new PersonBuilder(HOON).withEmail(VALID_EMAIL_BOB).build();
        command = PersonUtil.getAddCommand(toAdd);
        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: add a duplicate person except with different tags -> rejected */
        command = PersonUtil.getAddCommand(HOON) + " " + PREFIX_TAG.getPrefix() + "friends";
        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_PERSON);




        /* Case: missing student id -> rejected */
        command = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        /* Case: missing name -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        /* Case: missing phone -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        /* Case: missing email -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY
                + PHONE_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        /* Case: missing telegram handle -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        /* Case: invalid keyword -> rejected */
        command = "adds " + PersonUtil.getPersonDetails(toAdd);
        assertCommandFailure(command, Messages.MESSAGE_UNKNOWN_COMMAND);

        /* Case: invalid name -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + INVALID_NAME_DESC + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, Name.MESSAGE_CONSTRAINTS);

        /* Case: invalid phone -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + INVALID_PHONE_DESC
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, Phone.MESSAGE_CONSTRAINTS);

        /* Case: invalid email -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY
                + INVALID_EMAIL_DESC + TELEGRAM_HANDLE_DESC_AMY;
        assertCommandFailure(command, Email.MESSAGE_CONSTRAINTS);

        /* Case: invalid tag -> rejected */
        command = AddCommand.COMMAND_WORD + STUDENT_ID_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + INVALID_TAG_DESC;
        assertCommandFailure(command, Tag.MESSAGE_CONSTRAINTS);
    }

    /**
     * Executes the {@code AddCommand} that adds {@code toAdd} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Storage} and {@code PersonListPanel} equal to the corresponding components in
     * the current model added with {@code toAdd}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(Person toAdd) {
        assertCommandSuccess(PersonUtil.getAddCommand(toAdd), toAdd);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Person)}. Executes {@code command}
     * instead.
     * @see AddCommandSystemTest#assertCommandSuccess(Person)
     */
    private void assertCommandSuccess(String command, Person toAdd) {
        Model expectedModel = getModel();
        expectedModel.addPerson(toAdd);
        String expectedResultMessage = String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(toAdd));

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Person)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} and {@code PersonListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see AddCommandSystemTest#assertCommandSuccess(String, Person)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} and {@code PersonListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}

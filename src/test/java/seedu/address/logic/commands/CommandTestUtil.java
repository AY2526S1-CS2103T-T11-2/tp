package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Score;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY_STR = "Amy Bee";
    public static final String VALID_NAME_BOB_STR = "Bob Choo";
    public static final String VALID_STUDENT_ID_AMY_STR = "A0000000A";
    public static final String VALID_STUDENT_ID_BOB_STR = "A0000001B";
    public static final String VALID_PHONE_AMY_STR = "61111111";
    public static final String VALID_PHONE_BOB_STR = "61111112";
    public static final String VALID_EMAIL_AMY_STR = "amy@u.nus.edu";
    public static final String VALID_EMAIL_ALICE_STR = "alice@u.nus.edu";
    public static final String VALID_EMAIL_BOB_STR = "bob@u.nus.edu";
    public static final String VALID_ADDRESS_AMY_STR = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB_STR = "Block 123, Bobby Street 3";
    public static final String VALID_ATTENDANCE_AMY_STR =
            "true false false false false false false false false false false";
    public static final String VALID_ATTENDANCE_BOB_STR =
            "true true true true false true false false false false false";
    public static final String VALID_TELEGRAM_HANDLE_AMY_STR = "@Amy";
    public static final String VALID_TELEGRAM_HANDLE_BOB_STR = "@Bob";

    public static final Name VALID_NAME_AMY = new Name(VALID_NAME_AMY_STR);
    public static final Name VALID_NAME_BOB = new Name(VALID_NAME_BOB_STR);
    public static final Phone VALID_PHONE_AMY = new Phone(VALID_PHONE_AMY_STR);
    public static final Phone VALID_PHONE_BOB = new Phone(VALID_PHONE_BOB_STR);
    public static final Email VALID_EMAIL_AMY = new Email(VALID_EMAIL_AMY_STR);
    public static final Email VALID_EMAIL_BOB = new Email(VALID_EMAIL_BOB_STR);
    public static final StudentId VALID_STUDENT_ID_AMY = new StudentId(VALID_STUDENT_ID_AMY_STR);
    public static final StudentId VALID_STUDENT_ID_BOB = new StudentId(VALID_STUDENT_ID_BOB_STR);
    public static final Address VALID_ADDRESS_AMY = new Address(VALID_ADDRESS_AMY_STR);
    public static final Address VALID_ADDRESS_BOB = new Address(VALID_ADDRESS_BOB_STR);
    public static final Attendance VALID_ATTENDANCE_AMY = new Attendance(VALID_ATTENDANCE_AMY_STR);
    public static final Attendance VALID_ATTENDANCE_BOB = new Attendance(VALID_ATTENDANCE_BOB_STR);
    public static final Score VALID_SCORE_MIDTERM = Score.getRecordedScore(Exam.MIDTERM, "50");
    public static final Score VALID_SCORE_FINAL = Score.getRecordedScore(Exam.FINAL, "80");
    public static final ExamScores VALID_EXAM_SCORES_AMY = ExamScores.getEmptyExamScores()
            .updateScore(VALID_SCORE_MIDTERM);
    public static final ExamScores VALID_EXAM_SCORES_BOB = ExamScores.getEmptyExamScores()
            .updateScore(VALID_SCORE_FINAL);
    public static final TelegramHandle VALID_TELEGRAM_HANDLE_AMY = new TelegramHandle(VALID_TELEGRAM_HANDLE_AMY_STR);
    public static final TelegramHandle VALID_TELEGRAM_HANDLE_BOB = new TelegramHandle(VALID_TELEGRAM_HANDLE_BOB_STR);

    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_EXAM = "midterm";
    public static final String VALID_EXAM2 = "final";
    public static final String VALID_SCORE = "0";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY_STR;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB_STR;
    public static final String STUDENT_ID_DESC_AMY = " " + VALID_STUDENT_ID_AMY_STR;
    public static final String STUDENT_ID_DESC_BOB = " " + VALID_STUDENT_ID_BOB_STR;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY_STR;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB_STR;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY_STR;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB_STR;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String EXAM_DESC = " " + PREFIX_EXAM + VALID_EXAM;
    public static final String SCORE_DESC = " " + PREFIX_SCORE + VALID_SCORE;
    public static final String TELEGRAM_HANDLE_DESC_AMY = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_AMY_STR;
    public static final String TELEGRAM_HANDLE_DESC_BOB = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_BOB_STR;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_PHONE + "A"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob@yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_EXAM_DESC = " " + PREFIX_EXAM + "olevel";
    public static final String INVALID_SCORE_DESC = " " + PREFIX_SCORE + "-1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY_STR)
                .withPhone(VALID_PHONE_AMY_STR).withEmail(VALID_EMAIL_AMY_STR)
                .withStudentId(VALID_STUDENT_ID_AMY_STR).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB_STR)
                .withPhone(VALID_PHONE_BOB_STR).withEmail(VALID_EMAIL_BOB_STR)
                .withStudentId(VALID_STUDENT_ID_BOB_STR).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}

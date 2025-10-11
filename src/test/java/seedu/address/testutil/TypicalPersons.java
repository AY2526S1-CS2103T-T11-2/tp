package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB_STR;
import static seedu.address.testutil.TypicalExamScores.EXAM_SCORES_MIDTERM;
import static seedu.address.testutil.TypicalExamScores.EXAM_SCORES_MIDTERM_FINAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Set<Tag> FRIEND_TAG = new HashSet<>(List.of(new Tag("friends")));
    public static final Set<Tag> OWES_MONEY_FRIENDS_TAGS = new HashSet<>(Arrays.asList(
            new Tag("owesMoney"), new Tag("friends")));

    public static final Person ALICE = new Person.PersonBuilder(
            new Name("Alice Pauline"),
            new Phone("94351253"),
            new Email("alice@u.nus.edu"),
            new StudentId("A0000000A"),
            new TelegramHandle("@Alice"))
            .withAttendance(new Attendance(
                    "false false false false false false false false false false false"))
            .withExamScores(EXAM_SCORES_MIDTERM_FINAL)
            .withTags(FRIEND_TAG)
            .build();

    // Represents ALICE with default values as created by the add command
    public static final Person ALICE_DEFAULT = new Person.PersonBuilder(
            new Name("Alice Pauline"),
            new Phone("94351253"),
            new Email("alice@u.nus.edu"),
            new StudentId("A0000000A"),
            new TelegramHandle("@Alice"))
            .withTags(FRIEND_TAG)
            .build();

    public static final Person BENSON = new Person.PersonBuilder(
            new Name("Benson Meier"),
            new Phone("98765432"),
            new Email("johnd@u.nus.edu"),
            new StudentId("A0000001A"),
            new TelegramHandle("@Benson"))
            .withAttendance(new Attendance(
                    "true false false true false true false false true false false"))
            .withExamScores(EXAM_SCORES_MIDTERM)
            .withTags(OWES_MONEY_FRIENDS_TAGS)
            .build();

    public static final Person BENSON_DEFAULT = new Person.PersonBuilder(
            new Name("Benson Meier"),
            new Phone("98765432"),
            new Email("johnd@u.nus.edu"),
            new StudentId("A0000001A"),
            new TelegramHandle("@Benson"))
            .withTags(OWES_MONEY_FRIENDS_TAGS)
            .build();

    public static final Person CARL = new Person.PersonBuilder(
            new Name("Carl Kurz"),
            new Phone("95352563"),
            new Email("heinz@u.nus.edu"),
            new StudentId("A0000002A"),
            new TelegramHandle("@Carl"))
            .build();

    public static final Person DANIEL = new Person.PersonBuilder(
            new Name("Daniel Meier"),
            new Phone("87652533"),
            new Email("cornelia@u.nus.edu"),
            new StudentId("A0000003A"),
            new TelegramHandle("@Daniel"))
            .withTags(FRIEND_TAG)
            .build();

    public static final Person ELLE = new Person.PersonBuilder(
            new Name("Elle Meyer"),
            new Phone("94822241"),
            new Email("werner@u.nus.edu"),
            new StudentId("A0000004A"),
            new TelegramHandle("@Elle"))
            .build();

    public static final Person FIONA = new Person.PersonBuilder(
            new Name("Fiona Kunz"),
            new Phone("94824271"),
            new Email("lydia@u.nus.edu"),
            new StudentId("A0000005A"),
            new TelegramHandle("@Fiona"))
            .build();

    public static final Person GEORGE = new Person.PersonBuilder(
            new Name("George Best"),
            new Phone("94824421"),
            new Email("anna@u.nus.edu"),
            new StudentId("A0000006A"),
            new TelegramHandle("@George"))
            .build();

    // Manually added
    public static final Person HOON = new Person.PersonBuilder(
            new Name("Hoon Meier"),
            new Phone("84824241"),
            new Email("stefan@u.nus.edu"),
            new StudentId("A0000007A"),
            new TelegramHandle("@Hoon"))
            .build();

    public static final Person IDA = new Person.PersonBuilder(
            new Name("Ida Mueller"),
            new Phone("84821311"),
            new Email("hans@u.nus.edu"),
            new StudentId("A0000008A"),
            new TelegramHandle("@Ida"))
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new Person.PersonBuilder(
            new Name(VALID_NAME_AMY_STR),
            new Phone(VALID_PHONE_AMY_STR),
            new Email(VALID_EMAIL_AMY_STR),
            new StudentId(VALID_STUDENT_ID_AMY_STR),
            new TelegramHandle(VALID_TELEGRAM_HANDLE_AMY_STR))
            .withAttendance(VALID_ATTENDANCE_AMY)
            .withExamScores(VALID_EXAM_SCORES_AMY)
            .withTags(new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND))))
            .build();

    // Represents AMY with default values as created by the add command
    public static final Person AMY_DEFAULT = new Person.PersonBuilder(
            new Name(VALID_NAME_AMY_STR),
            new Phone(VALID_PHONE_AMY_STR),
            new Email(VALID_EMAIL_AMY_STR),
            new StudentId(VALID_STUDENT_ID_AMY_STR),
            new TelegramHandle(VALID_TELEGRAM_HANDLE_AMY_STR))
            .withTags(new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND))))
            .build();

    public static final Person BOB = new Person.PersonBuilder(
            new Name(VALID_NAME_BOB_STR),
            new Phone(VALID_PHONE_BOB_STR),
            new Email(VALID_EMAIL_BOB_STR),
            new StudentId(VALID_STUDENT_ID_BOB_STR),
            new TelegramHandle(VALID_TELEGRAM_HANDLE_BOB_STR))
            .withAttendance(VALID_ATTENDANCE_BOB)
            .withExamScores(VALID_EXAM_SCORES_BOB)
            .withTags(new HashSet<>(Arrays.asList(new Tag(VALID_TAG_HUSBAND), new Tag(VALID_TAG_FRIEND))))
            .build();

    public static final Person BOB_DEFAULT = new Person.PersonBuilder(
            new Name(VALID_NAME_BOB_STR),
            new Phone(VALID_PHONE_BOB_STR),
            new Email(VALID_EMAIL_BOB_STR),
            new StudentId(VALID_STUDENT_ID_BOB_STR),
            new TelegramHandle(VALID_TELEGRAM_HANDLE_BOB_STR))
            .withTags(new HashSet<>(Arrays.asList(new Tag(VALID_TAG_HUSBAND), new Tag(VALID_TAG_FRIEND))))
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an unsorted {@code AddressBook} with typical persons.
     */
    public static AddressBook getUnsortedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getUnsortedTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with typical persons with attendance and exam scores set to default values.
     */
    public static AddressBook getDefaultAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getDefaultTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getUnsortedTypicalPersons() {
        return new ArrayList<>(Arrays.asList(BENSON, DANIEL, ALICE, FIONA, ELLE, CARL, GEORGE));
    }

    public static List<Person> getDefaultTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE_DEFAULT, BENSON_DEFAULT, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

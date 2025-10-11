package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: Contact details are present and not null, field values are validated, immutable.
 */
public class Person {

    /**
     * Comparator of a person through their name
     */
    public static final Comparator<Person> NAME_COMPARATOR =
            Comparator.<Person, String>comparing((Person p) -> p.getName().fullName);

    // TODO: Add comparison by grades when grades are implemented
    public static final Comparator<Person> GRADE_COMPARATOR = null;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final StudentId studentId;
    private final TelegramHandle telegramHandle;

    // Data fields (can be optional)
    private final Attendance attendance;
    private final Set<Tag> tags;
    private final ExamScores examScores;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.studentId = builder.studentId;
        this.telegramHandle = builder.telegramHandle;
        this.attendance = builder.attendance;
        this.tags = builder.tags;
        this.examScores = builder.examScores;
    }

    /**
     * Builder class for Person.
     */
    public static class PersonBuilder {
        // Required fields
        private Name name;
        private Phone phone;
        private Email email;
        private StudentId studentId;
        private TelegramHandle telegramHandle;
        // Optional fields
        private Attendance attendance = new Attendance();
        private Set<Tag> tags = new HashSet<>();
        private ExamScores examScores = ExamScores.getEmptyExamScores();

        /**
         * Constructs the Person class with the Builder pattern.
         */
        public PersonBuilder(Name name, Phone phone, Email email, StudentId studentId, TelegramHandle telegramHandle) {
            requireAllNonNull(name, phone, email, studentId);
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.studentId = studentId;
            this.telegramHandle = telegramHandle;
        }

        /**
         * Constructs the PersonBuilder class from a Person.
         */
        public PersonBuilder(Person person) {
            requireAllNonNull(person);
            this.name = person.getName();
            this.phone = person.getPhone();
            this.email = person.getEmail();
            this.studentId = person.getStudentId();
            this.telegramHandle = person.getTelegramHandle();
            this.attendance = person.getAttendance();
            this.tags = new HashSet<>(person.getTags());
            this.examScores = person.getExamScores();
        }

        /**
         * Changes the name of the PersonBuilder class.
         */
        public PersonBuilder withName(Name name) {
            requireAllNonNull(name);
            this.name = name;
            return this;
        }

        /**
         * Changes the phone of the PersonBuilder class.
         */
        public PersonBuilder withPhone(Phone phone) {
            requireAllNonNull(phone);
            this.phone = phone;
            return this;
        }

        /**
         * Changes the email of the PersonBuilder class.
         */
        public PersonBuilder withEmail(Email email) {
            requireAllNonNull(email);
            this.email = email;
            return this;
        }

        /**
         * Changes the student id of the PersonBuilder class.
         */
        public PersonBuilder withStudentId(StudentId studentId) {
            requireAllNonNull(studentId);
            this.studentId = studentId;
            return this;
        }

        /**
         * Changes the telegram handle of the PersonBuilder class.
         */
        public PersonBuilder withTelegramHandle(TelegramHandle telegramHandle) {
            requireAllNonNull(telegramHandle);
            this.telegramHandle = telegramHandle;
            return this;
        }
        /**
         * Sets the optional attendance for the person and returns the builder.
         */
        public PersonBuilder withAttendance(Attendance attendance) {
            this.attendance = attendance;
            return this;
        }

        /**
         * Sets the optional tags for the person and returns the builder.
         */
        public PersonBuilder withTags(Set<Tag> tags) {
            this.tags.addAll(tags);
            return this;
        }

        /**
         * Sets the optional tags for the person and returns the builder.
         */
        public PersonBuilder withTags(String... tags) {
            this.tags = Arrays.stream(tags)
                    .map(Tag::new)
                    .collect(Collectors.toSet());
            return this;
        }

        /**
         * Sets the optional exam scores for the person and returns the builder.
         */
        public PersonBuilder withExamScores(ExamScores examScores) {
            this.examScores = examScores;
            return this;
        }

        /**
         * Creates and returns the final Person object.
         */
        public Person build() {
            return new Person(this);
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ExamScores getExamScores() {
        return this.examScores;
    }

    /**
     * Returns true if both persons have the same student id or email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && (otherPerson.getStudentId().equals(getStudentId())
                || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && studentId.equals(otherPerson.studentId)
                && telegramHandle.equals(otherPerson.telegramHandle)
                && attendance.equals(otherPerson.attendance)
                && examScores.equals(otherPerson.examScores)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, studentId, telegramHandle, attendance, examScores, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("studentId", studentId)
                .add("telegramHandle", telegramHandle)
                .add("attendance", attendance)
                .add("exam scores", examScores)
                .add("tags", tags)
                .toString();
    }

}

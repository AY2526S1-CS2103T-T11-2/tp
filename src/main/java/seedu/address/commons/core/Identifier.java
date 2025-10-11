package seedu.address.commons.core;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * An Object that can be used to uniquely identify a person.
 * Guarantees: immutable; is valid as declared in {@link #isValidIdentifier(String)}
 */
public class Identifier {

    private enum IdentifierType {
        INDEX,
        STUDENT_ID,
        INVALID // Only present in ctor, is never a valid value outside of a failed ctor
    }

    public static final String MESSAGE_CONSTRAINTS = "Identifier must be a valid Student ID or index.";

    private final Index index;
    private final StudentId studentId;
    private final IdentifierType source;

    /**
     * Constructs a {@code Identifier} from either a student ID or an index.
     *
     * @param input A String representing either a valid student id or index.
     */
    public Identifier(String input) {
        requireNonNull(input);
        source = getIdentifierType(input);
        if (source == IdentifierType.INDEX) {
            index = Index.fromOneBased(Integer.parseInt(input));
            studentId = null;
        } else if (source == IdentifierType.STUDENT_ID) {
            index = null;
            studentId = new StudentId(input);
        } else {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    private static IdentifierType getIdentifierType(String input) {
        if (Index.isValidOneBasedIndex(input)) {
            return IdentifierType.INDEX;
        } else if (StudentId.isValidStudentId(input)) {
            return IdentifierType.STUDENT_ID;
        } else {
            return IdentifierType.INVALID;
        }
    }

    /**
     * Returns if a given string is a valid student id or index.
     */
    public static boolean isValidIdentifier(String test) {
        return Index.isValidOneBasedIndex(test) || StudentId.isValidStudentId(test);
    }

    /**
     * Retrieves the {@code Person} object from the input model matching this {@code Identifier}.
     * @param model The model containing the Address Book to retrieve Person from
     * @return {@code Person} object in Address Book which matches this {@code Identifier}
     * @throws PersonNotFoundException if no {@code Person} in Address Book matches this {@code Identifier}
     */
    public Person retrievePerson(Model model) throws PersonNotFoundException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();
        Person identifiedPerson;

        if (source == IdentifierType.INDEX) {
            if (index.getZeroBased() >= personList.size()) {
                throw new PersonNotFoundException();
            }
            identifiedPerson = personList.get(index.getZeroBased());
        } else {
            identifiedPerson = personList.stream()
                    .filter(person -> person.getStudentId().equals(studentId))
                    .findFirst()
                    .orElseThrow(() -> new PersonNotFoundException());
        }

        return identifiedPerson;
    }

    /**
     * Returns an appropriate error message depending on Identifier source.
     */
    public String getMessageIdentifierNotFound() {
        if (source == IdentifierType.INDEX) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        } else {
            return MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Identifier)) {
            return false;
        }

        Identifier otherIdentifier = (Identifier) other;

        if (otherIdentifier.source != this.source) {
            return false;
        }

        if (source == IdentifierType.INDEX) {
            return otherIdentifier.index.equals(this.index);
        } else {
            return otherIdentifier.studentId.equals(this.studentId);
        }
    }

    @Override
    public String toString() {
        return (source == IdentifierType.INDEX) ? this.index.toString() : this.studentId.toString();
    }
}

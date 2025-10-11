package seedu.address.testutil;

import seedu.address.commons.core.Identifier;

/**
 * A utility class containing a list of {@code Identifier} objects to be used in tests.
 */
public class TypicalIdentifiers {
    public static final Identifier IDENTIFIER_FIRST_PERSON = new Identifier("1");
    public static final Identifier IDENTIFIER_SECOND_PERSON = new Identifier("2");
    public static final Identifier IDENTIFIER_THIRD_PERSON = new Identifier("3");
    public static final Identifier IDENTIFIER_INDEX_OUT_OF_RANGE = new Identifier(String.valueOf(Integer.MAX_VALUE));
    public static final Identifier IDENTIFIER_STUDENT_ID_NOT_FOUND = new Identifier("A4164592M");
}

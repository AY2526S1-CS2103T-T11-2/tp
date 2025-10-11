package seedu.address.testutil;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);

    public static final String FIRST_PERSON_STR = "1";
    public static final String SECOND_PERSON_STR = "2";
    public static final String THIRD_PERSON_STR = "3";

    public static final Identifier FIRST_PERSON_IDENTIFIER = new Identifier("1");
    public static final Identifier SECOND_PERSON_IDENTIFIER = new Identifier("2");
    public static final Identifier THIRD_PERSON_IDENTIFIER = new Identifier("3");

}

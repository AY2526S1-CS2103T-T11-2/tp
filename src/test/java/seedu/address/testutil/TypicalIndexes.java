package seedu.address.testutil;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_OUT_OF_RANGE = Index.fromOneBased(Integer.MAX_VALUE);

    public static final String FIRST_PERSON_STR = "1";
    public static final String SECOND_PERSON_STR = "2";
    public static final String THIRD_PERSON_STR = "3";
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should be of the format @username "
            + "and adhere to the following constraints:\n"
            + "1. The username should only contain alphanumeric characters and underscores.";
    // alphanumeric and special characters
    public static final String VALIDATION_REGEX = "^\\w+$";

    public final String value;

    /**
     * Constructs an {@code TelegramHandle}.
     *
     * @param telegramHandle A valid telegram handle.
     */
    public TelegramHandle(String telegramHandle) {
        requireNonNull(telegramHandle);
        checkArgument(isValidTelegramHandle(telegramHandle), MESSAGE_CONSTRAINTS);
        value = telegramHandle;
    }

    /**
     * Returns if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramHandle)) {
            return false;
        }

        TelegramHandle otherTelegramHandle = (TelegramHandle) other;
        return value.equals(otherTelegramHandle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

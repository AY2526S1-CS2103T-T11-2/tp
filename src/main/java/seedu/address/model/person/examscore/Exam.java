package seedu.address.model.person.examscore;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isUnsignedInteger;

import java.util.Objects;

/**
 * Represents an exam with an exam name and maximum score.
 */
public class Exam {

    public static final String MESSAGE_SCORE_INVALID_INTEGER = "Max score must be a non-negative integer";

    private final String name;
    private int maxScore;

    /**
     * Constructs an {@code Exam}.
     *
     * @param name A valid name.
     * @param maxScore A valid max score.
     */
    public Exam(String name, int maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public String getName() {
        return this.name;
    }

    public void setMaxScore(int newScore) {
        checkArgument(newScore >= 0, MESSAGE_SCORE_INVALID_INTEGER);
        this.maxScore = newScore;
    }

    /**
     * Returns if a given string represents a valid score.
     */
    public boolean isValidScore(String test) {
        if (!isUnsignedInteger(test)) {
            return false;
        }

        int intTest = Integer.parseInt(test);
        return intTest <= this.maxScore;
    }

    public String getMessageScoreConstraints() {
        return "Grade for " + this.name + " should be an integer between 0 and " + this.maxScore;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Exam)) {
            return false;
        }

        Exam otherExam = (Exam) other;
        return this.maxScore == otherExam.maxScore && this.name.equals(otherExam.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.maxScore);
    }

    @Override
    public String toString() {
        return this.name;
    }

}

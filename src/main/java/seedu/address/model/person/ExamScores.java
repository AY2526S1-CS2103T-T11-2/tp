package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's exam scores in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamScores(Score[])}
 */
public class ExamScores {

    private static final String IS_INTEGER_REGEX = "\\d+";
    private final Score[] arrayOfScores;

    /**
     * Creates an {@code ExamScores} object from an array of {@code Score}.
     */
    public ExamScores(Score[] arrayOfScores) {
        requireNonNull(arrayOfScores);
        checkArgument(isValidExamScores(arrayOfScores));
        this.arrayOfScores = arrayOfScores;
    }

    public static int getNumOfExams() {
        return ExamList.numOfExams();
    }

    /**
     * Returns a copy of ExamScores object with appropriate score replaced with new input score
     *
     * @param newScore Score object representing new score to update object with
     * @return new ExamScores object updated with new Score
     */
    public ExamScores updateScore(Score newScore) {
        Score[] arrayOfScoresCopy = this.arrayOfScores.clone();
        for (int i = 0; i < arrayOfScores.length; i++) {
            if (arrayOfScoresCopy[i].getExam() == newScore.getExam()) {
                arrayOfScoresCopy[i] = newScore;
                break;
            }
        }
        return new ExamScores(arrayOfScoresCopy);
    }

    /**
     * Returns an immutable score array, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Score[] getArrayOfScores() {
        return arrayOfScores.clone();
    }

    @Override
    public String toString() {
        String out = arrayOfScores[0].toString();
        for (int i = 1; i < arrayOfScores.length; i++) {
            out += "\n";
            out += arrayOfScores[i].toString();
        }
        return out;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExamScores)) {
            return false;
        }

        ExamScores otherExamScores = (ExamScores) other;
        for (int i = 0; i < arrayOfScores.length; i++) {
            if (!arrayOfScores[i].equals(otherExamScores.arrayOfScores[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static ExamScores getEmptyExamScores() {
        Score[] arrayOfScores = new Score[ExamList.numOfExams()];
        List<Exam> examList = ExamList.values();
        for (int i = 0; i < arrayOfScores.length; i++) {
            arrayOfScores[i] = Score.getUnrecordedScore(examList.get(i));
        }
        return new ExamScores(arrayOfScores);
    }

    /**
     * Returns true if a given array of Scores represents a valid set of exam scores.
     *
     * @param arrayOfScores
     */
    public static boolean isValidExamScores(Score[] arrayOfScores) {

        if (arrayOfScores.length != ExamList.numOfExams()) {
            return false;
        }

        List<Exam> examList = ExamList.values();
        for (int i = 0; i < ExamList.numOfExams(); i++) {
            if (arrayOfScores[i] == null) {
                return false;
            }
            if (examList.get(i) != arrayOfScores[i].getExam()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrayOfScores);
    }

    /**
     * Checks if the new max score is valid, by comparing it with the corresponding score for the given exam.
     * @param exam the exam to be edited
     * @param newMaxScore the new max score to compare the recorded scores against
     * @return true if the new max score is valid, else false.
     */
    public boolean newMaxScoreValid(Exam exam, int newMaxScore) {
        for (int i = 0; i < arrayOfScores.length; i++) {
            if (arrayOfScores[i].getExam().equals(exam)) {
                return arrayOfScores[i].isNewMaxScoreValid(newMaxScore);
            }
        }
        return true;
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.Optional;


/**
 * Represents a Person's score for an exam, may represent an unrecorded score.
 * Guarantees: immutable; recorded scores are valid as declared in {@link #isValidScore(String)}
 */
public abstract class Score {

    public final boolean isRecorded;
    protected final Exam exam;

    private Score(Exam exam, boolean recorded) {
        requireNonNull(exam);
        this.exam = exam;
        this.isRecorded = recorded;
    }

    public static Score getUnrecordedScore(Exam exam) {
        return new UnrecordedScore(exam);
    }

    public static Score getRecordedScore(Exam exam, String score) {
        return new RecordedScore(exam, score);
    }

    public Exam getExam() {
        return this.exam;
    }

    /**
     * Returns an {@Code Optional<Integer>} object representing the recorded score for class
     */
    public abstract Optional<Integer> getScore();

    private static class RecordedScore extends Score {
        private final int score;

        public RecordedScore(Exam exam, String strScore) {
            super(exam, true);
            checkArgument(exam.isValidScore(strScore), exam.getMessageScoreConstraints());
            this.score = Integer.parseInt(strScore);
        }

        @Override
        public Optional<Integer> getScore() {
            return Optional.of(score);
        }

        @Override
        public String toString() {
            return exam.getName() + ": " + this.score + "/" + exam.getMaxScore();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RecordedScore otherScore)) {
                return false;
            }

            return (score == otherScore.score) && (exam == otherScore.exam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isRecorded, exam, score);
        }
    }

    private static class UnrecordedScore extends Score {

        public UnrecordedScore(Exam exam) {
            super(exam, false);
        }

        @Override
        public Optional<Integer> getScore() {
            return Optional.empty();
        }

        @Override
        public String toString() {
            return exam.getName() + ": unrecorded";
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UnrecordedScore otherScore)) {
                return false;
            }

            return (exam == otherScore.exam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isRecorded, exam);
        }
    }
}

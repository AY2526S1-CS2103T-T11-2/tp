package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * An enum class that stores exam information including exam name and maximum score
 */
public enum Exam {
    MIDTERM("midterm", 70),
    FINAL("final", 100);

    public static final String IS_INTEGER_REGEX = "\\d+";
    public static final int NUM_OF_EXAM = Exam.values().length;
    public static final String MESSAGE_CONSTRAINTS = "Exam name must be one of " + Exam.getAllExamName();
    private final String name;
    private final int maxScore;

    private Exam(String name, int maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns if a given string represents a valid score.
     */
    public boolean isValidScore(String test) {
        if (!test.matches(IS_INTEGER_REGEX)) {
            return false;
        }

        int intTest = Integer.parseInt(test);
        return (intTest >= 0) && (intTest <= this.maxScore);
    }

    public String getMessageScoreConstraints() {
        return "Grade for " + this.name + " should be an integer between 0 and " + this.maxScore;
    }

    /**
     * Get associated Exam object from input examName.
     *
     * @param examName A valid exam name
     */
    public static Exam getExamFromName(String examName) {
        checkArgument(isValidExamName(examName), MESSAGE_CONSTRAINTS);
        for (int i = 0; i < NUM_OF_EXAM; i++) {
            Exam currentExam = Exam.values()[i];
            if (examName.equals(currentExam.name)) {
                return currentExam;
            }
        }
        return null;
    }


    /**
     * Returns if a given string is a valid exam name.
     */
    public static boolean isValidExamName(String examName) {
        for (int i = 0; i < NUM_OF_EXAM; i++) {
            Exam currentExam = Exam.values()[i];
            if (examName.equals(currentExam.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string with all exam names seperated by commas
     */
    public static String getAllExamName() {
        String out = Exam.values()[0].name;
        for (int i = 1; i < NUM_OF_EXAM; i++) {
            out += ", ";
            out += Exam.values()[i].name;
        }
        return out;
    }
}

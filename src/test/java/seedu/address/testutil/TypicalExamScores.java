package seedu.address.testutil;

import static seedu.address.testutil.TypicalScores.FINAL_SCORE_A;
import static seedu.address.testutil.TypicalScores.MIDTERM_SCORE_A;

import seedu.address.model.person.ExamScores;

/**
 * A utility class containing a list of {@code ExamScore} objects to be used in tests.
 */
public class TypicalExamScores {
    public static final ExamScores EMPTY_EXAM_SCORES = ExamScores.getEmptyExamScores();
    public static final ExamScores EXAM_SCORES_MIDTERM = EMPTY_EXAM_SCORES.updateScore(MIDTERM_SCORE_A);
    public static final ExamScores EXAM_SCORES_FINAL = EMPTY_EXAM_SCORES.updateScore(FINAL_SCORE_A);
    public static final ExamScores EXAM_SCORES_MIDTERM_FINAL = EXAM_SCORES_MIDTERM.updateScore(FINAL_SCORE_A);
}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Score;


/**
 * Jackson-friendly version of {@link ExamScores}.
 */
public class JsonAdaptedExamScores {

    public static final String WRONG_NUMBER_OF_SCORES_MESSAGE = "Wrong number of scores!";
    public static final String WRONG_ORDER_OF_SCORES_MESSAGE = "Wrong order of scores!";

    private final List<JsonAdaptedScore> scores = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedScore} with the given {@code examName} and {@code score}.
     */
    @JsonCreator
    public JsonAdaptedExamScores(@JsonProperty("scores") List<JsonAdaptedScore> scores) {
        this.scores.addAll(scores);
    }

    /**
     * Converts a given {@code ExamScores} into this class for Jackson use.
     */
    public JsonAdaptedExamScores(ExamScores source) {
        scores.addAll(Arrays.stream(source.getArrayOfScores())
                .map(JsonAdaptedScore::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted examScores object into the model's {@code ExamScores} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted examScore.
     */
    public ExamScores toModelType() throws IllegalValueException {

        if (scores.size() != Exam.NUM_OF_EXAM) {
            throw new IllegalValueException(WRONG_NUMBER_OF_SCORES_MESSAGE);
        }

        final Score[] arrayOfScores = new Score[Exam.NUM_OF_EXAM];
        for (int i = 0; i < Exam.NUM_OF_EXAM; i++) {
            arrayOfScores[i] = scores.get(i).toModelType();
        }


        if (!ExamScores.isValidExamScores(arrayOfScores)) {
            throw new IllegalValueException(WRONG_ORDER_OF_SCORES_MESSAGE);
        }

        return new ExamScores(arrayOfScores);
    }
}

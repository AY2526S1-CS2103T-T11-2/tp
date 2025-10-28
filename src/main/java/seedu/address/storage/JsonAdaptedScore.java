package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.examscore.Exam;
import seedu.address.model.person.examscore.ExamList;
import seedu.address.model.person.examscore.Score;

/**
 * Jackson-friendly version of {@link Score}.
 */
public class JsonAdaptedScore {

    private final String examName;
    private final String score;

    /**
     * Constructs a {@code JsonAdaptedScore} with the given {@code examName} and {@code score}.
     */
    @JsonCreator
    public JsonAdaptedScore(@JsonProperty("examName") String examName, @JsonProperty("score") String score) {
        this.examName = examName;
        this.score = score;
    }

    /**
     * Converts a given {@code Score} into this class for Jackson use.
     */
    public JsonAdaptedScore(Score source) {
        this.examName = source.getExam().getName();
        if (source.isRecorded) {
            this.score = source.getScore().get().toString();
        } else {
            this.score = "unrecorded";
        }
    }

    /**
     * Converts this Jackson-friendly adapted score object into the model's {@code Score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted score.
     */
    public Score toModelType() throws IllegalValueException {
        if (!ExamList.isValidExamName(examName)) {
            throw new IllegalValueException(ExamList.MESSAGE_CONSTRAINTS);
        }
        final Exam modelExam = ExamList.getExamFromName(examName);

        if (this.score.equals("unrecorded")) {
            return Score.getUnrecordedScore(modelExam);
        }

        if (!modelExam.isValidScore(score)) {
            throw new IllegalValueException(modelExam.getMessageScoreConstraints());
        }

        return Score.getRecordedScore(modelExam, score);
    }
}

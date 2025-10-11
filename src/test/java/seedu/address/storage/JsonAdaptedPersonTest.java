package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_STUDENT_ID = "A000A";
    private static final String INVALID_ATTENDANCE = "false";
    private static final JsonAdaptedScore INVALID_SCORE = new JsonAdaptedScore("olvl", "a+");
    private static final JsonAdaptedScore INVALID_SCORE2 = new JsonAdaptedScore("midterm", "700");
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TELEGRAM_HANDLE = "invalid";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().toJson();
    private static final JsonAdaptedScore VALID_SCORE = new JsonAdaptedScore(
            BENSON.getExamScores().getArrayOfScores()[0]);
    private static final JsonAdaptedExamScores VALID_EXAM_SCORES = new JsonAdaptedExamScores(BENSON.getExamScores());
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
        person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                 VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, INVALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, null, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidExamScores_throwsIllegalValueException() {
        List<JsonAdaptedScore> invalidScoreList;
        JsonAdaptedExamScores invalidExamScores;

        // throws error when the number of scores is wrong
        invalidScoreList = Arrays.asList(VALID_SCORE);
        invalidExamScores = new JsonAdaptedExamScores(invalidScoreList);
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, invalidExamScores, VALID_TAGS);
        String expectedMessage = JsonAdaptedExamScores.WRONG_NUMBER_OF_SCORES_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        // throws error when the name of exam is wrong
        invalidScoreList = Arrays.asList(VALID_SCORE, INVALID_SCORE);
        invalidExamScores = new JsonAdaptedExamScores(invalidScoreList);
        person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, invalidExamScores, VALID_TAGS);
        expectedMessage = Exam.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        // throws error when the value of score is wrong
        invalidScoreList = Arrays.asList(VALID_SCORE, INVALID_SCORE2);
        invalidExamScores = new JsonAdaptedExamScores(invalidScoreList);
        person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, invalidExamScores, VALID_TAGS);
        expectedMessage = Exam.MIDTERM.getMessageScoreConstraints();
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        // throws error when the order of scores is wrong
        invalidScoreList = Arrays.asList(VALID_SCORE, VALID_SCORE); // both midterm scores
        invalidExamScores = new JsonAdaptedExamScores(invalidScoreList);
        person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, invalidExamScores, VALID_TAGS);
        expectedMessage = JsonAdaptedExamScores.WRONG_ORDER_OF_SCORES_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullExamScores_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExamScores.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                null, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_STUDENT_ID,
                INVALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_EXAM_SCORES, VALID_TAGS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}

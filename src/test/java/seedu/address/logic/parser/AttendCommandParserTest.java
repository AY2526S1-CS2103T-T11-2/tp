package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AttendCommand.MESSAGE_WRONG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendCommand;

public class AttendCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE);

    private final AttendCommandParser parser = new AttendCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanThreeArgs_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " arg1" + " arg2" + " arg3" + " arg4",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validStudentId_success() {
        Identifier amyIdentifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        AttendCommand attendCommand = new AttendCommand(amyIdentifier, Index.fromOneBased(5));
        assertParseSuccess(parser, AttendCommand.COMMAND_WORD + " " + VALID_STUDENT_ID_AMY_STR + " 5",
                attendCommand);
    }

    @Test
    public void parse_maxInt_success() {
        Identifier amyIdentifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        AttendCommand attendCommand = new AttendCommand(amyIdentifier, Index.fromOneBased(Integer.MAX_VALUE));
        assertParseSuccess(parser, AttendCommand.COMMAND_WORD + " " + VALID_STUDENT_ID_AMY_STR + " "
                        + Integer.MAX_VALUE,
                attendCommand);
    }

    @Test
    public void parse_invalidStudentId_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " " + INVALID_STUDENT_ID_DESC + " 5",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " " + VALID_STUDENT_ID_AMY_STR + " 0",
                MESSAGE_WRONG_TUTORIAL);
    }

    @Test
    public void parse_negativeIndex_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " " + VALID_STUDENT_ID_AMY_STR + " -1",
                MESSAGE_WRONG_TUTORIAL);
    }

    @Test
    public void parse_aboveMaxInt_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " 1" + " 2147483648",
                MESSAGE_WRONG_TUTORIAL);
    }

    @Test
    public void parse_numberFormatException_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " 1" + " random words instead of number",
                MESSAGE_WRONG_TUTORIAL);
    }
}

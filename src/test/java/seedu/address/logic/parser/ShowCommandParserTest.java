package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class ShowCommandParserTest {
    private final ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_noPrefixesProvided_throwsParseException() {
        assertParseFailure(parser, "", "There should be one argument \n"
                + String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", "There should be one argument \n"
                + String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_negativeNumber_throwsParseException() {
        assertParseFailure(parser, "-3", "Invalid argument! Must be non-zero and positive "
                + "integer (should not contain characters like '+') \n"
                + String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_specialSymbol_throwsParseException() {
        assertParseFailure(parser, "%", "Invalid argument! Must be non-zero and positive "
                + "integer (should not contain characters like '+') \n"
                + String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGroupNumber_returnsShowCommand() {
        Predicate<Person> predicate = new GroupContainsKeywordsPredicate(Arrays.asList("1"));
        ShowCommand expectedShowCommand = new ShowCommand(predicate);

        assertParseSuccess(parser, "1", expectedShowCommand);
    }
}

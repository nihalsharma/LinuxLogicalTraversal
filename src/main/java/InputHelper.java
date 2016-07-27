import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * A class which helps in recognising the inputs and does some validations
 */
public class InputHelper {

    private static final String REGEX = "(\\/)|((\\/)([a-zA-Z0-9_!]+))+";

    private static Set<String> commandSet = new HashSet<String>();

    static {
        for (EnumCommand command : EnumCommand.values()) {
            commandSet.add(command.getName());
        }
    }

    /**
     * @param input the input line
     * @return a Command object which contains the parsed input command
     */
    public static Command validateCommand(String input) {

        input = input.trim();
        while (input.contains("  ")) {
            input = input.replace("  ", " ");
        }

        Command command = new Command();
        boolean validated = false;
        String parameter = "";
        String value = "";

        if (!(input == null || input.length() == 0)) {

            String tokens[] = input.trim().split(" ");
            if (tokens.length == 1) {

                validated = tokens[0].equals(EnumCommand.PWD.getName());
                value = EnumCommand.PWD.getName();
                parameter = "";

            } else if (tokens.length == 2) {

                if (input.equals(EnumCommand.CLEAR.getName())) {
                    validated = true;
                    value = EnumCommand.CLEAR.getName();
                } else {
                    validated = commandSet.contains(tokens[0]);
                    value = tokens[0];
                    validated = validated && validateDirectoryName(tokens[1]);
                    parameter = tokens[1];
                }

            } else {
                validated = false;
            }

        }
        command.setCommand(value);
        command.setParameter(parameter);
        command.setValidate(validated);
        return command;
    }

    public static boolean validateDirectoryName(String token) {
        return Pattern.matches(REGEX, token);
    }


    public static Set<String> getCommandSet() {
        return commandSet;
    }

    /**
     * a helper class to contain the parsed input command
     */
    static class Command {
        boolean validate;
        String command;
        String parameter;

        public boolean isValidate() {
            return validate;
        }

        public void setValidate(boolean validate) {
            this.validate = validate;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }
    }
}

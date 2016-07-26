import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A class which helps in recognising the inputs and does some validations
 */
public class InputHelper {


    private static Set<String> commandSet = new HashSet<String>();
    private static Map<String, String> optionSet = new HashMap<String, String>();

    static {
        for (EnumCommand command : EnumCommand.values()) {
            commandSet.add(command.getName());
        }

        optionSet.put(EnumCommand.MKDIR.getName(), "p");
        optionSet.put(EnumCommand.RM.getName(), "r");
    }

    private boolean validateCommand(String command) {
        if (!(command == null || command.length() == 0)) {
            String tokens[] = command.trim().split(" ");
            // here we are defining the validity on the basis of the length of the tokens
            if (tokens.length == 1) {
                return tokens[0].equals(EnumCommand.PWD.getName());
            } else if (tokens.length == 2) {
                
            } else if (tokens.length == 3) {

            } else {

            }


        }
        return false;
    }


    public static Set<String> getCommandSet() {
        return commandSet;
    }

    public static Map<String, String> getOptionSet() {
        return optionSet;
    }
}

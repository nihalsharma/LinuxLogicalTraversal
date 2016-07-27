import java.util.HashMap;
import java.util.Map;

/**
 * Enum class representing the commands
 */
public enum EnumCommand {

    CD("cd"),
    MKDIR("mkdir"),
    PWD("pwd"),
    RM("rm"),
    CLEAR("session clear");


    private String com;

    EnumCommand(String com) {
        this.com = com;
    }

    private static final Map<String, EnumCommand> commandMap = new HashMap<String, EnumCommand>();

    static {
        for (EnumCommand command : values()) {
            commandMap.put(command.getName(), command);
        }
    }

    public static EnumCommand getEnumByValue(String value) {
        return commandMap.get(value);
    }

    public String getName() {
        return com;
    }
}

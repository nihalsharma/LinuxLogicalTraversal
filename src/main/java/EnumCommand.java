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

    public String getName() {
        return com;
    }
}

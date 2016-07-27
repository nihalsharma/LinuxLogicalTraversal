/**
 * Gives the formatted result as desired
 */
public class ResultBuilder {

    /**
     * get the well formed output string for the different operations of
     * the command execution
     *
     * @param commandName name of the command
     * @param result      a boolean stating if the operation was successful
     * @return well formed output string
     */
    public static String getResultString(String commandName, boolean result) {
        EnumCommand command = EnumCommand.getEnumByValue(commandName);
        StringBuilder sb = new StringBuilder();

        switch (command) {
            case CD:
                sb = result ? sb.append("SUCC : REACHED") : sb.append("ERR : INVALID PATH");
                break;
            case MKDIR:
                sb = result ? sb.append("SUCC : CREATED") : sb.append("ERR : CANNOT CREATE");
                break;
            case RM:
                sb = result ? sb.append("SUCC : DELETED") : sb.append("ERR : CANNOT DELETE");
                break;
            case CLEAR:
                sb.append("SUCC: CLEARED: RESET TO ROOT");
                break;
            default:
                sb.append("ERR: CANNOT RECOGNIZE INPUT");
                break;

        }
        return String.valueOf(sb);
    }
}

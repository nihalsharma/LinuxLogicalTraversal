/**
 * This class helps with executing the commands
 */
public class CommandExecutor {

    DirectoryTree dTree;

    public CommandExecutor(DirectoryTree dTree) {
        this.dTree = dTree;
    }

    /**
     * Runs a command
     *
     * @param commandName name of the command to be executed
     * @param parameter   parameter which is given with the command
     * @return the desired output
     */
    public String runCommand(String commandName, String parameter) {

        String result;
        if (commandName.equals(EnumCommand.PWD.getName())) {

            String path = dTree.getCurrentDirectoryPath();
            result = "PATH: " + path;

        } else if (commandName.equals(EnumCommand.CD.getName())) {

            boolean canTraverse;
            if (parameter.startsWith("/")) {
                canTraverse = dTree.traverse(parameter);
            } else {
                canTraverse = dTree.traverseRelative(parameter);
            }
            result = ResultBuilder.getResultString(commandName, canTraverse);

        } else if (commandName.equals(EnumCommand.MKDIR.getName())) {

            boolean childAdded;

            if (parameter.startsWith("/")) {
                childAdded = dTree.addChild(parameter);
            } else {
                childAdded = dTree.addChildRelativeDirectory(parameter);
            }
            result = ResultBuilder.getResultString(commandName, childAdded);

        } else if (commandName.equals(EnumCommand.RM.getName())) {

            boolean removed;
            if (parameter.startsWith("/")) {
                removed = dTree.deleteDirectory(parameter);
            } else {
                removed = dTree.deleteDirectoryRelative(parameter);
            }
            result = ResultBuilder.getResultString(commandName, removed);

        } else if (commandName.contains(EnumCommand.CLEAR.getName())) {

            dTree.clear();
            result = ResultBuilder.getResultString(commandName, true);

        } else {

            result = ResultBuilder.getResultString(null, true);

        }

        return result;
    }



    //########################### getters and setters #############################

    public DirectoryTree getDTree() {
        return dTree;
    }

    public void setDTree(DirectoryTree dTree) {
        this.dTree = dTree;
    }
}

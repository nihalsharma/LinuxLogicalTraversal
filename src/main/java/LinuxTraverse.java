import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The main class for the project
 */
public class LinuxTraverse {


    public static void main(String[] args) {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        parser.parse();
        DirectoryTree directoryTree = new DirectoryTree();
        CommandExecutor executor = new CommandExecutor(directoryTree);

        if (parser.getFilename() == null) {
            processStandardInput(executor);
        } else {
            processFileInput(executor, parser.getFilename());
        }
    }

    /**
     * this method is used when the input is provided through file.
     * a sample file has been put in the project to take a look at the sample input lines
     *
     * @param executor a CommandExecutor instance
     * @param filename name of the input file
     */
    private static void processFileInput(CommandExecutor executor, String filename) {
        // to be read by a file of given file name
        File file = new File(filename);
        try {
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String input;

            // read the file and put all the lines in a container and execute them
            while ((input = br.readLine()) != null) {
                InputHelper.Command command = InputHelper.validateCommand(input);
                System.out.println(input);
                if (!command.isValidate()) {
                    System.out.println("ERR: CANNOT RECOGNIZE INPUT");
                } else {
                    String result = executor.runCommand(command.getCommand(), command.getParameter());
                    System.out.println(result);
                }
                Thread.sleep(1000); // sleeping for reading the next input with some delay with some delay
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong while loading the file");
        } catch (IOException e) {
            System.out.println("Could not read the file.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * To be used in case when the input is fed through the standard input
     *
     * @param executor a CommandExecutor instance
     */
    private static void processStandardInput(CommandExecutor executor) {
        System.out.println("Please give the inputs one by one and wait for the output. If you want to quit, press q");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            while (!(input = br.readLine()).equals("q")) {

                InputHelper.Command command = InputHelper.validateCommand(input);
                if (!command.isValidate())
                    System.out.println("ERR: CANNOT RECOGNIZE INPUT");
                else {
                    String result = executor.runCommand(command.getCommand(), command.getParameter());
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading the standard console");
            e.printStackTrace();
        }

    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class for the project
 */
public class LinuxTraverse {

    public static void main(String[] args) {
        CliParser parser = new CliParser(args);
        if (parser.getFilename() == null) {
            // to be read by stdio
            System.out.println("Please give the inputs one by one and wait for the output. If you want to quit, press q");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                while(!(input = br.readLine()).equals("q")){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // to be read by a file of given file name
            File file = new File(parser.getFilename());
            // read the file and put all the lines in a container and execute them

        }
    }
}

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to parse Command Line Options
 */
public class CliParser {

    private static final Logger log = Logger.getLogger(CliParser.class.getName());
    private String[] args = null;
    private Options options = new Options();
    private String filename = null;


    public CliParser(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("f", "file", false, "Provide the file name for this option.");
    }

    /**
     * Parse the command line options
     */
    public void parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("f")) {
                this.filename = cmd.getOptionValue("f");
            } else {
                log.log(Level.SEVERE, "Missing file name");
                help();
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse command line properties", e);
            help();
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);
        System.exit(0);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to parse Command Line Options
 */
public class CommandLineArgumentParser {

    private static final Logger log = Logger.getLogger(CommandLineArgumentParser.class.getName());
    private String[] args = null;
    private Options options = new Options();
    private String filename = null;


    public CommandLineArgumentParser(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        Option optionalArgument = new Option("f", "Provide the file name for this option.");
        optionalArgument.setOptionalArg(true);
        optionalArgument.setArgs(1);
        options.addOption(optionalArgument);
    }

    /**
     * Parse the command line options, if any
     */
    public void parse() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("f")) {
                if (!(cmd.getOptionValue("f") == null || cmd.getOptionValue("f").length() == 0))
                    this.filename = cmd.getOptionValue("f");
                else {
                    log.log(Level.SEVERE, "Please give a valid file name");
                    help();
                }
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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class OptionSetter {
    String[] arguments;
    public static Options defineOptions(){
        final Option fileOption = Option.builder("f")
                .required()
                .hasArg()
                .longOpt("file")
                .desc("File containing data to be classified.")
                .build();
        final Options options = new Options();
        options.addOption(fileOption);
        return options;
    }

    public OptionSetter(String[] args) {
        this.arguments = args;
    }

    public String parseOptions(final Options options){
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;
        try{
            commandLine = parser.parse(options, arguments);
        } catch (ParseException error) {
            System.out.println("ERROR: Please check command-line arguments!\n" + error);
        }
        if (commandLine != null) {
            return commandLine.getOptionValue("file");
        } else{
            throw new IllegalArgumentException("ERROR: Missing command-line arguments!");
        }
    }

}
import controllers.FileAggregator;
import controllers.FileFormatter;

import java.io.File;

public class PILogCLI {

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "filter" -> {
                    FileFormatter fileFormatter = new FileFormatter(new File(args[1]));
                    if (args.length > 2)
                        fileFormatter.outputFile(new File(args[2]));
                    fileFormatter.removeFileNoise();
                }
                case "group" -> {
                    FileAggregator fileAggregator = new FileAggregator(new File(args[1]));
                    if (args.length > 2)
                        fileAggregator.outputFile(new File(args[2]));
                    fileAggregator.groupByWordDistance();
                }
                default -> System.out.println("Required command line arguments <function: filter|group> <inputFilePath> <outputFilePath>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

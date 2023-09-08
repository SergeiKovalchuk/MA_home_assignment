package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileFormatter extends FileHandler {
    private static final String DEFAULT_PATTERN = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(\\d{4}) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]) .+";
    protected static final Logger logger = LogManager.getLogger(FileFormatter.class);

    public FileFormatter(File inputFile) {
        super(inputFile);
    }

    public void removeFileNoise() throws IOException {
        removeFileNoise(FileFormatter.DEFAULT_PATTERN);
    }

    public void removeFileNoise(String pattern) throws IOException {
        this.outputWriter = new BufferedWriter(new FileWriter(inputFile.getAbsolutePath() + "_temp"));
        logger.info("Starting remove file noise with pattern: " + pattern);
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.matches(pattern)) {
                    outputWriter.write(line);
                    outputWriter.newLine();
                }
            }
            outputWriter.close();
            scanner.close();

            logger.debug("replacing input file with temporary file");
            outputFile = new File(inputFile.getAbsolutePath() + "_temp");
            inputFile.delete();
            outputFile.renameTo(inputFile);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("Finished remove file noise");
    }
}

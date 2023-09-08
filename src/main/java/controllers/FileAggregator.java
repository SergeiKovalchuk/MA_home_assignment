package controllers;

import core.LogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FileAggregator extends FileHandler{
    protected static final Logger logger = LogManager.getLogger(FileAggregator.class);

    public FileAggregator(File inputFile) {
        super(inputFile);
    }

    public FileAggregator outputFile(File outputFile) throws IOException {
        this.outputFile = outputFile;
        this.outputWriter = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));
        return this;
    }

    public void groupByWordDistance() {
        try {
            logger.info("Starting group by word distance");
            Scanner scanner = new Scanner(inputFile);
            HashSet<String> handledPatterns = new HashSet<>();
            if (outputFile == null)
                this.outputWriter = new BufferedWriter(new FileWriter(inputFile.getAbsolutePath() + "_grouped"));
            while (scanner.hasNextLine()) {
                LogEntry entry = new LogEntry(scanner.nextLine());
                if (handledPatterns.contains(entry.getEntrySentence())) continue;

                for (int i = 0; i < entry.getEntryWords().length; i++) {
                    String groupByWord = entry.buildSentenceTokenExcludingWord(i);
                    if (handledPatterns.contains(groupByWord)) continue;

                    logger.debug("Starting to handle new unique sentence token " + groupByWord);

                    Scanner matchingScanner = new Scanner(inputFile);
                    HashSet<String> changingWords = new HashSet<>();
                    ArrayList<LogEntry> groupedEntries = new ArrayList<>();

                    while (matchingScanner.hasNextLine()) {
                        LogEntry comparedEntry = new LogEntry(matchingScanner.nextLine());
                        int wordDistance = entry.wordDistance(comparedEntry);

                        if ((wordDistance == 0) || (wordDistance == 1 && !entry.getEntryWord(i).equals(comparedEntry.getEntryWord(i)))) {
                            if (wordDistance == 1)
                                logger.debug("Found entry to group with word distance " + wordDistance + " word " + comparedEntry.getEntryWord(i));
                            groupedEntries.add(comparedEntry);
                            changingWords.add(comparedEntry.getEntryWord(i));
                        }

                    }
                    if (changingWords.size() > 1) {
                        for (int j = 0; j < groupedEntries.size(); j++) {
                            outputWriter.write(groupedEntries.get(j).toString() + "\n");
                        }
                        logger.trace("Multiple changing words found, outputting to: " + groupedEntries);
                        logger.debug("Outputted changing words: " + changingWords);

                        outputWriter.write("The Changing word was: " + changingWords + "\n\n");
                    }

                    handledPatterns.add(groupByWord);
                    outputWriter.flush();
                    matchingScanner.close();
                }
                logger.debug("Adding handled pattern to skip on next iterations: " + entry.getEntrySentence());
                handledPatterns.add(entry.getEntrySentence());
            }
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Finished group by word distance");
    }
}

package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;

public class FileHandler {
    protected static final Logger logger = LogManager.getLogger(FileAggregator.class);
    protected final File inputFile;
    protected File outputFile;
    protected BufferedWriter outputWriter;

    public FileHandler(File inputFile) {
        super();
        this.inputFile = inputFile;
    }
}

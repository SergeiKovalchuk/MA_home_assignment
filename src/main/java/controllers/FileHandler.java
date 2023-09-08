package controllers;

import java.io.BufferedWriter;
import java.io.File;

public class FileHandler {
    protected final File inputFile;
    protected File outputFile;
    protected BufferedWriter outputWriter;

    public FileHandler(File inputFile) {
        super();
        this.inputFile = inputFile;
    }
}

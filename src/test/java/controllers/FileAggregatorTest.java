package controllers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileAggregatorTest {
    @TempDir
    Path tempDir;

    Path tempFilePath;
    File tempFile;

    @BeforeEach
    public void setUp() {
        tempFilePath = tempDir.resolve("temp_file");
        tempFile = tempFilePath.toFile();
    }

    @Test
    void groupByWordDistance() throws Exception{
        File inputFile = new File("src/test/resources/file_without_noise");
        FileAggregator fileAggregator = new FileAggregator(inputFile).outputFile(tempFile);
        File expectedOutput = new File("src/test/resources/file_grouped");

        fileAggregator.groupByWordDistance();

        boolean filesEqual = FileUtils.contentEqualsIgnoreEOL(tempFile,expectedOutput,null);
        assertEquals(true,filesEqual);
    }
}
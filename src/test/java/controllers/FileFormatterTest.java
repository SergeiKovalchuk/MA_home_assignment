package controllers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileFormatterTest {
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
    void RemoveFileNoise() throws Exception{
        File inputFile = new File("src/test/resources/file_with_noise");
        FileFormatter fileFormatter = new FileFormatter(inputFile);
        File expectedOutput = new File("src/test/resources/file_without_noise");

        fileFormatter.removeFileNoise();

        boolean filesEqual = FileUtils.contentEqualsIgnoreEOL(tempFile,expectedOutput,null);
        assertEquals(true,filesEqual);
    }
}
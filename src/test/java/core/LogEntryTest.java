package core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {

    private static LogEntry logEntry;
    @BeforeAll
    public static void setUp() {
        logEntry = new LogEntry("03-01-2012 10:14:00 Sergei is testing a class");
    }

    @Test
    void getEntryWords() {
        assertArrayEquals(new String[]{"Sergei", "is", "testing", "a", "class"},logEntry.getEntryWords());
    }

    @Test
    void getEntryWord() {
        assertEquals("Sergei",logEntry.getEntryWord(0));
    }

    @Test
    void getEntrySentence() {
        assertEquals("Sergei is testing a class",logEntry.getEntrySentence());
    }

    @Test
    void buildSentenceTokenExcludingWorld() {
        assertEquals(" istestingaclass",logEntry.buildSentenceTokenExcludingWord(0));
        assertEquals("Sergeiis aclass",logEntry.buildSentenceTokenExcludingWord(2));
    }

    @Test
    void wordDistance() {
        LogEntry comparedEntryDistance2 = new LogEntry("05-05-2015 10:14:00 Sergei is building a car");
        LogEntry comparedEntryDistance1 = new LogEntry("05-05-2015 10:14:00 Sergei is building a class");
        assertEquals(2,logEntry.wordDistance(comparedEntryDistance2));
        assertEquals(1,logEntry.wordDistance(comparedEntryDistance1));
        assertEquals(0,logEntry.wordDistance(logEntry));
    }

    @Test
    void testToString() {
        assertEquals("03-01-2012 10:14:00 Sergei is testing a class",logEntry.toString());
    }
}
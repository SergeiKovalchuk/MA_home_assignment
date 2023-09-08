package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LogEntry {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final LocalDateTime dateTime;
    private final String[] entryWords;

    public LogEntry(String logEntry) {
        super();
        String[] entry = logEntry.split("\\s+");
        this.dateTime = LocalDateTime.parse(entry[0] + " " + entry[1], LogEntry.DATE_TIME_FORMATTER);
        this.entryWords = Arrays.copyOfRange(entry, 2, entry.length);
    }

    public String[] getEntryWords() {
        return entryWords;
    }

    public String getEntryWord(int index) {
        return entryWords[index];
    }

    public String getEntrySentence() {
        return String.join(" ", entryWords);
    }

    public String buildSentenceTokenExcludingWord(int excludedWordIndex) {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < entryWords.length; i++) {
            if (i != excludedWordIndex) {
                sentence.append(entryWords[i]);
            } else {
                sentence.append(" ");
            }
        }
        return sentence.toString();
    }

    public int wordDistance(LogEntry comparedLoggedEntry) {
        int differenceCounter = 0;
        String[] comparedWords = comparedLoggedEntry.getEntryWords();

        int maxLength = Math.max(comparedWords.length, entryWords.length);
        int minLength = Math.min(comparedWords.length, entryWords.length);

        for (int i = 0; i < minLength; i++) {
            if (!entryWords[i].equals(comparedWords[i])) differenceCounter++;
        }

        return differenceCounter + (maxLength - minLength);
    }

    public String toString() {
        return dateTime.format(DATE_TIME_FORMATTER) + " " + getEntrySentence();
    }
}

import org.dawone.textreporter.FactOfTheDayLineReader;
import org.dawone.textreporter.TextReporter;
import org.dawone.textreporter.WordProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
class TextReporterTest {

    @Test
    void reportIsWordUsed_InsertNullParameter_throwsException() {

        FactOfTheDayLineReader readerMock= Mockito.mock(FactOfTheDayLineReader.class);
        WordProcessor wordProcessorMock= Mockito.mock(WordProcessor.class);

        TextReporter textReporter = new TextReporter(readerMock, wordProcessorMock);



        assertThrows(IllegalArgumentException.class, ()->{textReporter.reportIsWordUsed(null);});

    }

    @Test
    void reportIsWordUsed_InsertEmptyParameter_throwsException() {

        FactOfTheDayLineReader readerMock= Mockito.mock(FactOfTheDayLineReader.class);
        WordProcessor wordProcessorMock= Mockito.mock(WordProcessor.class);

        TextReporter textReporter = new TextReporter(readerMock, wordProcessorMock);



        assertThrows(IllegalArgumentException.class, ()->{textReporter.reportIsWordUsed("");});

    }

    @Test
    void reportIsWordUsed_WordExists_returnsTrue() {
        FactOfTheDayLineReader readerMock= Mockito.mock(FactOfTheDayLineReader.class);
        String[] fact={"Hello WORLD"};
        when(readerMock.readLines()).thenReturn(fact);

        WordProcessor wordProcessor= new WordProcessor();
        TextReporter textReporter = new TextReporter(readerMock, wordProcessor);

        boolean actual= textReporter.reportIsWordUsed("Hello");
        assertTrue(actual);
    }

    @Test
    void reportIsWordUsed_WordDoesntExists_returnsFalse() {
        FactOfTheDayLineReader readerMock= Mockito.mock(FactOfTheDayLineReader.class);
        String[] fact={"It's up to you"};
        when(readerMock.readLines()).thenReturn(fact);

        WordProcessor wordProcessor= new WordProcessor();
        TextReporter textReporter = new TextReporter(readerMock, wordProcessor);

        boolean actual= textReporter.reportIsWordUsed("Hello");
        assertFalse(actual);
    }

}
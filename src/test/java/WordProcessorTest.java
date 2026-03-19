import org.dawone.textreporter.WordProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordProcessorTest {

    @Test
    void getWords_parameterIsNull_throwsException() {

        WordProcessor wordProcessor = new WordProcessor();
        assertThrows(IllegalArgumentException.class, () -> wordProcessor.getWords(null));
    }

    @Test
    void getWords_Insert2Words_returnSeparatedWords() {
        WordProcessor wordProcessor = new WordProcessor();

        String input = "Two words";

        String[] expected=new String[] {"two","words"};

        String[] actual= wordProcessor.getWords(input);

        assertArrayEquals(expected, actual);

    }


    @Test
    void getWords_InsertWordsWithDifferentPunctuation_returnSeparateWords() {
        WordProcessor wordProcessor = new WordProcessor();
        // Le pasamos un texto con comas, puntos, exclamaciones y apóstrofes
        String input = "Hello, world! It's a test.";

        // Esperamos que quite los signos, lo pase a minúsculas y no deje huecos vacíos
        String[] expected = new String[] {"hello", "world", "it", "s", "a", "test"};

        String[] actual = wordProcessor.getWords(input);

        assertArrayEquals(expected, actual);
    }





}
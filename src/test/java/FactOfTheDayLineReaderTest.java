import org.dawone.textreporter.FactOfTheDayLineReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactOfTheDayLineReaderTest {

    @Test
    void readLines_validEndpoint_returnsArrayWithOneFact() {

        String endpoint = "https://uselessfacts.jsph.pl//api/v2/facts/today";
        FactOfTheDayLineReader reader = new FactOfTheDayLineReader(endpoint);

        String[] actual = reader.readLines();

        assertNotNull(actual, "El array no debería ser nulo");
        assertEquals(1, actual.length, "Debería devolver exactamente 1 línea");
        assertFalse(actual[0].isEmpty(), "La línea no debería estar vacía");
    }

    @Test
    void readLines_invalidEndpoint_throwsException() {

        String apiEndpoint = "http://url-falsa-que-no-existe.com";
        FactOfTheDayLineReader reader = new FactOfTheDayLineReader(apiEndpoint);


        assertThrows(RuntimeException.class, () -> reader.readLines());
    }
}
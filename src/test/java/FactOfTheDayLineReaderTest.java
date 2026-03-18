import org.dawone.textreporter.FactOfTheDayLineReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactOfTheDayLineReaderTest {


    /*
     * NOTA SOBRE EL USO DE MOCKITO EN ESTA CLASE:
     * * En este test no se ha utilizado Mockito para simular (mockear) la conexión HTTP
     * porque la clase FactOfTheDayLineReader instancia su propio HttpClient directamente
     * dentro de su constructor: this.httpClient = HttpClient.newBuilder().build();
     *
     * Al tener la restricción de NO poder modificar el código original de la clase
     * (por ejemplo, no podemos crear un constructor nuevo para inyectar la dependencia),
     * no tenemos una forma directa de introducir un "Mock" de HttpClient desde este test
     * utilizando las herramientas básicas de Mockito.
     *
     * Por este motivo, el test se ha planteado como un Test de Integración: se conecta
     * a la API real y verifica que la estructura de la respuesta es correcta (longitud,
     * que no sea nula, etc.), ya que el contenido exacto de la frase cambia cada día.
     */

    @Test
    void readLines_validEndpoint_returnsArrayWithOneFact() {
        // 1. PREPARACIÓN
        // Usamos la URL real de la API
        String endpoint = "https://uselessfacts.jsph.pl//api/v2/facts/today";
        FactOfTheDayLineReader reader = new FactOfTheDayLineReader(endpoint);

        // 2. EJECUCIÓN
        // Llamamos al método (esto se conectará a Internet de verdad)
        String[] actual = reader.readLines();

        // 3. COMPROBACIÓN
        // Como no sabemos la frase exacta de hoy, comprobamos que cumpla las reglas:
        assertNotNull(actual, "El array no debería ser nulo");
        assertEquals(1, actual.length, "Debería devolver exactamente 1 línea");
        assertFalse(actual[0].isEmpty(), "La línea no debería estar vacía");
    }

    @Test
    void readLines_invalidEndpoint_throwsException() {
        // Le pasamos una URL rota o vacía para que falle a propósito
        String apiEndpoint = "http://url-falsa-que-no-existe.com";
        FactOfTheDayLineReader reader = new FactOfTheDayLineReader(apiEndpoint);

        // Comprobamos que, al fallar la conexión, lanza la excepción esperada
        assertThrows(RuntimeException.class, () -> reader.readLines());
    }
}
package org.Datos;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase encargada de la gestión de archivos del programa.
 *
 * <p>Se ocupa de la lectura del fichero JSON que contiene los datos
 * de los Pokémon y de la escritura del informe estadístico en un
 * fichero de texto.</p>
 *
 * <p>Utiliza la librería Jackson
 * ({@code tools.jackson.databind.ObjectMapper}) para la
 * deserialización del JSON a objetos de tipo {@link Pokemon}.</p>
 *
 * @author Camila Bustelo
 * @version 1.0
 * @see Pokemon
 * @see org.Negocio.Estadisticas
 */
public class GestorArchivos {

    /**
     * Objeto {@code ObjectMapper} de Jackson utilizado para
     * convertir el contenido del fichero JSON en una lista
     * de objetos {@link Pokemon}.
     */
    private ObjectMapper mapper;

    /**
     * Constructor que inicializa el gestor de archivos.
     *
     * <p>Crea una instancia de {@link ObjectMapper} que se
     * utilizará para la lectura y deserialización del JSON.</p>
     */
    public GestorArchivos() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Lee el fichero JSON con los datos de los Pokémon y lo convierte
     * en una lista de objetos {@link Pokemon}.
     *
     * <p>El fichero debe contener un array JSON de objetos, por ejemplo:</p>
     * <pre>
     * [
     *   {"id":1, "nombre":"Bulbasaur", "tipo":"PLANTA", "nivel":12, "ps":45},
     *   {"id":2, "nombre":"Ivysaur", "tipo":"PLANTA", "nivel":18, "ps":60}
     * ]
     * </pre>
     *
     * @param path ruta del fichero JSON a leer (por ejemplo,
     *             {@code "pokemon.json"})
     * @return lista de objetos {@link Pokemon} con todos los Pokémon
     *         leídos del fichero
     * @throws RuntimeException si el fichero no existe, no se puede
     *         leer o el contenido JSON no es válido
     */
    public List<Pokemon> listarPokemon(String path) {


            return mapper.readValue(
                    new File(path),
                    new TypeReference<List<Pokemon>>() {
                    }
            );
    }

    /**
     * Exporta el informe de texto a un fichero {@code .txt}.
     *
     * <p>Se encarga de escribir el contenido del informe en el
     * fichero indicado, sobrescribiendo su contenido si ya existía.
     * El fichero se cierra automáticamente gracias al bloque
     * {@code try-with-resources}.</p>
     *
     * @param path    ruta del fichero de destino (por ejemplo,
     *                {@code "informe_pokemon.txt"})
     * @param informe cadena de texto con el contenido del informe
     *                que se desea exportar
     * @throws RuntimeException si se produce un error de entrada/salida
     *         al escribir en el fichero
     */
    public void exportarTxt(String path, String informe) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(path))) {

            bw.write(informe);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

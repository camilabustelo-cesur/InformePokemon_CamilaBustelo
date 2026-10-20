package org;

import org.Datos.GestorArchivos;
import org.Datos.Pokemon;
import org.Negocio.Estadisticas;

import java.util.ArrayList;

/**
 * Clase principal del programa (punto de entrada).
 *
 * <p>Esta clase contiene el método {@link #main(String[])} que da
 * inicio a la aplicación. El flujo de ejecución es el siguiente:</p>
 *
 * <ol>
 *   <li>Leer el fichero {@code pokemon.json} y cargar los datos
 *       en una lista de objetos {@link Pokemon}.</li>
 *   <li>Calcular las estadísticas y generar el informe de texto
 *       con la clase {@link Estadisticas}.</li>
 *   <li>Mostrar el informe por pantalla.</li>
 *   <li>Exportar el informe al fichero {@code informe_pokemon.txt}
 *       mediante la clase {@link GestorArchivos}.</li>
 * </ol>
 *
 * @author Camila Bustelo
 * @version 1.0
 * @see GestorArchivos
 * @see Estadisticas
 * @see Pokemon
 */
public class Main {

    /**
     * Método principal de la aplicación.
     *
     * <p>Coordina toda la ejecución del programa: primero carga los
     * Pokémon desde el JSON, después genera el informe estadístico,
     * lo muestra por consola y finalmente lo exporta a un fichero
     * de texto.</p>
     *
     * @param args argumentos de línea de comandos (no se utilizan
     *             en esta aplicación)
     */
    public static void main(String[] args) {

        GestorArchivos gestor = new GestorArchivos();
        Estadisticas estadisticas = new Estadisticas();

        ArrayList<Pokemon> pokemons =
                gestor.listarPokemon("pokemon.json");

        String informe =
                estadisticas.generarInforme(pokemons);

        System.out.println(informe);

        gestor.exportarTxt(
                "informe_pokemon.txt",
                informe
        );
    }
}

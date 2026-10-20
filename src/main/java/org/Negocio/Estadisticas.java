package org.Negocio;

import org.Datos.Pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase encargada de realizar los cálculos estadísticos sobre
 * la lista de Pokémon.
 *
 * <p>Proporciona métodos para contar el total de Pokémon, calcular
 * medias de nivel y PS, obtener el Pokémon con mayor nivel o más PS,
 * listar los tipos distintos y generar un informe de texto completo
 * con todos los datos estadísticos.</p>
 *
 * <p>Todos los métodos trabajan sobre una {@link List} de
 * {@link Pokemon} y aprovechan el API de <em>Streams</em> de Java
 * para los cálculos.</p>
 *
 * @author Camila Bustelo
 * @version 1.0
 * @see Pokemon
 * @see org.Datos.GestorArchivos
 */
public class Estadisticas {

    /**
     * Cuenta el número total de Pokémon de la lista.
     *
     * @param pokemons lista de Pokémon a contar
     * @return número total de elementos de la lista
     */
    public int contarPokemon(List<Pokemon> pokemons) {
        return pokemons.size();
    }

    /**
     * Calcula el nivel medio de todos los Pokémon de la lista.
     *
     * <p>Obtiene la media aritmética del atributo {@code nivel}
     * de todos los Pokémon mediante el API de Streams. Si la lista
     * está vacía, devuelve {@code 0}.</p>
     *
     * @param pokemons lista de Pokémon sobre la que se calcula la media
     * @return media aritmética de los niveles, o {@code 0} si la
     *         lista está vacía
     */
    public double getNivelMedio(List<Pokemon> pokemons) {

        return pokemons.stream()
                .mapToDouble(Pokemon::getNivel)
                .average()
                .orElse(0);
    }

    /**
     * Calcula la media de los puntos de salud (PS) de todos
     * los Pokémon de la lista.
     *
     * <p>Obtiene la media aritmética del atributo {@code ps}
     * de todos los Pokémon mediante el API de Streams. Si la lista
     * está vacía, devuelve {@code 0}.</p>
     *
     * @param pokemons lista de Pokémon sobre la que se calcula la media
     * @return media aritmética de los PS, o {@code 0} si la
     *         lista está vacía
     */
    public double getPSMedio(List<Pokemon> pokemons) {

        return pokemons.stream()
                .mapToInt(Pokemon::getPs)
                .average()
                .orElse(0);
    }

    /**
     * Obtiene el Pokémon con el nivel más alto de la lista.
     *
     * <p>Si varios Pokémon empatan en el nivel máximo, se devuelve
     * el primero que se encuentre en la lista. Si la lista está
     * vacía, devuelve {@code null}.</p>
     *
     * @param pokemons lista de Pokémon entre los que se busca
     * @return el Pokémon con el nivel más alto, o {@code null} si
     *         la lista está vacía
     */
    public Pokemon getMaxNivel(List<Pokemon> pokemons) {

        return pokemons.stream()
                .max(Comparator.comparingDouble(Pokemon::getNivel))
                .orElse(null);
    }

    /**
     * Obtiene el Pokémon con más puntos de salud (PS) de la lista.
     *
     * <p>Si varios Pokémon empatan en los PS máximos, se devuelve
     * el primero que se encuentre en la lista. Si la lista está
     * vacía, devuelve {@code null}.</p>
     *
     * @param pokemons lista de Pokémon entre los que se busca
     * @return el Pokémon con más PS, o {@code null} si la lista
     *         está vacía
     */
    public Pokemon getMaxPS(List<Pokemon> pokemons) {

        return pokemons.stream()
                .max(Comparator.comparingInt(Pokemon::getPs))
                .orElse(null);
    }

    /**
     * Obtiene el conjunto de tipos distintos presentes en la lista.
     *
     * <p>Extrae el atributo {@code tipo} de cada Pokémon y lo
     * acumula en un {@link Set}, de forma que los tipos repetidos
     * solo aparecen una vez.</p>
     *
     * @param pokemons lista de Pokémon de la que se extraen los tipos
     * @return conjunto ({@link Set}) con los tipos distintos,
     *         sin repetidos
     */
    public Set<String> getTipos(List<Pokemon> pokemons) {

        return pokemons.stream()
                .map(Pokemon::getTipo)
                .collect(Collectors.toSet());
    }

    /**
     * Genera un bloque de texto con los nombres de los Pokémon
     * de un tipo concreto, precedidos por el tipo y su cantidad.
     *
     * <p>La comparación del tipo se realiza sin distinguir entre
     * mayúsculas y minúsculas ({@code equalsIgnoreCase}). El tipo
     * se muestra siempre en mayúsculas.</p>
     *
     * <p>Ejemplo de salida para el tipo {@code PLANTA}:</p>
     * <pre>
     * PLANTA: 3
     * Bulbasaur
     * Ivysaur
     * Venusaur
     *
     * </pre>
     *
     * @param tipo     tipo de Pokémon a buscar (por ejemplo, {@code "PLANTA"})
     * @param pokemons lista completa de Pokémon
     * @return cadena de texto formateada con el tipo, la cantidad
     *         y los nombres de los Pokémon de ese tipo
     */
    public String getPokemonPorTipo(String tipo,
                                    List<Pokemon> pokemons) {

        List<Pokemon> lista = pokemons.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase(tipo))
                .toList();

        String nombres = lista.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.joining("\n"));

        return tipo.toUpperCase() +
                ": " +
                lista.size() +
                "\n" +
                nombres +
                "\n\n";
    }

    /**
     * Genera el informe estadístico completo en formato de texto.
     *
     * <p>El informe incluye las siguientes secciones:</p>
     * <ul>
     *   <li><strong>Total de Pokémon:</strong> cantidad de elementos de la lista.</li>
     *   <li><strong>Nivel medio:</strong> media aritmética de los niveles.</li>
     *   <li><strong>PS medios:</strong> media aritmética de los PS.</li>
     *   <li><strong>Pokémon con mayor nivel:</strong> nombre y nivel del de mayor nivel.</li>
     *   <li><strong>Pokémon con más PS:</strong> nombre y PS del que tiene más PS.</li>
     *   <li><strong>Pokémon por tipo:</strong> listado agrupado por tipo elemental.</li>
     * </ul>
     *
     * @param pokemons lista completa de Pokémon sobre la que se
     *                 calculan las estadísticas
     * @return cadena de texto con el informe estadístico completo
     * @throws NullPointerException si la lista está vacía, ya que
     *         los métodos {@link #getMaxNivel(List)} y
     *         {@link #getMaxPS(List)} devuelven {@code null}
     */
    public String generarInforme(List<Pokemon> pokemons) {

        Pokemon maxNivel = getMaxNivel(pokemons);
        Pokemon maxPS = getMaxPS(pokemons);

        String informe =
                "=========================\n" +
                        " INFORME DE POKÉMON\n" +
                        "=========================\n\n" +

                        "Total de Pokémon: "
                        + contarPokemon(pokemons) + "\n" +

                        "Nivel medio: "
                        + getNivelMedio(pokemons) + "\n" +

                        "PS medios: "
                        +getPSMedio(pokemons) + "\n\n" +

                        "Pokémon con mayor nivel:\n" +
                        maxNivel.getNombre() +
                        " (Nivel " +
                        maxNivel.getNivel() +
                        ")\n\n" +

                        "Pokémon con más PS:\n" +
                        maxPS.getNombre() +
                        " (PS " +
                        maxPS.getPs() +
                        ")\n\n" +

                        "-------------------------\n" +
                        "POKÉMON POR TIPO\n" +
                        "-------------------------\n\n";

        for (String tipo : getTipos(pokemons)) {

            informe += getPokemonPorTipo(
                    tipo,
                    pokemons
            );
        }

        return informe;
    }
}

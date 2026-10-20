package org.Datos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa a un Pokémon del informe.
 *
 * <p>Es el modelo de datos (POJO) que se mapea automáticamente
 * desde el fichero {@code pokemon.json} mediante la librería
 * Jackson. Los métodos de acceso {@code get...()} y {@code set...()}
 * están generados automáticamente por Lombok mediante las
 * anotaciones {@link lombok.Getter} y {@link lombok.Setter}.</p>
 *
 * <p>Ejemplo de JSON que representa a un Pokémon:</p>
 * <pre>
 * {"id":1, "nombre":"Bulbasaur", "tipo":"PLANTA", "nivel":12, "ps":45}
 * </pre>
 *
 * @author Camila Bustelo
 * @version 1.0
 * @see org.Datos.GestorArchivos#listarPokemon(String)
 */
@Getter
@Setter
public class Pokemon {

    /**
     * Identificador numérico único del Pokémon.
     * Corresponde al campo {@code "id"} del fichero JSON.
     */
    private int id;

    /**
     * Nombre del Pokémon.
     * Corresponde al campo {@code "nombre"} del fichero JSON.
     */
    private String nombre;

    /**
     * Tipo elemental del Pokémon (por ejemplo: {@code PLANTA},
     * {@code FUEGO}, {@code AGUA}, {@code BICHO}, etc.).
     * Corresponde al campo {@code "tipo"} del fichero JSON.
     */
    private String tipo;

    /**
     * Nivel actual del Pokémon.
     * Corresponde al campo {@code "nivel"} del fichero JSON.
     */
    private double nivel;

    /**
     * Puntos de salud (PS) del Pokémon.
     * Corresponde al campo {@code "ps"} del fichero JSON.
     */
    private int ps;

    /**
     * Constructor vacío.
     *
     * <p>Necesario para que la librería Jackson pueda
     * instanciar los objetos mediante reflexión durante
     * la deserialización del JSON.</p>
     */
    public Pokemon() {
    }

    /**
     * Constructor completo que inicializa todos los atributos del Pokémon.
     *
     * @param id      identificador numérico único del Pokémon
     * @param nombre  nombre del Pokémon
     * @param tipo    tipo elemental del Pokémon
     * @param nivel   nivel actual del Pokémon
     * @param ps      puntos de salud del Pokémon
     */
    public Pokemon(int id, String nombre, String tipo, int nivel, int ps) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.ps = ps;
    }
}

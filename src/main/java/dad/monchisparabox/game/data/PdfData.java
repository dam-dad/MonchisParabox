package dad.monchisparabox.game.data;

/**
 * Clase que representa los datos para generar un archivo PDF.
 */
public class PdfData {
    private String nombre;
    private int movimientos;
    private String tiempo;

    /**
     * Constructor de la clase PdfData.
     *
     * @param nombre      El nombre del jugador.
     * @param movimientos El número de movimientos realizados por el jugador.
     * @param tiempo      El tiempo transcurrido durante el juego.
     */
    public PdfData(String nombre, int movimientos, String tiempo) {
        this.nombre = nombre;
        this.movimientos = movimientos;
        this.tiempo = tiempo;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre El nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de movimientos realizados por el jugador.
     *
     * @return El número de movimientos realizados por el jugador.
     */
    public int getMovimientos() {
        return movimientos;
    }

    /**
     * Establece el número de movimientos realizados por el jugador.
     *
     * @param movimientos El número de movimientos realizados por el jugador.
     */
    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Obtiene el tiempo transcurrido durante el juego.
     *
     * @return El tiempo transcurrido durante el juego.
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * Establece el tiempo transcurrido durante el juego.
     *
     * @param tiempo El tiempo transcurrido durante el juego.
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}

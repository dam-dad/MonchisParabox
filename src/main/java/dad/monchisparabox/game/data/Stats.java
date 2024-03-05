package dad.monchisparabox.game.data;

/**
 * Clase que representa las estadísticas de un jugador en un mapa.
 */
public class Stats {

    /** El ID del mapa. */
    public int idMapa;
    
    /** El tiempo transcurrido durante la partida. */
    public String tiempo;
    
    /** El número de movimientos realizados durante la partida. */
    public int movimientos;

    /**
     * Constructor de la clase Stats.
     *
     * @param idMapa      El ID del mapa.
     * @param tiempo      El tiempo transcurrido durante la partida.
     * @param movimientos El número de movimientos realizados durante la partida.
     */
    public Stats(int idMapa, String tiempo, int movimientos) {
        this.idMapa = idMapa;
        this.tiempo = tiempo;
        this.movimientos = movimientos;
    }

    /**
     * Establece un nuevo tiempo y número de movimientos.
     *
     * @param nuevoTiempo      El nuevo tiempo transcurrido durante la partida.
     * @param nuevosMovimientos Los nuevos movimientos realizados durante la partida.
     */
    public void setTiempoMovimientos(String nuevoTiempo, int nuevosMovimientos) {
        this.tiempo = nuevoTiempo;
        this.movimientos = nuevosMovimientos;
    }

    /**
     * Obtiene el ID del mapa.
     *
     * @return El ID del mapa.
     */
    public int getIdMapa() {
        return idMapa;
    }

    /**
     * Obtiene el tiempo transcurrido durante la partida.
     *
     * @return El tiempo transcurrido durante la partida.
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * Obtiene el número de movimientos realizados durante la partida.
     *
     * @return El número de movimientos realizados durante la partida.
     */
    public int getMovimientos() {
        return movimientos;
    }
}

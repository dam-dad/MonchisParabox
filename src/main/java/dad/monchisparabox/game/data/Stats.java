package dad.monchisparabox.game.data;

public class Stats {

    public int idMapa;
    public String tiempo;
    public int movimientos;

    public Stats(int idMapa, String tiempo, int movimientos) {
        this.idMapa = idMapa;
        this.tiempo = tiempo;
        this.movimientos = movimientos;
    }

    public void setTiempoMovimientos(String nuevoTiempo, int nuevosMovimientos) {
        this.tiempo = nuevoTiempo;
        this.movimientos = nuevosMovimientos;
    }
}

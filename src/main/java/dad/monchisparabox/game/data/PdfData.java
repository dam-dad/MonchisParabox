package dad.monchisparabox.game.data;

public class PdfData {
    private String nombre;
    private int movimientos;
    private String tiempo;

    public PdfData(String nombre, int movimientos, String tiempo) {
        this.nombre = nombre;
        this.movimientos = movimientos;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}

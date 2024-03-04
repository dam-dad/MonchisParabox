package dad.monchisparabox.game.data;

import java.util.List;

public class MapData {
    private int id;
    private String nombre;
    private String mapa;
    private String cancion;

    // Constructor, getters y setters
    public MapData() {}

    public MapData(int id, String nombre, String mapa, String cancion) {
        this.id = id;
        this.nombre = nombre;
        this.mapa = mapa;
        this.cancion = cancion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }
}

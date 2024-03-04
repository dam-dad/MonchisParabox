package dad.monchisparabox.game.data;

public class PdfData {
    private String mapName;
    private int movements;
    private String time;

    public PdfData(String mapName, int movements, String time) {
        this.mapName = mapName;
        this.movements = movements;
        this.time = time;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int movements) {
        this.movements = movements;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

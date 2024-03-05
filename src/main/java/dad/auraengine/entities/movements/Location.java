package dad.auraengine.entities.movements;

import dad.monchisparabox.game.GameMap;

/**
 * Clase que representa la ubicación de una entidad en el mapa del juego.
 */
public class Location {
    
    private GameMap map;
    private int X;
    private int Y;
    private int lastX;
    private int lastY;
    
    /**
     * Constructor de la clase Location.
     * @param map el mapa del juego.
     * @param X la coordenada X de la ubicación.
     * @param Y la coordenada Y de la ubicación.
     */
    public Location(GameMap map, int X, int Y) {
        this.map = map;
        this.X = X;
        this.Y = Y;
    }
    
    /**
     * Devuelve el mapa del juego.
     * @return el mapa del juego.
     */
    public GameMap getMap() {
        return map;
    }
    
    /**
     * Devuelve la coordenada X de la ubicación.
     * @return la coordenada X de la ubicación.
     */
    public int getX() {
        return X;
    }
    
    /**
     * Devuelve la coordenada Y de la ubicación.
     * @return la coordenada Y de la ubicación.
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Devuelve la última coordenada X registrada.
     * @return la última coordenada X registrada.
     */
    public int getLastX() {
        return lastX;
    }
    
    /**
     * Devuelve la última coordenada Y registrada.
     * @return la última coordenada Y registrada.
     */
    public int getLastY() {
        return lastY;
    }
    
    /**
     * Establece la última coordenada X registrada.
     * @param lastX la última coordenada X registrada.
     */
    public void setLastX(int lastX) {
        this.lastX = lastX;
    }
    
    /**
     * Establece la última coordenada Y registrada.
     * @param lastY la última coordenada Y registrada.
     */
    public void setLastY(int lastY) {
        this.lastY = lastY;
    }
    
    /**
     * Establece la coordenada X de la ubicación.
     * @param x la coordenada X de la ubicación.
     */
    public void setX(int x) {
        X = x;
    }
    
    /**
     * Establece la coordenada Y de la ubicación.
     * @param y la coordenada Y de la ubicación.
     */
    public void setY(int y) {
        Y = y;
    }
    
    /**
     * Incrementa la coordenada X de la ubicación si es posible.
     * @return true si se incrementó la coordenada X, false en caso contrario.
     */
    public boolean incrementX() {
        if (X < map.getMapMaxWidth() - 1) {
            X++;
            return true;
        }
        return false;
    }
    
    /**
     * Incrementa la coordenada Y de la ubicación si es posible.
     * @return true si se incrementó la coordenada Y, false en caso contrario.
     */
    public boolean incrementY() {
        if (Y < map.getMapMaxHeight() - 1) {
            Y++;
            return true;
        }
        return false;
    }
    
    /**
     * Decrementa la coordenada X de la ubicación si es posible.
     * @return true si se decrementó la coordenada X, false en caso contrario.
     */
    public boolean decrementX() {
        if (X > 0) {
            X--;
            return true;
        }
        return false;
    }
    
    /**
     * Decrementa la coordenada Y de la ubicación si es posible.
     * @return true si se decrementó la coordenada Y, false en caso contrario.
     */
    public boolean decrementY() {
        if (Y > 0) {
            Y--;
            return true;
        }
        return false;
    }
    
    /**
     * Crea y devuelve una copia de la ubicación actual.
     * @return una copia de la ubicación actual.
     */
    public Location clone() {
        return new Location(map, X, Y);
    }
    
    @Override
    public String toString() {
        return "Location [map=" + map + ", X=" + X + ", Y=" + Y + "]";
    }
}


/**
 *
 * @author armandorivera
 */
public class NodeMove {
    // Vamos a almacenar la posición de nuestro mejor movimiento
    public NodeMove(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public NodeMove(){
        // Cuando no tenemos un buen movimiento
        x = -1;
        y = -1;
    }

    private int x;
    private int y;
    
    /**
     * Getter and setter
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}

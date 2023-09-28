//Mateo Seijo 309095 - 309095
package domain;
//Bruno Acosta 313080
public class Coordinate {
    private int x;
    private int y;
    
    
    @Override
    public String toString() {
        return "(" + (this.getX()+1) + ","+(this.getY()+1) +")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate coordinate = (Coordinate) o;

        return this.getX() == coordinate.getX() && this.getY() == coordinate.getY();
    }
    
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
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

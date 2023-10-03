//Mateo Seijo 309095 - 309095
//Bruno Acosta  - 313080
package domain;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        //Constructor de Coodinate
        this.x = x;
        this.y = y;
    }

    public int getX() {
        //Get de x
        return x;
    }

    public void setX(int x) {
        //Set de X
        this.x = x;
    }

    public int getY() {
        //Get de Y
        return y;
    }

    public void setY(int y) {
        //Set de Y
        this.y = y;
    }

    @Override
    public String toString() {
        //Se sobre escribe para imprimir de manera correca X e Y como par ordenado
        return "(" + (this.getX() + 1) + "," + (this.getY() + 1) + ")";
    }

    @Override
    public boolean equals(Object o) {
        //Se utiliza para coparar si dos coordenadas son iguales
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate coordinate = (Coordinate) o;

        return this.getX() == coordinate.getX() && this.getY() == coordinate.getY();
    }

}

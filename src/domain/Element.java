//Mateo Seijo 309095
package domain;


public class Element {
    private char color;
    private char symbol;
    private Coordinate coordinate;

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

   

    
    
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    
    public void changeColor(){
        if(this.getColor() == 'B'){
            this.setColor('R');
        }else{
            this.setColor('B');
        }
    }
    
}

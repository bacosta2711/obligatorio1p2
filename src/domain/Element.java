//Mateo Seijo 309095
package domain;


public class Element {
    private char color;
    private char symbol;
    private Coordinate coordinate;

    
    public Coordinate getCoordinate() {
        //Get de coordenada
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        //Set de coordenada
        this.coordinate = coordinate;
    }
    
    public char getColor() {
        //Get del color
        return color;
    }

    public void setColor(char color) {
        //Set del color
        this.color = color;
    }

    public char getSymbol() {
        //Get del simbolo
        return symbol;
    }

    public void setSymbol(char symbol) {
        //Set del simbolo
        this.symbol = symbol;
    }
    
    public void changeColor(){
        //Cambia el color al elemento
        if(this.getColor() == 'B'){
            this.setColor('R');
        }else{
            this.setColor('B');
        }
    }
    
}

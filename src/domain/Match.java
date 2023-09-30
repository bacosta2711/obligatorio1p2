//Mateo Seijo 309095

package domain;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Board board;
    private int matchStart;
    private int matchFinish;
    private List<Coordinate> movements;

    public Match(Board board, int matchStart, int matchFinish) {
        //constructo a partit de un tablero, y el resto de campos
        this.board = board;
        this.matchStart = matchStart;
        this.matchFinish = matchFinish;
        this.movements = new ArrayList<>();
    }
    
    public Match() {
         //Constructor nulo de match
        this.board = null;
        this.matchStart = 0;
        this.matchFinish = 0;
        this.movements = new ArrayList<>();
    }
    
    public Board getBoard() {
        //Get el tablero
        return board;   
    }

    public void setBoard(Board board) {
        //Set del tablero
        this.board = board;
    }

    public int getMatchStart() {
        //Obtener hora inicio
        return matchStart;
    }

    public void setMatchStart(int matchStart) {
        //Asignar hora inicio
        this.matchStart = matchStart;
    }

    public int getMatchFinish() {
        //Obtener hora finalizado
        return matchFinish;
    }

    public void setMatchFinish(int matchFinish) {
        //Set hora finalizado
        this.matchFinish = matchFinish;
    }

    public List<Coordinate> getMovements() {
        //Obtener lista de movimientos ejecutados
        return movements;
    }

    public void setMovements(List<Coordinate> movements) {
        //Asignar lista de movimientos ejecutados
        this.movements = movements;
    }
    
    public void addMovements (Coordinate newCoordinate){
        //Agregar movimiento(cordenada) a lista de los mismos
        this.movements.add(newCoordinate);
    }
    
    public void resetMatch(){
        //TODOD
    }
}

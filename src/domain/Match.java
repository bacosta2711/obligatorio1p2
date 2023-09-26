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
        this.board = board;
        this.matchStart = matchStart;
        this.matchFinish = matchFinish;
        this.movements = new ArrayList<>();
    }
     public Match() {
        this.board = null;
        this.matchStart = 0;
        this.matchFinish = 0;
        this.movements = new ArrayList<>();
    }
    
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getMatchStart() {
        return matchStart;
    }

    public void setMatchStart(int matchStart) {
        this.matchStart = matchStart;
    }

    public int getMatchFinish() {
        return matchFinish;
    }

    public void setMatchFinish(int matchFinish) {
        this.matchFinish = matchFinish;
    }

    public List<Coordinate> getMovements() {
        return movements;
    }

    public void setMovements(List<Coordinate> movements) {
        this.movements = movements;
    }
    public void resetMatch(){
        
    }
    /*public int getDuration(){
        
    }*/
    public void addMovements (Coordinate newCoordinate){
        
    }
}

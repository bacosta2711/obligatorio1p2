//Mateo Seijo 309095

package domain;

import static interfaces.Interface.mainMenu;
import java.util.ArrayList;
import java.util.List;

public class Match {
    private Board board;
    private long matchStart;
    private long matchFinish;
    private List<Coordinate> movements;

    public Match(Board board, long matchStart, long matchFinish) {
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

    public long getMatchStart() {
        return matchStart;
    }

    public void setMatchStart(long matchStart) {
        this.matchStart = matchStart;
    }

    public long getMatchFinish() {
        return matchFinish;
    }

    public void setMatchFinish(long matchFinish) {
        this.matchFinish = matchFinish;
    }

    public List<Coordinate> getMovements() {
        return movements;
    }

    public void setMovements(List<Coordinate> movements) {
        this.movements = movements;
    }
    public void resetMatch(){
        mainMenu();
    }
    public long getDuration() {
        return this.getMatchFinish() - this.getMatchStart();
    }   
    public void addMovements (Coordinate newCoordinate){
        this.movements.add(newCoordinate);
    }
}

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
        this.matchStart = System.currentTimeMillis();
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

    public void setMatchStart() {
        this.matchStart = System.currentTimeMillis();
    }

    public long getMatchFinish() {
        return matchFinish;
    }

    public void setMatchFinish() {
        this.matchFinish = System.currentTimeMillis();;
    }

    public List<Coordinate> getMovements() {
        return movements;
    }

    public void setMovements(List<Coordinate> movements) {
        this.movements = movements;
    }
    public void resetMatch(){
        this.matchStart = System.currentTimeMillis();
        mainMenu();
    }
    public String getDuration() {
    long aux = this.getMatchFinish() - this.getMatchStart();
    long segundos = aux / 1000;
    long minutos = segundos / 60;
    long horas = minutos / 60;
    minutos %= 60;
    segundos %= 60;
    String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
    return tiempo;
    }  
    public void addMovements (Coordinate newCoordinate){
        this.movements.add(newCoordinate);
    }
}

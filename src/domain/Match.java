//Mateo Seijo 309095
//Bruno Acosta 313080
package domain;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private Board board;
    private long matchStart;
    private long matchFinish;
    private List<Coordinate> movements;

    public Match(Board board, long matchStart, long matchFinish) {
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

    public long getMatchStart() {
        //Obtener hora inicio
        return matchStart;
    }

    public void setMatchStart() {
        //Asignar hora inicio
        this.matchStart = System.currentTimeMillis();
    }

    public long getMatchFinish() {
        //Obtener hora finalizado
        return matchFinish;
    }

    public void setMatchFinish() {
        //Set hora finalizado
        this.matchFinish = System.currentTimeMillis();
    }

    public List<Coordinate> getMovements() {
        //Obtener lista de movimientos ejecutados
        return movements;
    }

    public void setMovements(List<Coordinate> movements) {
        //Asignar lista de movimientos ejecutados
        this.movements = movements;
    }

    public void addMovements(Coordinate newCoordinate) {
        //Agregar movimiento(cordenada) a lista de los mismos
        this.movements.add(newCoordinate);
    }

    public String getDuration() {
        long aux = this.getMatchFinish() - this.getMatchStart();
        long segundosTotales = aux / 1000;
        long segundos = segundosTotales % 60;
        long minutosTotales = segundosTotales / 60;
        long minutos = minutosTotales % 60;
        long horas = minutosTotales / 60;

        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        return tiempo;
    }

    public void resetMatch() {
        this.matchStart = System.currentTimeMillis();
    }
    
    public List<Coordinate> getMatchSolution() {
        List<Coordinate> solutions = this.getBoard().getBoardSolution();
        List<Coordinate> movements = this.getMovements();
        List<Coordinate> realSolution = new ArrayList<>();
        for (Coordinate coordinate : solutions) {
            int count = 0;
            for (Coordinate c : solutions) {
                if (coordinate.equals(c)) {
                    count++;
                }
            }
            for (Coordinate c : movements) {
                if (coordinate.equals(c)) {
                    count++;
                }
            }
            if (count % 2 != 0 && !realSolution.contains(coordinate)) {
                realSolution.add(coordinate);
            }
        }
        for (Coordinate coordinate : movements) {
            int count = 0;
            for (Coordinate c : solutions) {
                if (coordinate.equals(c)) {
                    count++;
                }
            }
            for (Coordinate c : movements) {
                if (coordinate.equals(c)) {
                    count++;
                }
            }

            if (count % 2 != 0 && !realSolution.contains(coordinate)) {
                realSolution.add(coordinate);
            }
        }
        return realSolution;
    }
}

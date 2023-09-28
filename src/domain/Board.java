//Mateo Seijo 309095
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private Element [][] matElement;
    private int countRows;
    private int countColums;
    private int difficulty;
    private List<Coordinate> boardSolution;

    public Board(){
        
    }
    public Board (int colums, int rows, int diff){
        this.countColums = colums;
        this.countRows= rows;
        this.difficulty = diff;
    }

    public Element[][] getMatElement() {
        return matElement;
    }
    
    
    
    public Board newRandomBoard( Board board){
        char[] symbols = {'/', '\\', '|', '-'};
        Random rand = new Random();
        
        int xBoard = board.getCountRows();
        int yBoard =board.getCountColums();
        
        Element [][] matAux = new Element [xBoard] [yBoard];   
        
        for(int x=0;x<xBoard;x++){
            for (int y=0;y<yBoard;y++) {
                Element e = new Element();
                e.setColor((yBoard+xBoard%2==0) ? 'B' : 'R');
                e.setSymbol(symbols[rand.nextInt(4)]);
                Coordinate cord = new Coordinate(x,y);
                e.setCoordinate(cord);
                matAux [x][y] = e;
              }
          }
        board.setMatElement(matAux);
      
    return board;
    }
    public void setMatElement(Element[][] matElement) {
        this.matElement = matElement;
    }

    public int getCountRows() {
        return countRows;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
    }

    public int getCountColums() {
        return countColums;
    }

    public void setCountColums(int countColums) {
        this.countColums = countColums;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public List<Coordinate> getBoardSolution() {
        return boardSolution;
    }
    
    public void setBoardSolution(List<Coordinate> boardSolution) {
        this.boardSolution = boardSolution;
    }
    public void doGeneric (Element oneElement){
        char direction = oneElement.getSymbol();
        switch (direction) {
            case '-' : doHorizontal(oneElement);
            break;
            case '/' : doDiagonal(oneElement);
            break;
            case '|' : doVertical(oneElement);
            break;
            case '\\' : doAntiDiagonal(oneElement);
            break;
        }
    }
    public void doGeneric (Match match,Element oneElement){
        char direction = oneElement.getSymbol();
        switch (direction) {
            case '-' : doHorizontal(oneElement);
            break;
            case '/' : doDiagonal(oneElement);
            break;
            case '|' : doVertical(oneElement);
            break;
            case '\\' : doAntiDiagonal(oneElement);
            break;
        }
        match.addMovements(oneElement.getCoordinate());
    }
    public void doDiagonal(Element oneElement) {
    int row = oneElement.getCoordinate().getX();
    int col = oneElement.getCoordinate().getY();
    int i = row;
    int j = col;
    while (i >= 0 && j < getCountColums()) {
        getMatElement()[i][j].changeColor();
        i--;
        j++;
    }
    i = row + 1;
    j = col - 1;
    while (i < getCountRows() && j >= 0) {
        getMatElement()[i][j].changeColor();
        i++;
        j--;
    }
    }
    public void doAntiDiagonal(Element oneElement){
    int row = oneElement.getCoordinate().getX();
    int col = oneElement.getCoordinate().getY();
    int i = row;
    int j = col;
    while (i < getCountRows() && j < getCountColums()) {
        getMatElement()[i][j].changeColor();
        i++;
        j++;
    }
    i = row - 1;
    j = col - 1;
    while (i >= 0 && j >= 0) {
        getMatElement()[i][j].changeColor();
        i--;
        j--;
    }
    }
    public void doVertical (Element oneElement){
        for (int y = 0; y < getCountRows(); y++) {         
                 this.getMatElement()[y] [oneElement.getCoordinate().getY()].changeColor();
             }
    }
    public void doHorizontal (Element oneElement){
        for (int x = 0; x < getCountColums(); x++) {
            this.getMatElement()[oneElement.getCoordinate().getX()] [x].changeColor();
        }
    }
    
    public boolean isResolved(Element[][] oneElement){
        char color = oneElement[0][0].getColor();
        boolean result = true;
        for (int y = 0; y < this.getCountRows() && result; y++) {
            for (int x = 0; x < this.getCountColums() && result; x++) {
                if(oneElement[x][y].getColor()!=color){
                    result=false;
                }
            }   
        }
        return result;
    }
    public boolean isMovementvalid(Board b,Coordinate c){
        return (c.getX()<=b.getCountRows() && c.getY()<=b.getCountColums()) || (c.getX()==-2&&c.getY()==-2 || c.getX()==-2&&c.getY()>=0 );
    }
   
    public void stepBack(Match match) {
    List<Coordinate> movementsAux = match.getMovements();
    int lastIndex = movementsAux.size() - 1;
    if (lastIndex >= 0) {
        Element element = getElementByCoord(match.getBoard(), movementsAux.get(lastIndex));
        doGeneric(element);
        movementsAux.remove(lastIndex);
        match.setMovements(movementsAux);
    } else {
        System.out.println("No hay movimientos para deshacer.");
    }
    }
    
    public void addCooordinateToBoard(Coordinate c) {
        this.boardSolution.add(c);
    }
    
    public void createSolution(Board oneBoard) {
    int numRows = oneBoard.getCountRows();
    int numCols = oneBoard.getCountColums();
    List<Coordinate> solutionCoordinates = new ArrayList<>();
    int difficulty = oneBoard.getDifficulty();
    
    while (solutionCoordinates.size() < difficulty) {
        int randomX = (int) (Math.random() * numRows);
        int randomY = (int) (Math.random() * numCols);
        Coordinate coordinate = new Coordinate(randomX, randomY);
        boolean coordinateExists = false;
        for (Coordinate existingCoordinate : solutionCoordinates) {
            if (existingCoordinate.equals(coordinate)) {
                coordinateExists = true;
                break;
            }
        }
        if (!coordinateExists) {
            solutionCoordinates.add(coordinate);
        }
    } 
    oneBoard.setBoardSolution(solutionCoordinates);
    }
    public void executeSolution (Board board){
        List<Coordinate> aux = board.getBoardSolution();
        for (int i = 0; i< aux.size(); i++){
            Coordinate coordinate = aux.get(i);
            Element auxElement = getElementByCoord(board,coordinate);
            System.out.println("La solucion es: "+(auxElement.getCoordinate().getX()+1)+"(F)"+(auxElement.getCoordinate().getY()+1)+"(C)");
            doGeneric(auxElement);
    }  
    }
    public Element getElementByCoord(Board board, Coordinate cord){
    Element [][] matAux = board.getMatElement();
        return matAux[cord.getX()][cord.getY()];
    }
    public void clone(Board other){
        Element [][] matAux = new Element[other.getCountRows()][other.getCountColums()];
        
        for (int i = 0; i < other.getCountRows(); i++) {
            for (int j = 0; j < other.getCountColums(); j++) {
                Element e = new Element();
                e.setColor(other.getMatElement()[i][j].getColor());
                e.setCoordinate(other.getMatElement()[i][j].getCoordinate());
                e.setSymbol(other.getMatElement()[i][j].getSymbol());
                matAux[i][j] = e ;
            }
        }
        /*
        List<Coordinate> solutionCoordinates = new ArrayList<>();
        //this. boardSolution = new ArrayList<>();
        for (Coordinate coordinate : other.getBoardSolution()) {
            solutionCoordinates.add(new Coordinate(coordinate.getX(),coordinate.getY()));
        }
        this.setBoardSolution(solutionCoordinates);*/
        
        this.setMatElement(matAux);
        
        this.setCountColums(other.getCountColums()); 
        this.setCountRows(other.getCountRows()); 
        this.setDifficulty(other.getDifficulty()); 
        
    }
}   

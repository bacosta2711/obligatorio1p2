//Mateo Seijo 309095
package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private Element [][] matElement;
    private int countRows;
    private int countColums;
    private int difficulty;
    private List<Coordinate> boardSolution;
    
    public Board(){ //TODO VALIDAR
        
    }
    
    public Board (int colums, int rows, int diff){
        //Constructor para un tablero a partir de columnas, filas y dificultad
        this.countColums = colums;
        this.countRows= rows;
        this.difficulty = diff;
    }

    public Element[][] getMatElement() {
        //Get matriz de el tablero
        return this.matElement;
    }
    
    public void setMatElement(Element[][] matElement) {
        //Set de matriz de tablero
        this.matElement = matElement;
    }
    
    public int getCountRows() {
        //Get de cantidad de filas del tablero
        return countRows;
    }

    public void setCountRows(int countRows) {
        //Set de cantidad de filas del tablero
        this.countRows = countRows;
    }

    public int getCountColums() {
         //Get de cantidad de columnas del tablero
        return countColums;
    }

    public void setCountColums(int countColums) {
        //Set de cantidad de columnas del tablero
        this.countColums = countColums;
    }

    public int getDifficulty() {
        //Get de dificultad del tablero
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        //Set de dificultad del tablero
        this.difficulty = difficulty;
    }
    
    public List<Coordinate> getBoardSolution() {
        //Get de Solucion del tamblero
        return boardSolution;
    }
    
    public void setBoardSolution(List<Coordinate> boardSolution) {
        //Set de Solucion del tamblero
        for (Coordinate coordinate : boardSolution) {
            System.out.println(coordinate.toString());
        }
        this.boardSolution = boardSolution;
    }
    
    public Element getElementByCoord(Board board, Coordinate cord){
    //Get Elemento from cordenadas 
    Element [][] matAux = board.getMatElement();
        return matAux[cord.getX()][cord.getY()];
    }
    
    public void newRandomBoard(){
        //Una vez creado este metodo se inicializa el tablero asginando valores aleatorios a cada posicion del mismo teniendo en cuenta la dificultad del mismo
        char[] symbols = {'/', '\\', '|', '-'};
        Random rand = new Random();
        
        int xBoard = this.getCountRows();
        int yBoard =this.getCountColums();
        
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
        this.setMatElement(matAux);
    }   
    
    public void newFileBoard(String file){
         //Este metodo recibe la ubicacion de un archivo y en base al mismo y el formato dado carga el tablero y sus soluciones
         
         try {
            Scanner in = new Scanner(new File(file));           
            String separator = " ";

            String linea = in.nextLine();
            String[] lcol = linea.split(separator);
            int cantRow =  Integer.parseInt(lcol[0]);
            int cantCol =  Integer.parseInt(lcol[1]);
            Element [] [] mat = new Element[cantRow] [cantCol];
            
            for (int x = 0; x < cantRow; x++) {
                linea = in.nextLine();    
                lcol = linea.split(separator);         
                for (int i=0;i<cantCol; i++) {
                    Element e = new Element();
                    e.setColor((lcol[i].charAt(1)=='A')? 'B' :'R');
                    e.setSymbol(lcol[i].charAt(0));
                    Coordinate c = new Coordinate(x,i);
                    e.setCoordinate(c);
                    e.toString();
                    mat[x][i] = e;
                }
            }
                
            List<Coordinate> solutionCoordinates = new ArrayList<>();
            linea = in.nextLine();    
            int cantSol = Integer.parseInt(linea);
        
            for (int i=0;i<cantSol; i++) {
                linea = in.nextLine(); 
                lcol = linea.split(separator);
                Coordinate c = new Coordinate(Integer.parseInt(lcol[0])-1,Integer.parseInt(lcol[1])-1);
                solutionCoordinates.add(c);
            }
            //Board b = new Board(cantRow,cantCol,cantSol);
            this.setDifficulty(cantSol);
            this.setCountColums(cantCol);
            this.setCountRows(cantRow);
            this.setBoardSolution(solutionCoordinates);
            this.setMatElement(mat);
            
            in.close();
            
            
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
     }
   
    public void addCooordinateToBoard(Coordinate c) {
         //Agregar una solicion a la lista de soluciones
        this.boardSolution.add(c);
    }
    
    public void doGeneric (Element oneElement){
        //se encarga de ver que movimiento se requiere hacer mara llamar a la funcion correspondiente
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
    
    public void doDiagonal(Element oneElement) {
        //Dado un elemento se encarga de cambiar el color a todos los que esten comprendidos en su diagonal
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
        //Dado un elemento se encarga de cambiar el color a todos los que esten comprendidos en su Anti-diagonal 
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
        //Dado un elemento se encarga de cambiar el color a todos los que esten comprendidos en su vertical
        for (int y = 0; y < getCountRows(); y++) {         
                 this.getMatElement()[y] [oneElement.getCoordinate().getY()].changeColor();
             }
    }
    
    public void doHorizontal (Element oneElement){
        //Dado un elemento se encarga de cambiar el color a todos los que esten comprendidos en su horizontal
        for (int x = 0; x < getCountColums(); x++) {
            this.getMatElement()[oneElement.getCoordinate().getX()] [x].changeColor();
        }
    }
    
    public void stepBack(Match match) {
    //Implementacion del movimiento hacia atras    
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
    
    public void createSolution() {
        //Dado una dificultad, se encarga de generar la cantidad minima de soluciones e impactarlas en el tablero
        int numRows = this.getCountRows();
        int numCols = this.getCountColums();
        List<Coordinate> solutionCoordinates = new ArrayList<>();
        int difficulty = this.getDifficulty();

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
        this.setBoardSolution(solutionCoordinates);
    }
    
    public void clone(Board other){
        //Dado un board le asigna un clon del pasado por parametro
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
        
        this.setMatElement(matAux);
        
        this.setCountColums(other.getCountColums()); 
        this.setCountRows(other.getCountRows()); 
        this.setDifficulty(other.getDifficulty()); 
        
    }
    
    public void executeSolution (){
        //Una vez usada la funcion crateSolution() debemos ejecutar esta para aplicar las soluciones al tablero
        List<Coordinate> aux = this.getBoardSolution();
        for (int i = 0; i< aux.size(); i++){
            Coordinate coordinate = aux.get(i);
            Element auxElement = getElementByCoord(this,coordinate);
            System.out.println("La solucion es: "+(auxElement.getCoordinate().getX()+1)+"(F)"+(auxElement.getCoordinate().getY()+1)+"(C)");
            doGeneric(auxElement);
        }  
    }
    
    public boolean isResolved(){
        //Dado el tablero nos indica si el mismo esta resulelt(todo el mismo color)
        char color = this.getMatElement()[0][0].getColor();
        boolean result = true;
        for (int y = 0; y < this.getCountRows() && result; y++) {
            for (int x = 0; x < this.getCountColums() && result; x++) {
                if(this.getMatElement()[y][x].getColor()!=color){
                    result=false;
                }
            }   
        }
        return result;
    }
    
    public boolean isMovementvalid(Board b,Coordinate c){
        //Dadas las coodeneadas que el usuario ingresa, nos indica si es un movimiento valido(tienen en cuenta el ir hacia atras y dimension del tablero)
        return (c.getX()<=b.getCountRows()-1 && c.getY()<=b.getCountColums()-1) || (c.getX()==-2&&c.getY()==-2 || c.getX()==-2&&c.getY()>=0 );
    }
}   

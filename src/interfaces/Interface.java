package interfaces;

import java.util.Scanner;
import domain.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Interface {
     static Match match = new Match();
     public static void main(String[] args) {
         mainMenu();
     }
     
     public static void mainMenu(){
         Scanner in = new Scanner(System.in);
         
         System.out.println("           ----------------------   ");
         System.out.println("           BIENVENIDO A SOLIFLIPS   ");
         System.out.println("           ----------------------   ");
         
         System.out.println("");
         
         System.out.println("- Desea jugar con nosotros? ingrese S si asi lo desea, de lo contrario ingrese N");
         
         String op= in.next();
         while(!(op.equalsIgnoreCase("S")||op.equalsIgnoreCase("N"))){
             System.out.println("Opcion no valida, si desea jugar ingrese S si asi lo desea, de lo contrario ingrese N");
             op= in.next();
         }
         
         if(op.equalsIgnoreCase("S")){
             playingMenu();
         }else{
            exit();
         }
     }
     
     
     public static void playingMenu(){
         Scanner in = new Scanner(System.in);
         
         System.out.println("           ----------------------   ");
         System.out.println("                VAMOS A JUGAR!      ");
         System.out.println("           ----------------------   ");
         boolean opcionValida = false;
         do{
         System.out.println("- Como desea implementar el tablero?   ");
         System.out.println(" ");
         System.out.println("a- Tomar datos de del archivo 'datos.txt'");
         System.out.println("b- Usar el tablero predefinido.");
         System.out.println("c- Usar tablero al azar.");
         
         
         
         String op= in.next();
         switch(op){
             case "a": boardFormFile("./test/datos.txt");
             opcionValida = true;
                break;
             case "A" : boardFormFile("./test/datos.txt");
             opcionValida = true;
                 break;
             case "b": boardFormFile("./test/default.txt");
             opcionValida = true;
                break;
             case "B" : boardFormFile("./test/default.txt");
             opcionValida = true;
                 break;
             case "c": boardFormRandom();
             opcionValida = true;
                break;
             case "C" : boardFormRandom();
             opcionValida = true;
                 break;
             case "x": exit();
             opcionValida = true;
                break;
             case "X" : exit();
             opcionValida = true;
                break;     
             default:
                 
                 System.out.println("Por favor, ingrese una opcion valida.");
         }
         }while(!opcionValida);
         
        play();
     }
     
     public static void play(){
         Scanner in = new Scanner(System.in);
         
         Coordinate coords = new Coordinate(0,0);
         printBoard(match.getBoard(), false, coords);
         
         do{
         
            Coordinate cord = new Coordinate(0,0);

            do {
                System.out.printf("\n");
                System.out.printf("- Ingrese la columna(entre 1 y %d) de la posicion que desea",match.getBoard().getCountColums());
                System.out.printf("\n");
                String y = in.next();
                if(y.equalsIgnoreCase("X")){
                    exit();
                }else{
                    if(y.equalsIgnoreCase("H")){
                        do{ System.out.println("Los movimientos que se han realizado son:");
                        showHistory(match);
                        System.out.printf("- Ingrese la columna(entre 1 y %d) de la posicion que desea",match.getBoard().getCountColums());
                        System.out.println("");
                        y = in.next();
                        } while(y.equalsIgnoreCase("H"));


                    }else{
                        if(y.equalsIgnoreCase("S")){
                            do{
                            System.out.println("Los movimientos que daran solucion al tablero son:");
                            showHistory(match);
                            showSolution(match);
                            System.out.printf("- Ingrese la columna(entre 1 y %d) de la posicion que desea",match.getBoard().getCountColums());
                            System.out.println("");
                            y = in.next();
                            } while(y.equalsIgnoreCase("S"));
                        }else{
                            cord.setY(Integer.parseInt(y)-1);
                        }
                    }
                }
            }while(!match.getBoard().isMovementvalid(match.getBoard(),cord));

            do {
                System.out.printf("- Ingrese la fila(entre 1 y %d) de la posicion que desea",match.getBoard().getCountRows());
                System.out.printf("\n");
                String x = in.next();
                if(x.equalsIgnoreCase("X")){
                    exit();
                }else{
                    if(x.equalsIgnoreCase("H")){
                        do{ 
                        System.out.println("Los movimientos que se han realizado son:");
                        showHistory(match);
                        System.out.printf("- Ingrese la fila(entre 1 y %d) de la posicion que desea",match.getBoard().getCountRows());
                        System.out.println("");
                        x = in.next();
                       }while(x.equalsIgnoreCase("H"));

                    }else{
                        if(x.equalsIgnoreCase("S")){
                            do{
                            System.out.println("Los movimientos que daran solucion al tablero son:");
                            showHistory(match);
                            showSolution(match);
                            System.out.printf("- Ingrese la fila(entre 1 y %d) de la posicion que desea",match.getBoard().getCountRows());
                            System.out.println("");
                            x = in.next();
                            }while(x.equalsIgnoreCase("S"));
                        }else{
                            cord.setX(Integer.parseInt(x)-1);
                        }
                    }
                }
            }while(!match.getBoard().isMovementvalid(match.getBoard(),cord));
            
            if(cord.getX()==-2&&cord.getY()==-2){
                //TODO IMPLEMENTAR IR PARA ATRAS
                
            }else{
                printBoard(match.getBoard(), true, cord);
            }
            
         }while(!match.getBoard().isResolved(match.getBoard().getMatElement()));
         
         if(match.getBoard().isResolved(match.getBoard().getMatElement())){
             System.out.printf("\n");
             System.out.println("Felicitaciones completaste el juego! en #INGRESAR TIEMPO#");
             System.out.println("");
             System.out.println("- Desea jugar con nosotros de nuevo? ingrese S si asi lo desea, de lo contrario ingrese N");
             System.out.println("");
             
             String op= in.next();
             while(!(op.equalsIgnoreCase("S")||op.equalsIgnoreCase("N"))){
                System.out.println("Opcion no valida, si desea volver a jugar ingrese S si asi lo desea, de lo contrario ingrese N");
                op= in.next();
             }

             if(op.equalsIgnoreCase("S")){
                playingMenu();
             }else{
               exit();
             }
             }
            
       }
         
     
            
      public static void printBoard(Board board,boolean reqMovements, Coordinate cords){  
        String blue = "\u001B[34m";
        String red = "\u001B[31m";
        String black = "\u001B[0m";
                
        int xBoard = board.getCountRows();
        int yBoard =board.getCountColums();
        
        if (reqMovements){
            Board boardNew= new Board();
            boardNew.clone(board);
            
            boardNew.doGeneric(match,boardNew.getElementByCoord(boardNew, cords));
            boardNew.setBoardSolution(board.getBoardSolution());
            for (int p=0;p<2;p++){
                System.out.print("    1");
                for (int y=1;y<yBoard; y++) {
                    System.out.printf("   %d",y+1);
                }   
                System.out.print("              ");   
            }
           System.out.printf("\n");
           for (int p=0;p<2;p++){
                printDivisor(yBoard);
                System.out.print("            ");
            }
             
            
            for(int x=0;x<xBoard;x++) {
                System.out.printf("\n");
                System.out.printf("%d ", x+1);
                for(int y = 0; y < yBoard; y++) {     
                    System.out.printf("|");
                    
                    Coordinate c = new Coordinate(x,y);
                    Element eToPrint = board.getElementByCoord(board,c);
                    
                    if(eToPrint.getColor()=='B'){
                        System.out.print(" "+blue+eToPrint.getSymbol()+black+" ");
                    }else{
                         System.out.print(" "+red+eToPrint.getSymbol()+black+" ");
                    }    
                   
                }
                System.out.printf("|");
                
                printStep();
                 System.out.printf("%d ", x+1);
                 for(int y = 0; y < yBoard; y++) {     
                    System.out.printf("|");
                    
                    Coordinate c = new Coordinate(x,y);
                    Element eToPrint = boardNew.getElementByCoord(boardNew,c);
                    
                    if(eToPrint.getColor()=='B'){
                        System.out.print(" "+blue+eToPrint.getSymbol()+black+" ");
                    }else{
                         System.out.print(" "+red+eToPrint.getSymbol()+black+" ");
                    }    
                   
                }
                System.out.printf("|");
                System.out.printf("\n");
                printDivisor(yBoard);
                 System.out.print("            ");
                printDivisor(yBoard);
                
            }
            match.setBoard(boardNew);
        }else{
            
            System.out.print("    1");
            for (int y=1;y<yBoard; y++) {
                System.out.printf("   %d",y+1);
            }    
            System.out.printf("\n");
            
            for(int x=0;x<xBoard;x++) {
                printDivisor(yBoard);
                System.out.printf("\n");
                System.out.printf("%d ", x+1);
                for(int y = 0; y < yBoard; y++) {     
                    System.out.printf("|");
                    
                    Coordinate c = new Coordinate(x,y);
                    Element eToPrint = match.getBoard().getElementByCoord(board,c);
                    
                    if(eToPrint.getColor()=='B'){
                        System.out.print(" "+blue+eToPrint.getSymbol()+black+" ");

                    }else{
                        System.out.print(" "+red+eToPrint.getSymbol()+black+" ");
                    }
                }
                System.out.printf("|");
                System.out.printf("\n");
            }
            printDivisor(yBoard);
            System.out.printf("\n");
            System.out.printf("\n");
        }
    }
    
    
     public static void printDivisor(int length){
         System.out.printf("  +---");
         for (int y=1;y<length-1; y++) {
             System.out.printf("+---",y+1);
         }
         System.out.printf("+---+");
    }
    
    
    public static void printStep(){
        System.out.print("    ===>    ");
    }
     
     
     
     
     public static void boardFormFile(String file){
        //String nombreArchivo = "./test/datos.txt";

        try {
            Scanner in = new Scanner(new File(file));
            
            String separator = " ";
            
            
            // Leer y mostrar el contenido del archivo línea por línea
            //while (in.hasNextLine()) {
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
                    Coordinate c = new Coordinate(Integer.parseInt(lcol[0]),Integer.parseInt(lcol[1]));
                    solutionCoordinates.add(c);
                }
                Board b = new Board(cantRow,cantCol,cantSol);
                b.setCountColums(cantCol);
                b.setCountRows(cantRow);
                b.setBoardSolution(solutionCoordinates);
                b.setMatElement(mat);
                
               
                match.setBoard(b);
                
                
                
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
     
     
     }
     public static void boardFormDefault(){}
     public static void boardFormRandom(){
         Scanner in = new Scanner(System.in);
         
         int dificulty=0;
         int rows=0;
         int column=0;
         
         String letterAux = "";
         
         System.out.println("");
         
         do {
             System.out.println("- Ingrese la cantidad de columnas que desea (entre 3 y 9)");
             letterAux = in.next();
             if(letterAux.equalsIgnoreCase("X")){
                 exit();
             }else{
                 column = Integer.parseInt(letterAux);
             }
         }while(column < 3 || column>9);
         
         System.out.println("");
         
         do {
             System.out.println("- Ingrese la cantidad de filas que desea (entre 3 y 9)");
             letterAux = in.next();
             if(letterAux.equalsIgnoreCase("X")){
                 exit();
             }  
             else{
                 rows = Integer.parseInt(letterAux);
             }
         }while(rows < 3 || rows>9);
         
         
         System.out.println("");
         
         do {
             System.out.println("- Ingrese la dificulatd que desea (entre 1 y 8)");
             letterAux = in.next();
             if(letterAux.equalsIgnoreCase("X")){
                 exit();
             }else{
                 dificulty = Integer.parseInt(letterAux);
             }
         }while(dificulty < 1 || dificulty>8);
         
         System.out.println("");
         
         Board b = new Board(rows,column,dificulty);
         b =b.newRandomBoard(b);
          b.createSolution(b);
         b.executeSolution(b);
         match.setBoard(b);
         
     }
     
     public static void reset(){}

     public static void exit(){
         System.out.println("");
         System.out.println("Muchas gracias por todo! Hasta la proxima :)");
         System.exit(0);
     }
     
     public static void showSolution(Match match){
        List<Coordinate> solutions = match.getBoard().getBoardSolution();
        
       
         for (int i = solutions.size() - 1; i >= 0; i--) {
              Coordinate c = solutions.get(i);
              System.out.println(c.toString());
        }
     }
     
     public static void showHistory(Match match){
        List<Coordinate> movements = match.getMovements();
        
        for (int i = movements.size() - 1; i >= 0; i--) {
              Coordinate c = movements.get(i);
              System.out.println(c.toString());
        }
     }
}



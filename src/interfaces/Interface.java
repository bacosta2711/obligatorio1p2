package interfaces;

import java.util.Scanner;
import domain.*;
import java.util.ArrayList;
import java.util.List;

public class Interface {

    static Match match = new Match();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        //metodo que se encarga de imprimir el menu de bienvenida
        Scanner in = new Scanner(System.in);

        System.out.println("           ----------------------   ");
        System.out.println("           BIENVENIDO A SOLIFLIPS   ");
        System.out.println("           ----------------------   ");

        System.out.println("");

        System.out.println("- Desea jugar con nosotros? ingrese S si asi lo desea, de lo contrario ingrese N");

        String op = in.next();
        while (!(op.equalsIgnoreCase("S") || op.equalsIgnoreCase("N"))) {
            System.out.println("Opcion no valida, si desea jugar ingrese S si asi lo desea, de lo contrario ingrese N");
            op = in.next();
        }

        if (op.equalsIgnoreCase("S")) {
            match.setMatchStart();
            playingMenu();
        } else {
            exit();
        }
    }

    public static void playingMenu() {
        //metodo que se encarga de implementar la logica para levantar el tablero
        Scanner in = new Scanner(System.in);

        System.out.println("           ----------------------   ");
        System.out.println("                VAMOS A JUGAR!      ");
        System.out.println("           ----------------------   ");
        boolean validOption = false;
        do {
            System.out.println("- Como desea implementar el tablero?   ");
            System.out.println(" ");
            System.out.println("a- Tomar datos de del archivo 'datos.txt'");
            System.out.println("b- Usar el tablero predefinido.");
            System.out.println("c- Usar tablero al azar.");

            String op = in.next();
            op = op.toUpperCase();
            switch (op) {
                case "A":
                    boardFormFile("./test/datos.txt");
                    validOption = true;
                    break;
                case "B":
                    boardFormFile("./test/default.txt");
                    validOption = true;
                    break;
                case "C":
                    boardFormRandom();
                    validOption = true;
                    break;
                case "X":
                    exit();
                    validOption = true;
                    break;
                default:

                    System.out.println("Dato incorrecto.Por favor, ingrese una opcion valida.");
                    System.out.println(" ");
            }
        } while (!validOption);

        play();
    }

    public static void play() {
        //aqui se implementa toda la logica de jugar
        Scanner in = new Scanner(System.in);

        Coordinate coords = new Coordinate(0, 0);
        printBoard(match.getBoard(), false, coords);
        do {
            Coordinate cord = new Coordinate(-3, -3);//Se crea una no valida de entrada
            do {
                do {
                    System.out.printf("- Ingrese la fila(entre 1 y %d) de la posicion que desea", match.getBoard().getCountRows());
                    System.out.printf("\n");
                    String x = in.next();
                    System.out.println("");
                    if (x.equalsIgnoreCase("X")) {
                        exit();
                    } else if (x.equalsIgnoreCase("H")) {
                        System.out.println("Los movimientos que se han realizado son:");
                        showHistory(match);
                        play();
                    } else if (x.equalsIgnoreCase("S")) {
                        System.out.println("Los movimientos que daran solucion al tablero son:");
                        showSolution(match);
                        play();
                    } else {
                        try {
                            cord.setX(Integer.parseInt(x) - 1);
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese una opción/caracter válido.");
                        }
                    }
                } while (!match.getBoard().isXValid(cord.getX()));

                do {
                    System.out.printf("- Ingrese la columna(entre 1 y %d) de la posicion que desea", match.getBoard().getCountColums());
                    System.out.printf("\n");
                    String y = in.next();
                    System.out.println("");
                    if (y.equalsIgnoreCase("X")) {
                        exit();
                    } else if (y.equalsIgnoreCase("H")) {
                        System.out.println("Los movimientos que se han realizado son:");
                        showHistory(match);
                        play();
                    } else if (y.equalsIgnoreCase("S")) {
                        System.out.println("Los movimientos que daran solucion al tablero son:");
                        showSolution(match);
                        play();
                    } else {
                        try {
                            cord.setY(Integer.parseInt(y) - 1);
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese una opción/caracter válido.");
                        }
                    }
                } while (!match.getBoard().isYValid(cord.getY()));

                if (!match.getBoard().isMovementValid(cord)) {
                    System.out.println("Par de cordenadas no valido!");
                }
            } while (!match.getBoard().isMovementValid(cord));

            if (cord.getX() == -2 && cord.getY() == -2) {
                boolean result = match.getBoard().stepBack(match);
                if (!result) {
                    System.out.println("No hay movimientos para desahacer.");
                }
                printBoard(match.getBoard(), false, cord);
            } else {
                printBoard(match.getBoard(), true, cord);
            }

        } while (!match.getBoard().isResolved());

        if (match.getBoard().isResolved()) {
            System.out.printf("\n");
            match.setMatchFinish();
            System.out.println("- El tiempo que duró su partida fue de : " + match.getDuration() + " (HH:MM:SS)");
            System.out.println("");
            System.out.println("- Desea jugar con nosotros de nuevo? ingrese S si asi lo desea, de lo contrario ingrese N");
            System.out.println("");

            String op = in.next();
            while (!(op.equalsIgnoreCase("S") || op.equalsIgnoreCase("N"))) {
                System.out.println("Opcion no valida, si desea volver a jugar ingrese S si asi lo desea, de lo contrario ingrese N");
                op = in.next();
            }

            if (op.equalsIgnoreCase("S")) {
                match.setMatchStart();

                reset();
            } else {
                exit();
            }
        }

    }

    public static void printBoard(Board board, boolean reqMovements, Coordinate cords) {
        //dado un tablero debemos indicar si vamos a realziar un movimiento,en caso de realizarlo indicar las coords
        //Esto se debe a que varia si se imprime un tablero o dos
        String blue = "\u001B[34m";
        String red = "\u001B[31m";
        String black = "\u001B[0m";

        int xBoard = board.getCountRows();
        int yBoard = board.getCountColums();

        if (reqMovements) {
            Board boardNew = new Board();
            boardNew.clone(board);

            boardNew.doGeneric(boardNew.getElementByCoord(boardNew, cords));
            match.addMovements(cords);

            boardNew.setBoardSolution(board.getBoardSolution());
            for (int p = 0; p < 2; p++) {
                System.out.print("    1");
                for (int y = 1; y < yBoard; y++) {
                    System.out.printf("   %d", y + 1);
                }
                System.out.print("              ");
            }
            System.out.printf("\n");
            for (int p = 0; p < 2; p++) {
                printDivisor(yBoard);
                System.out.print("            ");
            }

            for (int x = 0; x < xBoard; x++) {
                System.out.printf("\n");
                System.out.printf("%d ", x + 1);
                for (int y = 0; y < yBoard; y++) {
                    System.out.printf("|");

                    Coordinate c = new Coordinate(x, y);
                    Element eToPrint = board.getElementByCoord(board, c);

                    if (eToPrint.getColor() == 'B') {
                        System.out.print(" " + blue + eToPrint.getSymbol() + black + " ");
                    } else {
                        System.out.print(" " + red + eToPrint.getSymbol() + black + " ");
                    }

                }
                System.out.printf("|");

                printStep();
                System.out.printf("%d ", x + 1);
                for (int y = 0; y < yBoard; y++) {
                    System.out.printf("|");

                    Coordinate c = new Coordinate(x, y);
                    Element eToPrint = boardNew.getElementByCoord(boardNew, c);

                    if (eToPrint.getColor() == 'B') {
                        System.out.print(" " + blue + eToPrint.getSymbol() + black + " ");
                    } else {
                        System.out.print(" " + red + eToPrint.getSymbol() + black + " ");
                    }

                }
                System.out.printf("|");
                System.out.printf("\n");
                printDivisor(yBoard);
                System.out.print("            ");
                printDivisor(yBoard);

            }
            System.out.println("");
            match.setBoard(boardNew);
        } else {

            System.out.print("    1");
            for (int y = 1; y < yBoard; y++) {
                System.out.printf("   %d", y + 1);
            }
            System.out.printf("\n");

            for (int x = 0; x < xBoard; x++) {
                printDivisor(yBoard);
                System.out.printf("\n");
                System.out.printf("%d ", x + 1);
                for (int y = 0; y < yBoard; y++) {
                    System.out.printf("|");

                    Coordinate c = new Coordinate(x, y);
                    Element eToPrint = match.getBoard().getElementByCoord(board, c);

                    if (eToPrint.getColor() == 'B') {
                        System.out.print(" " + blue + eToPrint.getSymbol() + black + " ");

                    } else {
                        System.out.print(" " + red + eToPrint.getSymbol() + black + " ");
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

    public static void printDivisor(int length) {
        //Se encarga de hacer las divisiones en el tablero basado en el largo del mismo
        System.out.printf("  +---");
        for (int y = 1; y < length - 1; y++) {
            System.out.printf("+---", y + 1);
        }
        System.out.printf("+---+");
    }

    public static void printStep() {
        //imprime las flechas de la transaccion del tbleo
        System.out.print("    ===>    ");
    }

    public static void boardFormFile(String file) {
        //Este metodo recibe la ubicacion de un archivo y en base al mismo y el formato dado carga el tablero y sus soluciones
        Board b = new Board();
        b.newFileBoard(file);
        match.setBoard(b);
    }

    public static void boardFormRandom() {
        //Este metodo se encarga de solicitar la informacion necesaria para crear un tablero aleatorio
        Scanner in = new Scanner(System.in);

        int dificulty = 0;
        int rows = 0;
        int column = 0;

        String letterAux = "";

        System.out.println("");

        do {
            try {
                System.out.println("- Ingrese la cantidad de columnas que desea (entre 3 y 9)");
                letterAux = in.next();
                if (letterAux.equalsIgnoreCase("X")) {
                    exit();
                } else {
                    column = Integer.parseInt(letterAux);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opcion valida");
                continue;
            }
        } while (column < 3 || column > 9);

        System.out.println("");

        do {
            try {
                System.out.println("- Ingrese la cantidad de filas que desea (entre 3 y 9)");
                letterAux = in.next();
                if (letterAux.equalsIgnoreCase("X")) {
                    exit();
                } else {
                    rows = Integer.parseInt(letterAux);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opcion valida");
                continue;
            }
        } while (rows < 3 || rows > 9);

        System.out.println("");

        do {
            try {
                System.out.println("- Ingrese la dificulatd que desea (entre 1 y 8)");
                letterAux = in.next();
                if (letterAux.equalsIgnoreCase("X")) {
                    exit();
                } else {
                    dificulty = Integer.parseInt(letterAux);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opcion valida");
                continue;
            }
        } while (dificulty < 1 || dificulty > 8);

        System.out.println("");
        boolean isReallyValid = false;
        Board b = new Board(rows, column, dificulty);
        while (!isReallyValid) {
            b.newRandomBoard();
            isReallyValid = b.isResolved();
        }
        b.createSolution();
        b.executeSolution();
        match.setBoard(b);

    }

    public static void reset() {
        List<Coordinate> list = new ArrayList<>();
        match.setMovements(list);
        playingMenu();
    }

    public static void exit() {
        //Imprime la informacion necesaria para salir del juego
        System.out.println("");
        System.out.println("Muchas gracias por todo! Hasta la proxima :)");
        System.exit(0);

    }

    public static void showSolution(Match match) {
        //Se encarga de mostrar la solucion del tablero
        List<Coordinate> solutions = match.getMatchSolution();

        for (int i = solutions.size() - 1; i >= 0; i--) {
            Coordinate c = solutions.get(i);
            System.out.println(c.toString());

        }
    }

    public static void showHistory(Match match) {
        //Se encarga de mostrar el historial de movimientos
        List<Coordinate> movements = match.getMovements();

        for (int i = movements.size() - 1; i >= 0; i--) {
            Coordinate c = movements.get(i);
            System.out.println(c.toString());
        }
    }
}

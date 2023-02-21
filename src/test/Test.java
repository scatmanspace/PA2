package test;

import game.*;

import java.util.*;

public class Test {

    /**
     * This method checks the argument size, length, and format of input commands.
     *
     * @param given is a string array made by splitting the command input at the spaces.
     * @return returns false if the length is not the desired length/format and true otherwise.
     */
    private static Boolean argCheck(String[] given) {
        if (given.length != 2                                            // checks argument length
                || given[1].length() != 4                                 // checks move char length
                || !given[1].matches("[a-h][1-8][a-h][1-8]")) {     // checks move format
            System.out.println("Invalid Input!\n");
            return false;
        }
        return true;
    }

    /*
    mv d2d4
    mv d3d5
    mv e7e6
    mv d1d1
    mv d1d3
    mv d8h4
    mv c1e3
    */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);      // input scanner
        Game game = new Game("1", "2");
        game.showBoard(System.out);

        System.out.println("Welcome to the chess game!");
        while (true) {
            System.out.println("\nEnter Command: \n");

            String command = keyboard.nextLine();
            String[] arguments;


            if (command.equals("quit"))
                break;
            else if (command.equals("print status")) {
                game.showBoard(System.out);
                System.out.println(game.getMoves());
            }
            else if (command.equals("undo")) {
                if (game.undoMove()) {
                    game = new Game(game.getPlayer1(), game.getPlayer2(), game.getMoves());
                    game.showBoard(System.out);
                } else
                    System.out.println("No moves to undo!");
            }
            else if (command.startsWith("new game")) {
                arguments = command.split(" ");                        // splits input to isolate player names
                game = new Game(arguments[2], arguments[3]);
                game.showBoard(System.out);
            }
            else if (command.startsWith("mv")) {
                arguments = command.split(" ");                        // splits input to isolate move
                if (!argCheck(arguments)) continue;
                if (game.move(new Move(arguments[1]), false)) {       // checks if move is legal
                    System.out.printf("\n%s Moves: %s\n\n", (!game.isWhiteTurn() ? game.getPlayer1() : game.getPlayer2()), arguments[1]);
                    game.showBoard(System.out);
                } else
                    System.out.println("Illegal Move!");
            }
            else if (command.startsWith("cp")) {
                arguments = command.split(" ");                        // splits input to isolate move
                if (!argCheck(arguments)) continue;
                if (game.move(new Move(arguments[1]), true)) {        // checks if move is legal
                    System.out.printf("\n%s Captures: %s\n\n", (!game.isWhiteTurn() ? game.getPlayer1() : game.getPlayer2()), arguments[1]);
                    game.showBoard(System.out);
                } else
                    System.out.println("Illegal Capture!");
            }
            else {
                System.out.println("Invalid command entered.");
            }
        }
    }
}


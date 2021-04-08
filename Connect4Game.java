/* <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><
W. Jace Rinder
<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
package connectX;

import java.util.*;

import static connectX.IGameBoard.*;

public class Connect4Game {
    public static void main(String[] args) {
        boolean halt_play = false; //Variable used to wrap the main for multiple iterations of the game
        GameBoard game;
        while(halt_play == false)
        {
            int row_check = 0, col_check = 0, win_check = 0;
            Scanner scanner = new Scanner(System.in); //Gets input from client
            boolean valid_row = false, valid_col = false, valid_win = false;
            //While-loop used to prompt user for number of rows to be initialized
            while(valid_row == false)
            {
                System.out.println("How many rows should be on the board?\n");
                row_check = Integer.parseInt(scanner.nextLine());
                if(row_check < MIN_SIZE)
                    System.out.println("Must have at least 3 rows.\n");
                else if(row_check > MAX_SIZE)
                    System.out.println("Can have at most 100 rows\n");
                else
                    valid_row = true;

            }
            //While-loop used to prompt user for number of rows to be initialized
            while(valid_col == false)
            {
                System.out.println("How many columns should be on the board?\n");
                col_check = Integer.parseInt(scanner.nextLine());
                if(col_check < MIN_SIZE)
                    System.out.println("Must have at least 3 columns.\n");
                else if(col_check > MAX_SIZE)
                    System.out.println("Can have at most 100 columns\n");
                else
                    valid_col = true;

            }
            //While-loop used to prompt user for winning condition to be specified
            while(valid_win == false)
            {
                System.out.println("How many in a row to win?\n");
                win_check = Integer.parseInt(scanner.nextLine());
                if(win_check < MIN_WIN)
                    System.out.println("Must have at least 3 in a row to win.\n");
                else if(win_check > MAX_WIN)
                    System.out.println("Can have at most 25 in a row to win\n");
                else
                    valid_win = true;

            }
            game = new GameBoard(row_check,col_check,win_check); // Initializes the board to be used
            char user = 'X';    //Sets first move to 'X
            int col = 0; //Starts the column specifies at 0
            while (!game.checkTie() && !game.checkForWin(col)) { // Loop designed to iterate until a win or tie occurs
                if (game.moves_made() == 0) { //Body for first turn of the game
                    System.out.println(game.toString()); // prints the board
                    System.out.println("Player " + user + ", what column do you want to place your marker in?\n");
                    col = Integer.parseInt(scanner.nextLine()); //reads in columns of choice by user
                    if (col < 0) { //Checks for negative input
                        System.out.println("Column cannot be less than 0\n");
                        col = 0;
                    } else if (col > game.getNumColumns()) { //Checks for excessive input
                        System.out.println("Column cannot be greater than" + game.getNumColumns() + "\n");
                        col = 0;
                    } else if (!game.checkIfFree(col)) { //Checks for overflow of columns
                        System.out.println("Column is full\n");
                    } else { //If all fail-safes check out, places token
                        game.placeToken(user, col);
                        System.out.println(game.toString());
                    }
                } else if (game.moves_made() % 2 == 1) {
                    user = 'O';
                    System.out.println("Player " + user + ", what column do you want to place your marker in?\n");
                    col = Integer.parseInt(scanner.nextLine());
                    if (col < 0) {  //Checks for negative input
                        System.out.println("Column cannot be less than 0\n");
                        col = 0;
                    } else if (col > game.getNumColumns()) {    //Checks for excessive input
                        System.out.println("Column cannot be greater than" + game.getNumColumns() + "\n");
                        col = 0;
                    } else if (!game.checkIfFree(col)) {    //Checks for overflow of columns
                        System.out.println("Column is full\n");
                    } else {    //If all fail-safes check out, places token
                        game.placeToken(user, col);
                        System.out.println(game.toString());
                    }
                } else {
                    user = 'X';
                    System.out.println("Player " + user + ", what column do you want to place your marker in?\n");
                    col = Integer.parseInt(scanner.nextLine());
                    if (col < 0) {  //Checks for negative input
                        System.out.println("Column cannot be less than 0\n");
                        col = 0;
                    } else if (col > game.getNumColumns()) {   //Checks for excessive input
                        System.out.println("Column cannot be greater than " + game.getNumColumns() + "\n");
                        col = 0;
                    } else if (!game.checkIfFree(col)) {    //Checks for overflow of columns
                        System.out.println("Column is full\n");
                    } else {    //If all fail-safes check out, places token
                        game.placeToken(user, col);
                        System.out.println(game.toString());
                    }
                }
                if (game.checkForWin(col)) {    //If a win has been verified, prints the client that is victorious
                    System.out.println("Player " + user + " won!\n");
                    halt_play = true; //Sets the replay specifier to true
                }
                if (game.checkTie()) {  //If a tie has been verified, informs the client of a tie
                    System.out.println("It's a tie!\n");
                    halt_play = true; //Sets the replay specifier to true
                }
                if (halt_play == true) { //If the replay specifier is true, asks the client if they would like to replay
                    char choice = ' '; //Variable to represent the choice of the client
                    //This loop is designed to cycle until the client inputs a valid response
                    while (choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N') {
                        System.out.println("Would you like to play again? Y/N\n");
                        choice = scanner.next().charAt(0);
                        if (choice == 'Y' || choice == 'y') //If the client chooses yes, the replay specifier is reset
                            halt_play = false;
                    }
                }
            }
        }
    }
}

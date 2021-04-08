/* <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><
W. Jace Rinder
<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
package connectX;

/**
 * invariant MIN_SIZE <= row <= MAX_SIZE
 * invariant MIN_SIZE <= col <= MAX_SIZE
 * invariant MIN_WIN <= win <= MAX_WIN
 * Correspondence: num.of.rows = row
 * Correspondence: num.of.cols = col
 * Correspondence: num.to.win = win
 */

public class GameBoard implements IGameBoard
{
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    private char [][] board;           //Two-dimensional array to represent the gameboard
    private static int row;            //Private static constant to constitute the row dimension of the board
    private static int col;            //Private static constant to constitute the col dimension of the board
    private static int win;            //Private static constant to represent the winning condition
    private int num_pieces;            //This private int variable represnts the total number of moves made
    private static move prev_move;     //Char member variable to represent the last piece type used
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    // Sub-class to be used within the GameBoard class as a data structure designed to hold the information of the
    // moves made by the client
    private class move {
        public int row;
        public int col;
        public char type;

        move(int r, int c, char t) {
            row = r;
            col = c;
            type = t;
        }
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * @return Returns an object of the GameBoard class with the specification of the arguments
     * @post Object returned is fully initialized and has dimensions of 'r' and 'c' as well as a win condition of 'w'
     * @pre MIN_SIZE <= r <= MAX_SIZE  :  MIN_SIZE <= c <= MAX_SIZE :  MIN_WIN <= w <= MAX_WIN
     */
    //Constructor, which initializes the board and the two helper variables of the class
    public GameBoard(int r, int c, int w)
    {
        row = r;
        col = c;
        win = w;
        board = new char[row][col];
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                board[i][j] = ' ';
            }
        }
        num_pieces = 0;
        prev_move = new move(0, 0, 'X');
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    public void placeToken(char p, int c)
    {
        for(int i = 0; i < row; i++) //For-loop that iterates through every row of the board
        {
            if(board[i][c] == ' ') //Conditional to detect if a free space is available
            {
                board[i][c] = p;    //Occupies free space with parameter p
                prev_move.row = i;  //Series of operations to set the prev_move to the most recent move
                prev_move.col = c;
                prev_move.type = p;
                num_pieces++;    //Counter for number of pieces on the board
                break;
            }
        }
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    public char whatsAtPos(int r, int c)
    {
        if(r < 0 || r >= row || c < 0 || c >= col) //Conditional to check for spaces outside the bounds of the board
            return '$';
        return board[r][c];
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Overridden toString method to format the contents of the board for visualization
     * @return String formatted to display the contents of the board
     * @post The returned string will accurately represent the contents of the board
     */
    @Override
    public String toString()
    {
        String s = "";
        for(int i = 0; i < col; i++) // For-loop designed to iterate through the columns of the board
        {
            s += "| " + i;  //Sets the numerical frame of the board
        }
        s += "|\n"; //Formatting pipe and newline
        for(int i = row - 1; i >= 0; i--) // For-loop designed to iterate through the rows of the board
        {
            for(int j = 0; j < col; j++) //For-loop designed to iterate through the columns of the board
            {
                s += "|" + board[i][j] + " ";  //Appends respective spaces to the visual board
            }
            s += "|\n"; //Formatting pipe and newline
        }
        return s; //Formatted string representing the board
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    public int moves_made() {return num_pieces;}
    public int getNumRows() {return row;}
    public int getNumColumns() {return col;}
    public int getNumToWin() {return win;}
    public int getPrevRow() {return prev_move.row;}
    public char getPrevType() {return prev_move.type;}
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
}
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/

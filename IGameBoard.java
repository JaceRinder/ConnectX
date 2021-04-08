package connectX;

public interface IGameBoard
{
    /**
     *  IGameBoard will ideally be implemented by classes representing a ConnectX game, where X is a designated win con-
     *  dition.  The board of the game will be stored as a two-dimensional grid, where whitespace represents an unused
     *  element and "X" and "O" will represent respective players.
     *
     *
     * Initialization Ensures: Upon instantiating a class which implements this interface, a two-dimensional board will
     * be created with whitespace in each element.  The board size will be within MIN_SIZE and MAX_SIZE for both the
     * rows and the columns.
     *
     * Defines: rows = Z
     *          columns = Z
     *          win_condition = Z
     *
     * Constraints: MIN_SIZE <= row <= MAX_SIZE
     *              MIN_SIZE <= columns <= MAX_SIZE
     *              MIN_WIN <= win_condition <= MAX_WIN
     */
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    //Constant variables to be used in every class
    int MIN_SIZE = 3;
    int MAX_SIZE = 100;
    int MIN_WIN = 3;
    int MAX_WIN = 25;
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * @param p,c p represents which user the token belongs to and the c is the designated column to
     *            which it will be placed
     * @return void
     * @post A token of the respective user will be placed on the board in column c
     * @pre 0 <= c <= columns  :  checkIfFree(c) == true  :  p == 'X' || p == 'O'
     */
    void placeToken(char p, int c);
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * @param r,c int r and c represent the respective row and column to be checked
     * @return A char representing the contents of the arguments' position or a '$' representing a cell out of bounds
     * @post Given the position denoted by the parameter, this function will either return the contents of the board
     *          or return a '$' which denotes a space outside the bounds of the board
     * @pre r >= 0  :  c >= 0
     */
    char whatsAtPos(int r, int c);
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/

    /**
     * Getter function that returns the number of pieces on the board
     * @return Integer value representing the number of pieces on the board
     * @post returned value = number of pieces on the board
     */
    int moves_made();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Getter function that returns the number of rows on the board
     * @return Integer value representing the number of rows on the board
     * @post returned value = rows
     */
    int getNumRows();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Getter function that returns the number of columns on the board
     * @return Integer value representing the number of columns on the board
     * @post returned value = columns
     */
    int getNumColumns();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Getter function that returns the win condition of the board
     * @return Integer value representing the number of like pieces in a row needed to win
     * @post returned value = win_condition
     */
    int getNumToWin();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Getter function that returns the respective row of the last move made
     * @return Integer value representing the row of the last move made
     * @post returned value = row.of.last.move
     */
    int getPrevRow();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Getter function that returns the type of the last move made
     * @return Char representing the type of the last move made ('X' or 'O')
     * @post returned value = type.of.last.move
     */
    char getPrevType();
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Wrapper function used to check the board in every direction for a winning configuration
     * @param c This integer represents the column that will be used to check for a win
     * @return The boolean return value is true if a win has been detected and false otherwise
     * @post The function will check horizontally, vertically, and diagonally for a winning configuration and return
     * true or false.
     * @pre 0 <= c <= columns
     */
    default boolean checkForWin(int c)
    {
        if(checkHorizWin(getPrevRow(), c, getPrevType())) // Calls for horizontal verification
            return true;
        if(checkVertWin(getPrevRow(), c, getPrevType())) // Calls for vertical verification
            return true;
        if(checkDiagWin(getPrevRow(), c, getPrevType())) // Calls for diagonal verification
            return true;
        return false; // If no winning verification is found, returns false
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Checks the board horizontally for a winning configuration of type 'p'
     * @param r,c,p The int r represents the row of the last move made; the int c represents the column of the last move
     *              made; the char p represents the respective user of the last move
     * @return The boolean return value of this function returns true if a horizontal win is detected, false otherwise
     * @post This function will verify if a horizontal winning condition has taken place
     * @pre 0 <= r <= rows : 0 <= c <= columns  :  p == 'X' || p == 'O'
     */
    default boolean checkHorizWin(int r, int c, char p)
    {
        int count = 0;
        for(int i = 0; i < getNumColumns(); i++)    //For-loop that iterates through the columns of the board
        {
            if(whatsAtPos(r,i) == p)    //If position (r,i) is equal to parameter p, increment the counter
                count++;
            else                        //If a non-matching position is detected, counter reset
                count = 0;
            if(count == getNumToWin())  //If counter == win condition, return true
                return true;
        }
        return false;
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Checks the board vertically for a winning configuration of type 'p'
     * @param r,c,p The int r represents the row of the last move made; the int c represents the column of the last move
     *              made; the char p represents the respective user of the last move
     * @return The boolean return value of this function returns true if a vertical win is detected, false otherwise
     * @post This function will verify if a vertical winning condition has taken place
     * @pre 0 <= r <= rows : 0 <= c <= columns  :  p == 'X' || p == 'O'
     */
    default boolean checkVertWin(int r, int c, char p)
    {
        int count = 0;
        for(int i = 0; i < getNumRows(); i++)     //For-loop that iterates through the rows of the board
        {
            if(whatsAtPos(i,c) == p)    //If position (i,c) is equal to parameter p, increment the counter
                count++;
            else                        //If a non-matching position is detected, counter reset
                count = 0;
            if(count == getNumToWin())            //If counter == win condition, return true
                return true;
        }
        return false;
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Checks the board diagonally for a winning configuration of type 'p'
     * @param r,c,p The int r represents the row of the last move made; the int c represents the column of the last move
     *              made; the char p represents the respective user of the last move
     * @return The boolean return value of this function returns true if a diagonal win is detected, false otherwise
     * @post This function will verify if a diagonal winning condition has taken place
     * @pre 0 <= r <= rows : 0 <= c <= columns  :  p == 'X' || p == 'O'
     */
    default boolean checkDiagWin(int r, int c, char p)
    {
        int count = 0;
        for(int i = getNumToWin() - 1; i > 0; i--)        //For-loop designed to check the 3 descending spaces of diagonal /
        {
            if(whatsAtPos((r - i), (c - i)) == p) //Conditional to check similarity of parameter p and neigboring spaces
            {
                count++;
                if(count == getNumToWin() - 1)//If 3 successive diagonal neighbors match parameter p, return true
                    return true;
            }
            else    //Reset counter upon reaching unlike space
            {
                count = 0;
            }
        }
        for(int i = 1; i <= getNumToWin() - 1; i++)//For-loop designed to check the 3 asscending spaces of diagonal /
        {
            if(whatsAtPos((r + i), (c + i)) == p)//Conditional to check similarity of parameter p and neigboring spaces
            {
                count++;
                if(count == getNumToWin() - 1)//If 3 successive diagonal neighbors match parameter p, return true
                    return true;
            }
            else    //Reset counter upon reaching unlike space
            {
                count = 0;
            }
        }
        count = 0;
        for(int i = getNumToWin() - 1;i > 0; i--)//For-loop designed to check the 3 descending spaces of diagonal \
        {
            if(whatsAtPos((r + i), (c - i)) == p)//Conditional to check similarity of parameter p and neigboring spaces
            {
                count++;
                if(count == getNumToWin() - 1)//If 3 successive diagonal neighbors match parameter p, return true
                    return true;
            }
            else    //Reset counter upon reaching unlike space
            {
                count = 0;
            }
        }
        for(int i = 1; i <= getNumToWin() - 1; i++)//For-loop designed to check the 3 ascending spaces of diagonal
        {
            if(whatsAtPos((r - i), (c + i)) == p)//Conditional to check similarity of parameter p and neighboring spaces
            {
                count++;
                if(count == getNumToWin() - 1) //If 3 successive diagonal neighbors match parameter p, return true
                    return true;
            }
            else    //Reset counter upon reaching unlike space
            {
                count = 0;
            }
        }
        return false;
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Checks if the board configuration indicates a tie
     * @return The boolean return value is true if a tie is detected and false otherwise
     * @post This function verifies a tie configuration on the board
     */
    default boolean checkTie() {
        for (int i = 0; i < getNumColumns(); i++) //For-loop designed to iterate through the columns of the board
        {
            if (checkForWin(i) || checkIfFree(i))  //If a win or free space is on the board, a tie is not present
                return false;
        }
        return true; // Indicates a tie
    }
    /*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
    /**
     * Check whether column 'c' is full
     * @param c
     * @return The boolean return value is true if the column has space remaining, false otherwise
     * @post This function verifies if there is in fact free space in column 'c'
     * @pre 0 <= c <= columns
     */
    default boolean checkIfFree(int c)
    {
        for(int i = 0; i < getNumRows(); i++) // For-loop iterates through the entire column
        {
            if(whatsAtPos(i, c) == ' ') //If free space is detected, returns true
                return true;
        }
        return false;
    }
}
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/
/*<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>*/

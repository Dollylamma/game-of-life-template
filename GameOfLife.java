import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y)
    {
        // Construct a 2d array of the given x and y size.
        board = new int[x][y];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        // call step the number of times requested
        for(int i=0; i<turns; i++){
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step()
    {
        print();
        int newBoard[][] = new int[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                newBoard[i][j]=board[i][j];
            }
        }
        // Update the game board, store a 1 if the cell is alive and a 0 otherwise.
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                int neighbor=countNeighbors(i, j);
                // System.out.println(i + " + " + j + " + " + neighbor);
                if(get(i, j)==0){
                    if(neighbor==3){
                        newBoard[i][j]=1;
                    }
                } else{
                    if(neighbor<2 || neighbor>3){
                        newBoard[i][j]=0;
                    }
                }
            }
        }
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j]=newBoard[i][j];
            }
        }
    }


    public int countNeighbors(int x, int y) {
        int count = 0;
        // count the number of neighbors the cell has
        // use the get(x,y) method to read any board state you need.
        int xarr[] = {1,  1,  0, -1, -1, -1, 0, 1};
        int yarr[] = {0, -1, -1, -1,  0,  1, 1, 1};
        for(int i=0; i<8; i++){
            if(get(x+xarr[i], y+yarr[i]) == 1){
                count++;
            }
        }
        return count;
    }

    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}

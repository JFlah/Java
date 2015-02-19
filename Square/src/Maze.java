import javax.swing.*;
import java.io.*;
import java.util.*;
/**
 *  This class represents a Maze in the maze solver application
 */
public class Maze {
    static boolean debug = true;
    int height;
    int width;
    Square[][] grid;
    Square startSquare;
    Square exitSquare;

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public Square getStart(){
        return startSquare;
    }

    public Square getExit(){
        return exitSquare;
    }

    public Square getSquare(int row, int col){
        return grid[row][col];
    }

    public void reset(){
        for(int r=0; r<height; r++)
            for(int c=0; c<width; c++)
                grid[r][c].reset();
    }

    public static Maze loadMaze(String fname) throws FileNotFoundException {
        Maze maze = new Maze();
        File mfile = new File(fname);
        Scanner scan = new Scanner(mfile);
        if (scan.hasNext()) {
            maze.height = scan.nextInt();
            maze.width = scan.nextInt();
            maze.grid = new Square[maze.height][maze.width];
        }
        int i = 0;
        while (scan.hasNext()) {
            for (int j = 0; j < maze.width; j++) {
                if (scan.hasNextInt()) {
                    int type = scan.nextInt();
                    Square current = new Square(type, i, j);
                    if (type == 2) {
                        maze.startSquare = current;
                    } else if (type == 3) {
                        maze.exitSquare = current;
                    }
                    maze.grid[i][j] = current;
                }
            }
            i++;
        }
        if (debug)
            System.out.println(maze);
        return maze;
    }

    public List<Square> getNeighbors(Square square){
        List<Square> neighbors = new ArrayList<Square>();
        if (square.getRow()-1 >= 0) {
            neighbors.add(this.grid[square.getRow()-1][square.getColumn()]);
        }
        if (square.getColumn()+1 < this.width ) {
            neighbors.add(this.grid[square.getRow()][square.getColumn()+1]);
        }
        if (square.getRow()+1 < this.height) {
            neighbors.add(this.grid[square.getRow()+1][square.getColumn()]);
        }
        if (square.getColumn()-1 >= 0) {
            neighbors.add(this.grid[square.getRow()][square.getColumn()-1]);
        }
        return neighbors;
    }

    public String toString(){
        String mstring = "";
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                mstring += grid[i][j];
            }
            if(i<height-1)
                mstring+="\n";
        }
        return mstring;
    }
}

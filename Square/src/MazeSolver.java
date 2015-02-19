import java.util.*;

abstract class MazeSolver {
    protected Maze maze;
    protected boolean solved=false; // set to true when the maze is solved
    protected boolean solvable=true;// set to false if the maze cannot be solved


    MazeSolver(Maze maze){
        this.maze = maze;
        createWorklist();
        maze.getStart().mark();
        addToWorklist(maze.getStart());
    }

    public boolean isSolved(){
        return solved;
    }

    public boolean isSolvable(){
        return solvable;
    }

    abstract void createWorklist();

    abstract boolean isWorklistEmpty();

    abstract void addToWorklist(Square sq);

    abstract Square removeFromWorklist();

    public List<Square> getPath() {
        List<Square> path = new ArrayList<Square>();
        if (!isSolved()) {
            return path;
        }
        Square squa = removeFromWorklist();
        if (squa.getType() == 3) {
            while (squa.getBack().getType() != 2) {
                path.add(squa);
                squa = squa.getBack();

            }
            path.add(squa);
        }
//        else if (squa.getType() == 2) {
//
//        }
        return path;
    }

    public void step(){
        if (isWorklistEmpty()) {
            this.solvable = false;
            return;
        }
        Square ourSq = removeFromWorklist();
        if (ourSq.getType() == 3) {
            this.solved = true;
            addToWorklist(ourSq);
            return;
        }

        List<Square> neighbors = this.maze.getNeighbors(ourSq);

        for (Square s : neighbors) {
            if (s.getType() != 0 && !s.getMarked() && !s.explored) {
                s.mark();
                s.setBack(ourSq);
                addToWorklist(s);
            }
        }
        ourSq.explored = true;
    }
}

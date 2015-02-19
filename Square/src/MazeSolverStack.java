class MazeSolverStack extends MazeSolver {

private LinkedStack<Square> worklist;

    MazeSolverStack(Maze maze) {
        super(maze);
    }

    void createWorklist() {
        this.worklist = new LinkedStack<Square>();
    }

    boolean isWorklistEmpty() {
        return this.worklist.isEmpty();
    }

    void addToWorklist(Square sq) {
        sq.mark();
        this.worklist.push(sq);
    }

    Square removeFromWorklist() {
        return this.worklist.pop();
    }


}

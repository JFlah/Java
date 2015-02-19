class MazeSolverQueue extends MazeSolver {

    private LinkedQueue<Square> worklist;

    MazeSolverQueue(Maze maze) {
        super(maze);
    }

    void createWorklist() {
        this.worklist = new LinkedQueue<Square>();
    }

    boolean isWorklistEmpty() {
        return this.worklist.isEmpty();
    }

    void addToWorklist(Square sq) {
        this.worklist.enqueue(sq);
    }

    Square removeFromWorklist() {
        return this.worklist.dequeue();
    }
}

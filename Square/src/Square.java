/**
 * This class represents a square in a maze.
 */
public class Square {
    private int type;
    private int row;
    private int column;
    public boolean marked;
    public boolean explored;
    public boolean onpath;
    private Square back; // a reference to the previous square in the path

    public static final int SPACE=0, WALL=1, START=2, EXIT=3;
    public static final String typeStrings[] = {"_","#","S","E"};

    public Square(int type, int row, int column){
        this.type = type;
        this.row = row;
        this.column = column;
        marked = false;
        explored = false;
        onpath = false;
        back = null;
    }

    public void onpath(){
        onpath = true;
    }

    public void mark(){
        marked = true;
    }

    public void reset(){
        marked = false;
        explored = false;
        onpath = false;
        back = null;
    }

    public void setBack(Square sq){
        back = sq;
    }

    public Square getBack(){
        return back;
    }

    public boolean getMarked(){
        return marked;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public int getType(){
        return type;
    }

    public String toString(){
        if(type == START)
            return "S";
        else if(type == EXIT)
            return "E";
        else if(onpath)
            return "x";
        else if(explored)
            return ".";
        else if(marked)
            return "o";
        else if(type>=0 && type<4)
            return typeStrings[type];
        else
            return "?";
    }


}
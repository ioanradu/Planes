public class Move {

    public int line;
    public int col;

    public Move(int line, int col) {  // definim constructorul clasei
        this.line = line;
        this.col = col;
    }
    public int returnLine() {
        return line;
    }

    public int returnColumn() {
        return col;
    }

}

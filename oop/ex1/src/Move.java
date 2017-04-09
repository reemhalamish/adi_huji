
public class Move {

    private int row, leftBound, rightBound;

    public Move(int row, int leftBound, int rightBound) {
        this.row = row;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public int getRow() {
        return row;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public String toString(){
        return row + ":" + leftBound + "-" + rightBound;
    }
}

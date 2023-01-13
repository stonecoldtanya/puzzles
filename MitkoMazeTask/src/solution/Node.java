package MitkoMazeTask.src.solution;

import java.util.*;

public class Node{
    private char[][] goal = {{'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                            {'*', '*', '*', '*', '*', '*', '*', 'N', 'N', 'N', '*', '*', '*'},
                            {'*', '*', 'N', 'N', '*', '*', 'N', 'N', '*', 'N', '*', '*', '*'},
                            {'*', '*', '*', 'N', '*', '*', '*', '*', '*', 'N', 'N', 'N', '*'},
                            {'*', '*', '*', 'N', '*', '*', '*', 'M', '*', 'N', '*', '*', '*'},
                            {'*', '*', '*', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '*', '*', '*'},
                            {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                            {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}};
    private char[][] state;
    private Node parent;
    private int goalRow = 4;
    private int goalCol = 7;
    private int cost = 0;
    private List<Node> neighbours;
    private int mRow;
    private int mCol;


    public boolean isGoal(char[][] state) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                if (state[i][j] == 'M') {
                    this.mRow = i;
                    this.mCol = j;
                    break;
                }
            }
        }
        if (mCol == goalCol && mRow == goalRow) {
            return true;
        }

        return false;
    }

    public Node(int mRow, int mCol){
        this.state = getState();
    }

    public Node(char[][] state) {
        this.state = state;
        this.parent = null;
        this.cost = 0;
        this.neighbours = null;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                if (state[i][j] == 'M') {
                    mRow = i;
                    mCol = j;
                }
            }
        }
        this.mCol = mCol;
        this.mRow  = mRow;
    }
    private static char[][] copyState(char[][] source) {
        int numberOfColumns = source.length;
        int numberOfRows = source[0].length;

        char[][] destination = new char[numberOfColumns][numberOfRows];
        for (int column = 0; column < numberOfColumns; column++) {
            System.arraycopy(source[column], 0, destination[column], 0, numberOfRows);
        }
        return destination;
    }

    public Node(char[][] state, Node parent) {
        this.state = state;
        this.parent = parent;
    }
    public Node(char[][] state, Node parent, int mRow, int mCol) {
        this.state = state;
        this.parent = parent;
        this.mRow =  mRow;
        this.mCol = mCol;
    }

    public void addNeighbour(Node neighbour) {
        neighbour.setParent(this);
        neighbour.setCost(neighbour.getCost());
        this.neighbours.add(neighbour);
    }

    private static char[][] swapper(char[][] base, int rowM, int colM, int row, int col) {
        char[][] temp = copyState(base);
        char tempChar = temp[rowM][colM];
        temp[rowM][colM] = temp[row][col];
        temp[row][col] = tempChar;

        return temp;
    }

    public List<Node> getNeighbours() {
        if (this.neighbours == null) {
            int rowM = getMRow();
            int colM = getMCol();

            List<Node> result = new ArrayList<Node>();
            // Move left
            if (rowM >= 0 && rowM <= 7 && colM <= 12 && colM > 0 && state[rowM][colM - 1] != 'N') {
                char[][] neighbourState = swapper(state, rowM, colM, rowM, colM - 1);
                Node neighbour = new Node(neighbourState, this, rowM, colM - 1);
                this.cost += 1;
                result.add(neighbour);

            }

            // Move right
            if (rowM >= 0 && rowM <= 7 && colM < 12 && colM >= 0 && state[rowM][colM + 1] != 'N') {
                char[][] neighbourState = swapper(state, rowM, colM, rowM, colM + 1);
                Node neighbour = new Node(neighbourState, this, rowM, colM + 1);
                this.cost += 1;
                result.add(neighbour);
            }

            // Move up
            if (rowM > 0 && rowM <= 7 && colM <= 12 && colM >= 0 && state[rowM - 1][colM] != 'N') {
                char[][] neighbourState = swapper(state, rowM, colM, rowM - 1, colM);
                Node neighbour = new Node(neighbourState, this, rowM - 1, colM);
                this.cost += 1;
                result.add(neighbour);
            }

            // Move down
            if (rowM >= 0 && rowM < 7 && colM <= 12 && colM >= 0 && state[rowM + 1][colM] != 'N') {
                char[][] neighbourState = swapper(state, rowM, colM, rowM + 1, colM);
                Node neighbour = new Node(neighbourState, this, rowM + 1, colM);
                this.cost += 1;
                result.add(neighbour);
            }
            this.neighbours = result;
        }

        return this.neighbours;
    }

    public static int manhattan(Node node) {
        int result = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                char[][] state = node.getState();
                if (state[i][j] == 'M') {
                    result = Math.abs(node.getGoalRow() - i) + Math.abs(node.getGoalCol() - j);
                }
            }
        }
        return result;
    }

    private double evaluate() {
        Node next = new Node(state);
        return next.getCost() + manhattan(next);
    }


    public char[][] getGoal() {
        return goal;
    }

    public void setGoal(char[][] goal) {
        this.goal = goal;
    }

    public char[][] getState() {
        return this.state;
    }

    public void setState(char[][] state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getGoalRow() {
        return goalRow;
    }

    public void setGoalRow(int goalRow) {
        this.goalRow = goalRow;
    }

    public int getGoalCol() {
        return goalCol;
    }

    public void setGoalCol(int goalCol) {
        this.goalCol = goalCol;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public int getMRow() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                if (state[i][j] == 'M') {
                    this.mRow = i;
                    this.mCol = j;
                }
            }
        }
        return mRow;
    }

    public void setMRow(int mRow) {
        this.mRow = mRow;
    }

    public int getMCol() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                if (state[i][j] == 'M') {
                    this.mRow = i;
                    this.mCol = j;
                }
            }
        }
        return mCol;
    }

    public void setMCol(int mCol) {
        this.mCol = mCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.equals(goal, node.goal) && Arrays.equals(state, node.state);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(goal);
        result = 31 * result + Arrays.hashCode(state);
        return result;
    }


//    public char[][] print
//        @Override
//    public int compareTo(Node next) {
////        this.evaluate((Node) next.neighbours) - this.evaluate(next);
//        return 0;
//
//    }

//    @Override
//    public int compare(Node x, Node y) {
////        if (x.evaluate(x) < y.evaluate(y)) {
////            return -1;
////        }
////        if (x.evaluate(x) > y.evaluate(y)) {
////            return 1;
////        }
//        return (int) (x.evaluate(x) - y.evaluate(y));
////        return 0;
//    }

//    @Override
//    public int compareTo(Node other) {
//        if (other == null) return 1;
//
//        return (int) (this.evaluate() - other.evaluate());
//        //return -1;
//    }


    @Override
    public String toString() {
        return "Node{" +
                "state=" + Arrays.toString(state) +
                '}';
    }
}



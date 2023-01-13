package MitkoMazeTask.src.solution;


import java.util.List;

public class MainMaze {

    public static void main(String[] args) {
        char[][] startState = {{'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*', '*', 'N', 'N', 'N', '*', '*', '*'},
                {'*', '*', 'N', 'N', '*', '*', 'N', 'N', '*', 'N', '*', '*', '*'},
                {'*', '*', '*', 'N', '*', '*', '*', '*', '*', 'N', 'N', 'N', '*'},
                {'*', '*', '*', 'N', '*', '*', '*', '*', '*', 'N', '*', '*', '*'},
                {'M', '*', '*', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}};

        pathFinder a = new pathFinder(startState);
        System.out.printf("The puzzle is:");
        System.out.println();
        printMaze(startState);
        System.out.println("The goal is:");
        printMaze(a.getRoot().getGoal());
        List<String> solution = a.getSolution();
        System.out.printf("The solution is in %d steps: ", a.getSolution().size());
        System.out.println(solution);


    }
    private static void printMaze(char[][] startState) {
        for (int y = 0; y <= 7; y++) {
            for (int x = 0; x <= 12; x++) {
                System.out.print(" " + startState[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }
//    public static Object printMatrix(char[][] matrix) {
//        for (int i = 0; i < matrix.length; i++)
//            // Loop through all elements of current row
//            for (int j = 0; j < matrix[i].length; j++)
//                System.out.print(matrix[i][j] + " ");
//
//        return null;
//    }

}

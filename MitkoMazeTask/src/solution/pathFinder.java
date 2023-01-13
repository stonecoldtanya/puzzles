package MitkoMazeTask.src.solution;
import java.util.Comparator;
import java.util.*;

import static MitkoMazeTask.src.solution.Node.manhattan;

public class pathFinder {
    private Node root;
    int time = 0;
    private boolean isFound = false;
    private List<String> solution;

    public pathFinder(char[][] startState) {
        this.root = new Node(startState);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    Comparator<? super Node> comparator = new Comparator<Node>() {
      public int compare(Node a,Node b) {
            return (a.getCost() + manhattan(a)) - (b.getCost() + manhattan(b));
        }
    };
//    private class comparator implements Comparator<Node>{			//comparator for manhattan heuristic and totalCost
//
//        public int compare(Node a,Node b) {
//            return (int) ((a.getCost() + manhattan(a)) - (b.getCost() + manhattan(b)));
//        }
//    }

    public void aStar() {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        Node current= new Node(root.getState());
        current.setCost(0);

        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node> (comparator);
        nodePriorityQueue.add(current);
        while (!nodePriorityQueue.isEmpty()) {
            //stateSets.add(getSpot(currentNode));
            Node currentNode = nodePriorityQueue.poll();
            if (stateSets.contains(getSpot(currentNode)))
                continue;
            stateSets.add(getSpot(currentNode));

            if (currentNode.getMCol() == currentNode.getGoalCol() && currentNode.getMRow() == currentNode.getGoalRow()){
                solved(currentNode);
                this.isFound = true;
                System.out.println(time);
                return;
            }

            List<Node> neighbours = currentNode.getNeighbours();
            for (Node neighbour: neighbours) {
               // Node child = new Node(neighbour.getState());
//                currentNode.addNeighbour(child);
//                child.setParent(currentNode);

//                neighbour.setCost(manhattan(neighbour));
                nodePriorityQueue.add(neighbour);

            }
            time += 1;
        }

    }

    public static String getSpot(Node current) {
        return "(" + current.getMRow() + ";" + current.getMCol() + ")";
    }

    public List<String> getSolution() {
        if (this.solution == null) {
            aStar();
        }
        return this.solution;
    }
    private void solved(Node solved) {
        List<String> solution = new ArrayList<>();

        Node current = solved;
        int counter = 0;
        while (current != null) {
            solution.add(0,getSpot(current));
            //counter++;
            current = current.getParent();
        }

        this.solution = solution;
    }

}

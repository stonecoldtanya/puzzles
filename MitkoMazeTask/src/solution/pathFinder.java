package MitkoMazeTask.src.solution;
import java.util.Comparator;
import java.util.*;

public class pathFinder {
    private Node root;
    int time = 0;
    private List<String> solution;
    private boolean isFound = false;
    public pathFinder(char[][] startState) {
        this.root = new Node(startState);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

//    Comparator<? super Node> comparator = new Comparator<Node>() {
//      public int compare(Node a,Node b) {
//            return (a.getCost() + manhattan(a)) - (b.getCost() + manhattan(b));
//        }
//    };

    Comparator<? super Node> comparator =Comparator.comparingDouble(p -> p.getTotalCost());

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
                return;
            }

            List<Node> neighbours = currentNode.getNeighbours();
            for (Node neighbour: neighbours) {
                neighbour.setTotalCost(neighbour.evaluate() + currentNode.getTotalCost());
                nodePriorityQueue.add(neighbour);

            }
            //time += 1;
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
        while (current != null) {
            solution.add(0,getSpot(current));
            current = current.getParent();
        }

        this.solution = solution;
    }

}

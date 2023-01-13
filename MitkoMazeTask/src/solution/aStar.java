package MitkoMazeTask.src.solution;

import java.util.*;

import static MitkoMazeTask.src.solution.Node.manhattan;

public class aStar {
    private List<String> solution;
    private Node root;
    int counter = 0;

    public aStar(char[][] startState) {
        this.root = new Node(startState);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getCount() {
        return counter;
    }

    Comparator<? super Node> comparator =new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if(o2 == null)return 1;
            if (o2 == null)return -1;

            return (int) ((o1.getCost() + manhattan(o1)) - (o2.getCost() + manhattan(o2)));
        }
    };
    public void solve() {
            Node startNode = new Node(getRoot().getState());

           // PriorityQueue<Node> party = new PriorityQueue<>();
            //party.add(state);
//            Set<Node> visited = new HashSet<>();
            while (startNode.getMCol() != startNode.getGoalCol() && startNode.getMRow() != startNode.getGoalRow()) {
                PriorityQueue<Node> party = new PriorityQueue<>(comparator);
                party.add(startNode);
                while (!party.isEmpty()) {
                    Node current = party.poll();

                    if (manhattan(current) == 0) {
                        solved(current);
                        return;
                    }

                    List<Node> children = current.getNeighbours();
                    for (Node child : children) {
                        child.setCost(0);
                        manhattan(child);
                        party.add(child);
                    }

                    solution.add(getSpot(party.poll()));
                    //party.clear();
                }
            }
            //System.out.println(state.getNeighbours());

    }

    public String getSpot(Node current) {
        char[][] map = current.getState();
        return "(" + current.getMRow() + ";" + current.getMCol() + ")";
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public List<String> getSolution() {
        if (this.solution == null) {
            solve();
        }
        return this.solution;
    }
    private void solved(Node solved) {
        List<String> solution = new ArrayList<>();

        Node current = solved;
        int counter = 0;
        while (current != null) {
            solution.add(getSpot(current));
            //counter++;
            current = current.getParent();
        }

        this.solution = solution;
    }


}

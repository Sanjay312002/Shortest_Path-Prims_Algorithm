import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GraphDraw extends JFrame {
    int width;
    int height;

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

    public GraphDraw() { // Constructor
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    public GraphDraw(String name) { // Construct with label
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    class Node {
        int x, y;
        String name;

        public Node(String myName, int myX, int myY) {
            x = myX;
            y = myY;
            name = myName;
        }
    }

    class edge {
        int i, j;

        public edge(int ii, int jj) {
            i = ii;
            j = jj;
        }
    }

    public void addNode(String name, int x, int y) {
        // add a node at pixel (x,y)
        nodes.add(new Node(name, x, y));
        this.repaint();
    }

    public void addEdge(int i, int j) {
        // add an edge between nodes i and j
        edges.add(new edge(i, j));
        this.repaint();
    }

    public void paint(Graphics g) { // draw the nodes and edges
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.black);
        for (edge e : edges) {
            g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
                    nodes.get(e.j).x, nodes.get(e.j).y);
        }

        for (Node n : nodes) {
            int nodeWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
            g.setColor(Color.white);
            g.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.black);
            g.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);

            g.drawString(n.name, n.x - f.stringWidth(n.name) / 2,
                    n.y + f.getHeight() / 2);
        }
    }
}

class MinimumSpanningTree {
    private static final int countOfVertices = 8;

    int findMinKeyVertex(int keys[], Boolean setOfMST[]) {
        int minimum_index = -1;
        int minimum_value = Integer.MAX_VALUE;
        for (int vertex = 0; vertex < countOfVertices; vertex++)
            if (setOfMST[vertex] == false && keys[vertex] < minimum_value) {
                minimum_value = keys[vertex];
                minimum_index = vertex;
            }

        return minimum_index;
    }

    void showMinimumSpanningTree(int mstArray[], int graphArray[][]) {
        try {
            Thread.sleep(2000);
            GraphDraw frame = new GraphDraw("MST Graph");//

            frame.setSize(500, 1000);

            frame.setVisible(true);

            frame.addNode("Avanashi", 120, 100);
            frame.addNode("Tiruppur", 170, 150);
            frame.addNode("Palladam", 120, 230);
            frame.addNode("Kangayam", 300, 250);
            frame.addNode("Kundadam", 230, 340);
            frame.addNode("Dharapuram", 330, 400);
            frame.addNode("Udumalpet", 80, 550);
            frame.addNode("Madathukulam", 230, 600);
            int weight = 0;
            for (int j = 1; j < countOfVertices; j++) {
                weight = weight + graphArray[mstArray[j]][j];
                frame.addEdge(mstArray[j], j);
                Thread.sleep(1000);
                // frame.addEdge(mstArray[j], j, Color.black);

            }
            frame.addNode("  Minimum spanning tree weight : " + weight + "  ", 220, 700);

        } catch (InterruptedException e) {
        } //

    }

    void designMST(int graphArray[][]) {
        int mstArray[] = new int[countOfVertices];
        int keys[] = new int[countOfVertices];
        Boolean setOfMST[] = new Boolean[countOfVertices];
        for (int j = 0; j < countOfVertices; j++) {
            keys[j] = Integer.MAX_VALUE;
            setOfMST[j] = false;
        }
        keys[0] = 0;
        mstArray[0] = -1;
        for (int i = 0; i < countOfVertices - 1; i++) {
            int edge = findMinKeyVertex(keys, setOfMST);
            setOfMST[edge] = true;
            for (int vertex = 0; vertex < countOfVertices; vertex++)
                if (graphArray[edge][vertex] != 0 && setOfMST[vertex] == false
                        && graphArray[edge][vertex] < keys[vertex]) {
                    mstArray[vertex] = edge;
                    keys[vertex] = graphArray[edge][vertex];
                }
        }
        showMinimumSpanningTree(mstArray, graphArray);
    }
}

class Matrix {

    public void displayAM() {
        JFrame f = new JFrame();
        JLabel l = new JLabel("matrix");
        JTextArea area = new JTextArea(
                "     a  ,  b  ,  c  ,  d  ,  e  ,  f  ,  g  ,  h\n" +
                        " a | 0   , 11 , 28 , 56 ,  0  , 0  , 0  , 0 |\n" +
                        " b | 11 ,  0 , 17 , 29 , 36 ,  0 ,  0 ,  0 |\n" +
                        " c | 28 , 17 ,  0 , 32 , 32 ,  0 , 49, 55|\n" +
                        " d | 58 , 29 , 32,  0 , 26 , 33 ,  0 ,  0 |\n" +
                        " e |  0  , 36 , 32, 26 ,  0 , 17 , 44, 51|\n" +
                        "  f |  0  ,  0 ,  0  , 33 , 17 ,  0 , 39, 31|\n" +
                        " g |  0  ,  0 , 49 ,  0  , 44 , 39 ,  0, 15|\n" +
                        " h |  0  ,  0 , 55 ,  0 , 51 , 31 , 15,  0 |");
        area.setBounds(100, 100, 250, 250);
        l.setBounds(100, 30, 150, 100);
        f.add(l);
        f.add(area);

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class testGraphDraw {
    // Here is some example syntax for the GraphDraw class
    public void generate() {
        GraphDraw frame = new GraphDraw("Graph");

        frame.setSize(500, 1000);

        frame.setVisible(true);
        frame.addNode("Avanashi", 120, 100);
        frame.addNode("Tiruppur", 170, 150);
        frame.addNode("Palladam", 120, 230);
        frame.addNode("Kangayam", 300, 250);
        frame.addNode("Kundadam", 230, 340);
        frame.addNode("Dharapuram", 330, 400);
        frame.addNode("Udumalpet", 80, 550);
        frame.addNode("Madathukulam", 230, 600);
        frame.addEdge(0, 1);
        frame.addEdge(0, 2);
        frame.addEdge(0, 3);
        // frame.addEdge(1, 0);
        frame.addEdge(1, 2);
        frame.addEdge(1, 4);
        frame.addEdge(1, 3);
        frame.addEdge(2, 3);
        frame.addEdge(2, 4);
        frame.addEdge(2, 6);
        frame.addEdge(2, 7);
        frame.addEdge(3, 4);
        frame.addEdge(3, 5);
        frame.addEdge(4, 5);
        frame.addEdge(4, 6);
        frame.addEdge(4, 7);
        frame.addEdge(5, 6);
        frame.addEdge(5, 7);
        frame.addEdge(6, 7);
        MinimumSpanningTree mst = new MinimumSpanningTree();
        int graphArray[][] = new int[][] { { 0, 11, 28, 56, 0, 0, 0, 0 },
                { 11, 0, 17, 29, 36, 0, 0, 0 },
                { 28, 17, 0, 32, 32, 0, 49, 55 },
                { 58, 29, 32, 0, 26, 33, 0, 0 },
                { 0, 36, 32, 26, 0, 17, 44, 51 },
                { 0, 0, 0, 33, 17, 0, 39, 31 },
                { 0, 0, 49, 0, 44, 39, 0, 15 },
                { 0, 0, 55, 0, 51, 31, 15, 0 } };
        Matrix am = new Matrix();
        am.displayAM();
        mst.designMST(graphArray);
    }

}

class Main {
    public static void main(String[] args) {
        testGraphDraw tgd = new testGraphDraw();
        tgd.generate();
    }
}
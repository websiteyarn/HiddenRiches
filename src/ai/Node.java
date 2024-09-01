package ai;

import java.awt.Color;


public class Node {
    Node parent;
    public int col;
    public int row;
    int totalcost;
    int cost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;
    
    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}

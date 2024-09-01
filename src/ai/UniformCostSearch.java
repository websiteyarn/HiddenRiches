package ai;

import java.util.ArrayList;
import main.GamePanel;

public class UniformCostSearch {
    
    GamePanel gamePanel;
    // NODE
    Node[][] node;
    Node startNode, goalNode, currentNode;
    public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> checkedList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    
    // others
    boolean goalReached = false;
    int tileMap[][] = new int[50][50];
    
    public UniformCostSearch(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        instantiateNodes();
    }
    
    public void instantiateNodes(){
        node = new Node[gamePanel.WORLD_COLUMN][gamePanel.WORLD_ROW];
        // place nodes
        int col = 0;
        int row = 0;
        
        while(col < gamePanel.WORLD_COLUMN && row < gamePanel.WORLD_ROW){
            // creates node for every tile in the map
            node[col][row] = new Node(col,row);
            
            col++;
            if(col == gamePanel.WORLD_COLUMN){
                row++;
                col = 0;
            }
        }        
    }
    
    public void resetNodes(){
        int col = 0;
        int row = 0;
        
        while(col < gamePanel.WORLD_COLUMN && row < gamePanel.WORLD_ROW){
            // resets open, checked, and solid nodes
            // for every time the player moves
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            
            col++;
            if(col == gamePanel.WORLD_COLUMN){
                row++;
                col = 0;
            }
            
            openList.clear();
            checkedList.clear();
            pathList.clear();
            goalReached = false;
        }      
    }
    
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();
        
        // set start at goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];        
        
        int col = 0;
        int row = 0;
        
        // sets solid node
        while(col < gamePanel.WORLD_COLUMN && row < gamePanel.WORLD_ROW){
            // solid tiles
            int tileNum = gamePanel.tileManager.mapTileNum[col][row];
            if(gamePanel.tileManager.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }
            // set cost
            getCost(node[col][row]);
            
            col++;
            if(col == gamePanel.WORLD_COLUMN){
                row++;
                col = 0;
            }
        }
        
        // solid objects
        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] != null){
                int objCol = gamePanel.obj[i].worldXLocation/gamePanel.TILE_SIZE;
                int objRow = gamePanel.obj[i].worldYLocation/gamePanel.TILE_SIZE;

                if(gamePanel.obj[i].collision == true){
                    node[objCol][objRow].solid = true;
                }           
            }
        }
    }
    
    public void getCost(Node node){
        int x = Math.abs(node.col - startNode.col);
        int y = Math.abs(node.row - startNode.row);
        
        node.cost = x + y;        
    }
    
    public boolean UniformCostSearch(){
        startNode.totalcost = 0;
        openList.add(startNode); 
        
        while(goalReached == false){
            if(openList.size() == 0)
                return false;
            
            // find the best node in openList
            int bestNodeIndex = 0;
            int bestNodeTCost = openList.get(0).totalcost;        

            // evaluates frontier
            for(int i=0; i<openList.size(); i++){
                if(openList.get(i).totalcost < bestNodeTCost){
                    bestNodeIndex = i;
                    bestNodeTCost = openList.get(i).totalcost;
                }
            }
            
            currentNode = openList.get(bestNodeIndex);
            openList.remove(currentNode);
            
            // goal test
            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
                return true;
            }
            
            int col = currentNode.col;
            int row = currentNode.row;
            
            currentNode.checked = true;
            checkedList.add(currentNode);
            
            // open the up node
            // this node is one node above the current node
            if(row-1 >= 0){
                openNode(node[col][row-1]);
            }
            
            // open the left node
            if(col-1 >= 0){
                openNode(node[col-1][row]);
            }

            // open the down node
            if(row+1 < gamePanel.WORLD_ROW){
                openNode(node[col][row+1]);
            }
            
            // open the right node
            if(col+1 < gamePanel.WORLD_COLUMN){
                openNode(node[col+1][row]);
            }
        }
        return goalReached;
    }
    
    public void openNode(Node node){
        if(node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            openList.add(node);
            node.parent = currentNode;
            node.totalcost = currentNode.totalcost + node.cost;
        } 
        else if(node.open == true && node.checked == false && node.solid == false){
            int totcost1 = node.totalcost;
            
            int totcost2 = currentNode.totalcost + node.cost;
            
            if(totcost2 < totcost1){
                node.totalcost = totcost2;
                node.parent = currentNode;
            }
        }
    }
        
    public void trackPath(){
        // backtrack and draw best path
        Node current = goalNode;
        int totalNode=0;
        
        while(current != startNode){
            totalNode++;
            pathList.add(0, current);
            current = current.parent;
        }
//        System.out.println("Total node UCS: "+totalNode);
    }
}

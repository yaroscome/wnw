package models;

import java.util.HashMap;

/**
 *
 * @author yaros
 */
public class TreeNode {
    protected String type;
    protected String disposalReason;
    protected HashMap<String, TreeNode> subTree;
    
    
    
    
    //Constructor for creating leaf nodes. It will keep null on all fields except for 
	//disposalReason (will be empty).
    public TreeNode(String spoiledReason) {
        this.disposalReason = spoiledReason;
        
    }
    
    //Creating intermediate node, disposalReason will be null and not null on other fields
    public TreeNode(String type, HashMap<String, TreeNode> subTree) {
        this.type = type;
        this.disposalReason = null;
        this.subTree = subTree;
    }

    public String getDisposalReason() {
        return disposalReason;
    }

    public void setSpoiledReason(String spoiledReason) {
        this.disposalReason = spoiledReason;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, TreeNode> getSubTree() {
        return subTree;
    }

    public void setSubTree(HashMap<String, TreeNode> subTree) {
        this.subTree = subTree;
    }
    
    
    
    
}

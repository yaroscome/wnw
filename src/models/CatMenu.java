package models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author yaros
 */
public class CatMenu {
    private TreeNode root;
    private List path;
    private List disposalReason;
    
    
    public CatMenu() {
        root = new TreeNode("foodCategory", new HashMap<String, TreeNode>());
        path = new ArrayList<String>();
        disposalReason = new ArrayList<String>(Arrays.asList("spoiled", "overpreparation", "expired"));
    }

    public TreeNode getRoot() {
        return root;
    }

    public List getPath() {
        return path;
    }
    
    public void resetPath() {
        path = new ArrayList<String>();
    }

    public List getDisposalReason() {
        return disposalReason;
    }
    
    public TreeNode loadCustomCatMenu(String fileName) {
        return null;
    }
    
    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(root);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
    
    public void exportMetadata(String filename) {
       try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(path);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("IOException");
        } 
    }
    
    
    
    
    
    
    
    public static void initDefaultCatMenu(TreeNode root) {
        TreeNode currentNode = null;
        HashMap subTree = root.getSubTree();
        
        subTree.put("Meat", new TreeNode("foodType", new HashMap<String, TreeNode>()));
        subTree.put("Fish", new TreeNode("foodType", new HashMap<String, TreeNode>()));
        
        currentNode = (TreeNode) subTree.get("Meat");
        
        subTree = currentNode.getSubTree();
        subTree.put("Beef", new TreeNode(""));        
        subTree.put("Lamb", new TreeNode(""));
        subTree.put("Chicken", new TreeNode(""));
        
        
        subTree = root.getSubTree();
        currentNode = (TreeNode) subTree.get("Fish");
        
        subTree = currentNode.getSubTree();
        
        subTree.put("Tuna", new TreeNode(""));        
        subTree.put("Mackerel", new TreeNode(""));
        
    }
    
    
    
    
}

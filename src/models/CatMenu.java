package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void loadCustomCatMenu(String filename) {
        List list = new ArrayList<String>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
			String line = bufferedReader.readLine();
			while(line != null) {
				
				line = line.replace("[", "");
				line = line.replace("{", "");
				line = line.replace("}", "");
				line = line.replace("]", "");
				line = line.replace(" ", "");
				
				TreeNode currentNode = root;
				StringTokenizer tokenizer = new StringTokenizer(line, ":,");
				
				while(tokenizer.hasMoreElements()) {
					String type = tokenizer.nextToken(); 
					String value = tokenizer.nextToken();
					if(tokenizer.countTokens() == 0) {
						currentNode.getSubTree().put(value, new TreeNode(""));
					} else {
						ArrayList<String> keys = new ArrayList<String>(currentNode.getSubTree().keySet());
						boolean notFound = true;
						for(String k: keys) {
							if(k.equals(value)) {
								currentNode = currentNode.getSubTree().get(k);
								notFound = false;
								break;
							}
						}
						
						if(notFound) {
							currentNode.getSubTree().put(value, new TreeNode(type, new HashMap<String, TreeNode>()));
							currentNode = currentNode.getSubTree().get(value);
						}
						
					}
					
					
					
					
					
				}
				
				line = bufferedReader.readLine();
			}
            
        }catch (FileNotFoundException ex) {
            System.out.println("File not found");
            Logger.getLogger(CatMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex) {
			Logger.getLogger(CatMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		System.out.println("done.");
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

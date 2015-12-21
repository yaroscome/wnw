package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.TreeNode;


/**
 *
 * @author yaros
 */
public class WinnowChallenge {

    private static void initAll() {
        
        
        
//        root.put("meat", new TreeNode());
//        root.put("fish", new TreeNode());
//
//        Map foodCategory = (Map) root.get("meat"); //level 1
//
//        foodCategory.put("Chicken", null);
//        foodCategory.put("Lamb", null);
//        foodCategory.put("Pork", null);
//
//        foodCategory = (Map) root.get("fish");
//        foodCategory.put("Tuna", null);
//        foodCategory.put("Mackerel", null);
//
//        foodCategory = null;
        
    }
    
    private static void buildTree(Map root) {
        List<String> keys = new ArrayList();
        keys.addAll(root.keySet());
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        
        Map currentNode = root;
        System.out.println("\n\n\n");
        System.out.println("root: FoodGroup");
        
        boolean keepRunning = true;
        
        while(keepRunning) {
            System.out.println("0. Edit current node");
            int i = 1;
            for(String k: keys) {
                System.out.println(i + ". " + k);
                ++i;
            }
            
            try {
                String line = keyboard.readLine();
                int parsedInt = Integer.parseInt(line);
                if(parsedInt == 0){
                    
                }
                
                
            } catch(IOException e) {
                
            } 
            
            keepRunning = false;
        }
        
        
    }
    
    private static void loadTree() {
        
    }
    
    
    public static void mains(String[] args){
        
        Map root = null; 
        
        
        
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonTree = gson.toJson(root);
        
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        

       
        
        System.out.println("JSON: " + jsonTree);
        
        boolean keepRunning = true;
//        boolean keepRunning = false;
        while(keepRunning) {
            
            try {
                
                System.out.println("1. Show default CatMenu tree");
                System.out.println("2. Build tree");
                System.out.println("3. Load saved tree");
                System.out.println("Press any other key to exit");
                String line = keyboard.readLine();
                
                switch(line) {
                    
                    case "1":
                        System.out.println("CatMenu: " + jsonTree);
                        break;
                        
                    case "2": 
                        buildTree(root);
                        break;
                        
                    case "3":
                        loadTree();
                        break;
                        
                    default: 
                        keepRunning = false;
                }
            } catch (IOException ex) {
                break;
            }
        }  
    }
}

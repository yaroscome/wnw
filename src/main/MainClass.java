package main;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CatMenu;
import models.TreeNode;

/**
 *
 * @author yaros
 */
public class MainClass {
    
    
    public static void main(String[] args) {
        
        CatMenu catMenu = new CatMenu();
        CatMenu.initDefaultCatMenu(catMenu.getRoot());
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
         
       
        Gson gson = new Gson(); //GsonBuilder().serializeNulls().create();
        
        
        
        boolean keepRunning = true;
        do {
			System.out.print("\n\n\n"); 
			
            System.out.println("0 - To stop");
            System.out.println("1 - Import cutom tree");
            System.out.println("2 - Export metadata (select path)");
            try {
                String line = keyboard.readLine();
                int choice = Integer.parseInt(line);
                
                switch(choice) {
                    case 1: importCustomTree(catMenu); break;
                    case 2: selectPath(catMenu); break;
                    default: keepRunning = false;
                }
                
                
            } catch (IOException ex) { 
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            String jsonTree = gson.toJson(catMenu.getRoot());
			System.out.println("JSON: " + jsonTree);
			
			System.out.println("Exporting to file: rootexp.json");
			catMenu.save("rootexp.json");
            
        } while(keepRunning);
        
		
        
    }
    
    
    private static void importCustomTree(CatMenu catMenu) {
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("filename: ");
            String filename = keyboard.readLine();
            catMenu.loadCustomCatMenu(filename);
            
        } catch(IOException e) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private static void selectPath(CatMenu catMenu) {
        System.out.println("Select path: ");
        
        int levelCounter = 0;
        List<String> path = catMenu.getPath();
        List<String> disposalReasonList = catMenu.getDisposalReason();
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        TreeNode currentNode = catMenu.getRoot();
        
              
        boolean keepRunning = true;
        do {
            
            if(currentNode.getType() != null) {
                System.out.println("\n\n\n");
                System.out.println("Level: " + currentNode.getType());
                System.out.println("Select: ");
                System.out.println("0 - Cancel");
                List<String> keys = new ArrayList();
                keys.addAll(currentNode.getSubTree().keySet());
                int i = 1;
                for(String k: keys) {
                    System.out.println(i + " - " + k);
                    ++i;
                } 
                
                try {
                    String line = keyboard.readLine();
                    int choice = Integer.parseInt(line);

                    if(choice >= 1 && choice <= i) {
                        path.add("{ " +currentNode.getType() + ":" + keys.get(choice - 1) + " }");
                        currentNode = currentNode.getSubTree().get(keys.get(choice - 1));
                        if(currentNode.getDisposalReason() != null && currentNode.getDisposalReason().isEmpty()) {
                            int j = 1;
                            for(String reason: disposalReasonList) {
                                System.out.println(j + " " + reason);
                                ++j;
                            }
                            String reasonLine = keyboard.readLine();
                            int reasonChoice = Integer.parseInt(reasonLine);
                            if(reasonChoice >= 1 && reasonChoice <= i) {
                                String reason = disposalReasonList.get(reasonChoice - 1);
                                path.add("{ disposalReason: " + reason + " }");
                                keepRunning = false;
                            } else {
                                keepRunning = false;
                                path.clear();
                            }
                        }
                    } else {
                        keepRunning = false;
                    }                
                } catch (IOException ex) { 
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }while(keepRunning);
        if(!path.isEmpty()) {
            catMenu.exportMetadata("metadata");
            path.clear();
        }
        
    }
    
    
}

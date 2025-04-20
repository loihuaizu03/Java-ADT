
package dao;

import ADT.ArrayList;
import ADT.ListInterface;
import ADT.SortedArrayList;
import ADT.SortedListInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Chow Xing Shao
 */
public class CommonDAO<T> {
    
    private String fileName;

    public CommonDAO(String fileName) {
        this.fileName = fileName;
    }
    
    public void saveToFile(ListInterface<T> list) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(list);
            //System.out.println("save successful");
            ooStream.close();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } 
        catch (Exception e) {
            System.out.println("\nCannot save to file");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ListInterface<T> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<T> list = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList<T>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return list;
        }
    }
    
    
}
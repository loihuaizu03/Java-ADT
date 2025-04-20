
package boundary;

import ADT.ListInterface;
import control.ProgrammeMaintenance;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author sky
 */
public class ProgrammeUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuUI = new MenuUI();
    
    public int getMenuChoice() {
        menuUI.displayProgrammeMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public void displayAddingNewProgramme(){
        System.out.println("[Adding New Programme]\n");
    }
    
    public void displayProgramme(){
        System.out.println("[Programmes Available]\n");
    }
    
    public String inputProgrammeCode(){
        System.out.print("Programme Code -> ");
        String name = scanner.nextLine();
        return name;
    }
    
    public String inputProgrammeName(){
        System.out.print("Programme Name -> ");
        String name = scanner.nextLine();
        return name;
    }
    
    public void displayFaculty(){
        System.out.println("Faculty Available\n\n1. FAFB\n2. FOCS\n3. FCCI\n4. FOAS");
    }
    
     public int inputFaculty(){
        displayFaculty();
        System.out.print("\nFaculty -> ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
    
    public int chooseProgramme(){
        System.out.print("\n\nChoose Programme -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public void displayEmpty(){
        System.out.println("Programme List Is Currently Empty...");
    }
    
    public void displayheader(){
        System.out.printf("%-5s%-20s%-20s\n", "", "Programme Code", "Programme Name");
        System.out.println(MessageUI.GetDivider('-', 70 ));
    }
}

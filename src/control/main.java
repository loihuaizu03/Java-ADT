
package control;

import boundary.MenuUI;
import dao.Initializer;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Chow Xing Shao
 */
public class main {
    
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        MenuUI main = new MenuUI();
        
        //initialize initial value
        System.out.print("Initialize Initial Value For Present Purposes? (1/0) -> ");
        int initialize = scanner.nextInt();
        if(initialize == 1){
            Initializer initializer = new Initializer();
        }
        
        boolean exit = false;
        
        int choice;
        do {             
            main.displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    exit = true;
                    break;
                    
                //programme
                case 1:
                    ProgrammeMaintenance.main(args);
                    break;
                
                //Student Registration Management
                case 2:
                    StudentMaintenance.main(args);
                    break;
                    
                //Course Management 
                case 3:
                    CourseMaintenance.main(args);
                    break;

                //Tutorial Group Management    
                case 4:
                    TutorialGroupMaintenance.main(args);
                    break;
                    
                //Tutor Management    
                case 5:
                    TutorMaintenance.main(args);
                    break;

                //Assignment Team Management
                case 6:
                    AssignmentTeamMainteinance.main(args);
                    break;

                

                default:
                    throw new AssertionError();
            }
        } while (!exit);
    }
}


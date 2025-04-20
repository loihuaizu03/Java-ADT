
package boundary;

import Entity.Faculty;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Chow Xing Shao
 */
public class FacultyUI {
    Scanner scanner = new Scanner(System.in);
    
    public void displayHeader(){
        System.out.printf("%-5s%-15s%s\n", "", "Faculty Code", "Faculty Name");
        System.out.println(MessageUI.GetDivider('-', 60));
    }
    
    public int chooseFaculty(){
        System.out.print("Choose Faculty To View Courses -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public void displayViewingCourse(Faculty faculty){
        System.out.println("[Viewing Courses Taken By Faculty " + faculty.getFacultyCode()+" ]\n");
    }
}

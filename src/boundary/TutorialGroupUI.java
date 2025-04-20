
package boundary;

import Entity.Programme;
import Entity.Student;
import Entity.TutorialGroup;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Teo Hua Ying
 */
public class TutorialGroupUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuUI = new MenuUI();

    public int getMenuChoice() {
        menuUI.displayTutorialGroupMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public int chooseProgrammeToAdd(){
        System.out.print("Choose Programme To Add Tutorial Group -> ");
        int maxStudent = scanner.nextInt();
        return maxStudent;
    }
    
    public int chooseProgrammeToRemove(){
        System.out.print("Choose Programme To Remove Tutorial Group -> ");
        int maxStudent = scanner.nextInt();
        return maxStudent;
    }
    
    public int chooseGroupToAdd(){
        System.out.print("Choose Tutorial Group To Add Students -> ");
        int tutorialGroup = scanner.nextInt();
        return tutorialGroup;
    }
    
    public int chooseGroupToRemove(){
        System.out.print("Choose Tutorial Group To Remove Students -> ");
        int tutorialGroup = scanner.nextInt();
        return tutorialGroup;
    }
    
    public int chooseGroupToBeRemove(){
        System.out.print("\nChoose Tutorial Group To Be Remove -> ");
        int tutorialGroup = scanner.nextInt();
        return tutorialGroup;
    }
    
    public int chooseTutorialGroup(){
        System.out.print("Choose Tutorial -> ");
        int tutorialGroup = scanner.nextInt();
        return tutorialGroup;
    }
    
    public int chooseAddStudent(){
        System.out.print("Choose Student Add To Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
    
     public int chooseRemoveStudent(){
        System.out.print("Choose Student Remove From Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
     
    public int chooseGroupToTransferred(){
        System.out.print("\nChoose Tutorial Group To Transferred Student -> ");
        int index = scanner.nextInt();
        return index;
    }
     
    public int chooseStudentToChange(){
        System.out.print("\nChoose Student To Change Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseGroupToChange(){
        System.out.print("\nChoose Which Tutorial Group You Wish To Transferred To -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseGroupToMerge(){
        System.out.print("\nChoose Tutorial Group To Merge -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int inputMaxStudent(){
        System.out.print("Maximum Students -> ");
        int maxStudent = scanner.nextInt();
        return maxStudent;
    }
    
    public void displayEmpty(){
        System.out.println("Tutorial Group List Is Currently Empty...");
    }
    
    public void displayHeader(){
        System.out.printf("%-4s%-20s%s\n", "", "Tutorial Group", "Number Of Students/Maximum Students");
        System.out.println(MessageUI.GetDivider('-', 60));
    }
    
    public void displayAddingTo(TutorialGroup tutorialGroup){
        System.out.println("[Adding Students To " + tutorialGroup.getTutorialGroup() +"]\n");
    }
    
    public void displayRemovingFrom(TutorialGroup tutorialGroup){
        System.out.println("[Removing Students From " + tutorialGroup.getTutorialGroup() +"]\n");
    }
    
     public void displayRemovingFrom(Programme programme){
        System.out.println("[Removing Tutorial Group From " + programme.getProgrammeCode() + "]\n");
    }
    
    public void displayAddingTo(Programme programme){
        System.out.println("[Adding Tutorial Group To " + programme.getProgrammeCode() + "]\n");
    }
    
    public void displayChangeGroup(){
        System.out.println("[Change Tutorial Group]\n");
    }
    
    public void displayChanging(Student student){
        System.out.println("[Changing Tutorial Group For " + student.getId()+ "]\n");
    }
    
    public void displayMergeGroup(){
        System.out.println("[Merge Tutorial Group]\n");
    }
}

package boundary;

import Entity.AssignmentTeam;
import Entity.TutorialGroup;
import boundary.MenuUI;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Varsyathini
 */
public class AssignmentTeamUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuui = new MenuUI();

    public int getMenuChoice() {
        menuui.displayAssignmenTeamMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public void displayheader(){
        System.out.printf("%-2s%-20s%s\n", "", "Team Name", "Number Of Students/Maximum Students");
        System.out.println(MessageUI.GetDivider('-', 50));
    }
    
    public void displayTeamDetail(AssignmentTeam assignmentTeam){
        System.out.println("[Assignment Team Details]\n");
        System.out.printf("%s%-15s\n", "1." + "Team Name", " -> " + assignmentTeam.getTeamName());
        System.out.printf("%s%-15s\n", "2." + "Maximum Number Of Student", " -> " + assignmentTeam.getMaxStudents());
    }
    
    public void displayEmpty(){
        System.out.println("Assignment Team List Is Currently Empty...");
    }
    
    
    public void displayAddingTeam(){
        System.out.println("[Adding Assignment Teams]\n");
    }
    
    public void displayRemovingTeam(){
        System.out.println("[Removing Assignment Teams]\n");
    }
    
    public void displayAddingTeamTo(TutorialGroup tutorialGroup){
        MessageUI.printLine();
        System.out.println("[Adding Assignment Teams To "+ tutorialGroup.getTutorialGroup() +" ]\n");
    }
    
    public void displayTeamsUnderGroup(TutorialGroup tutorialgroup){
        
        System.out.println("[Assignment Team Under "+ tutorialgroup.getTutorialGroup() +" ]\n");
    }
    
    
    public void displayRemovingTeamFrom(TutorialGroup tutorialGroup){
        MessageUI.printLine();
        System.out.println("[Removing Assignment Teams From "+ tutorialGroup.getTutorialGroup() +" ]\n");
    }
    
    public void displayAddingStudentTo(AssignmentTeam assignmentTeam){
        //MessageUI.printLine();
        System.out.println("[Adding Students To"+ assignmentTeam.getTeamName()+" ]\n");
    }
    
    public void displayRemovingStudentFrom(AssignmentTeam assignmentTeam){
        //MessageUI.printLine();
        System.out.println("[Removing Students From "+ assignmentTeam.getTeamName()+" ]\n");
    }
    
    public void displayMergeGroup(){
        System.out.println("[Merge Assignment Team]\n");
    }
    
    public int chooseGroupToGenerateTeamReport(){
        System.out.print("Choose Tutorial Group To Generate Assignment Team Report -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTeamToRemove(){
        System.out.print("Choose Assignment Team To Be Removed -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTeamToMerge(){
        System.out.print("Choose Assignment Team To Merge -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int inputProgramme(){
        System.out.print("Choose Programme To View Tutorial Group -> ");
        int programme = scanner.nextInt();
        return programme;
    }
    
    public String inputAssignmentTeamName(){
        System.out.print("Assignment Team Name -> ");
        String name = scanner.nextLine();
        return name;
    }
    
    public int inputMaximum(){
        System.out.print("Maximum Students per Group -> ");
        int max = scanner.nextInt();
        return max;
    }
    
    public int inputTutorialGroup(){
        System.out.print("Choose Tutorial Group To Add Assignment Team -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseToView(){
        System.out.print("Choose Assignment Team To View Students -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTutorialGroup(){
        System.out.print("Choose Tutorial Group To View Assignment Teams -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseAssignmentTeam(){
        System.out.print("Choose Assignment Team To Amend Details -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseDetail(){
        System.out.print("Choose Detail You Wish To Amend -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseTeamAddStudent(){
        System.out.print("Choose Assignment Team To Add Students -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseTeamRemoveStudent(){
        System.out.print("Choose Assignment Team To Remove Students -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseStudentToAdd(){
        System.out.print("\nChoose Students To Add -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseStudentToRemove(){
        System.out.print("\nChoose Student To Add -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    
    public String changeTeamName(AssignmentTeam assignmentTeam){
        scanner.nextLine();     //clear buffer
        System.out.print(assignmentTeam.getTeamName()+ " -> ?");
        String name = scanner.nextLine();
        return name;
    }
    
    public int changeMaxStudent(AssignmentTeam assignmentTeam){
        scanner.nextLine();     //clear buffer
        System.out.print(assignmentTeam.getMaxStudents()+ " -> ?");
        int max = scanner.nextInt();
        return max;
    }

    
    public AssignmentTeam getTeamDetail(){
        scanner.nextLine();
        //get team name
        String name = inputAssignmentTeamName();
        
        //get maximum per group
        int maxStudent = inputMaximum();
        
        AssignmentTeam assignmentTeam = new AssignmentTeam(maxStudent, name);
        
        return assignmentTeam;
    }
    
    
}


package boundary;

import Entity.Course;
import Entity.Tutor;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Soh Kee Yhang
 */
public class TutorUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuUI = new MenuUI();
    
    String typeMenu[] = {"TUTORIAL", "PRACTICAL", "LECTURE"};
    
    public int getMenuChoice() {
        MessageUI.printLine();
        menuUI.displayTutorMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public void displayHeader(){
        System.out.printf("%-8s%-25s%-5s%-9s%-17s%-20s%-30s%-10s%-10s\n", "", "Name", "Age", "Gender", "Phone Number", "IC", "Email", 
                        "Tutor ID", "Type");
        System.out.println(MessageUI.GetDivider('-', 130));
    }
    
    public void displayEmpty(){
        System.out.println("Tutor List Is Currently Empty...\n");
    }
    
    public void displayFilter(){
        System.out.println("Filter Tutor In Alphabetically Order\n");
    }
    
    public void displayAssignTutor(Course course){
        MessageUI.printLine();
        System.out.println("[Assignning Tutors To " + course.getCourseCode() + " ]\n");
    }
    
    public void displayAssignGroup(Tutor tutor){
        MessageUI.printLine();
        System.out.println("[Assignning Tutors To " + tutor.getId() + " ]\n");
    }
    
    public void displayAddingTutor(){
        System.out.println("[Adding New Tutor]\n");
    }
    
    public void displayTutorFrom(Course course){
        System.out.println("[View Tutor From Course "+ course.getCourseCode()+"]\n");
    }
    
    public void displayCoursesFrom(Tutor tutor){
        System.out.println("[View Corses From Tutor "+ tutor.getId()+"]\n");
    }
    
    public void displaySearchCourse(){
        System.out.println("[Searching Course From Tutor]\n");
    }
    
    public void displaySearchTutor(){
        System.out.println("[Searching Tutor From Course]\n");
    }
    
    public void displaySearchCourseFrom(Tutor tutor){
        System.out.println("[Searching Course From Tutor "+ tutor.getId() +"]\n");
    }
    
    public void displaySearchTutorFrom(Course course){
        System.out.println("[Searching Tutor From Course "+ course.getCourseCode()+"]\n");
    }
    
    public void displaySearchingFor(String keyword){
        System.out.println("[Searching For "+  keyword +"]\n");
    }
    
    public int chooseTutor(){
        System.out.print("Choose Tutor -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTutorialGroup(){
        System.out.print("\nChoose Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTutorToAddGroup(){
        System.out.print("\nChoose Tutor To Add Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToAdd(){
        System.out.print("Choose Course To Add Tutor -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToAddToGroup(){
        System.out.print("Choose Course To Add Tutor To Tutorial Group -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToRemove(){
        System.out.print("Choose Course To Remove Tutor -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToView(){
        System.out.print("\nChoose Course To View Tutor -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseTutorToSearch(){
        System.out.print("Choose Tutor To Search For Course -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToSearch(){
        System.out.print("Choose Course To Search For Tutor -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public String inputKeyWords(){
        scanner.nextLine();
        System.out.print("Tutor ID To Search -> ");;
        String keyword = scanner.nextLine();
        return keyword;
    }
    
    public int inputType() {
        boolean valid = false;
        int type = 0;
        do {
            menuUI.displayMenu(typeMenu, "Course Type");
            type = scanner.nextInt();
            scanner.nextLine();     //clear buffer
            if (type >= 1 && type <= 3) {
                valid = true;
                break;
            } else {
                System.out.println("Choose Type In Between 1-3!");
                valid = false;
            }

        } while (!valid);
        return type;
    }
    
    public Tutor getTutorDetail(int id){
        PersonUI personUI = new PersonUI();
        String name = personUI.inputName();
        if(name == null){
            return null;
        }
        String phone = personUI.inputPhoneNumber();
        String ic = personUI.inputIC();
        String email = personUI.inputEmail();     
        int type = inputType();
        
        Tutor tutor = new Tutor(name, phone, ic, email, type, id);
        return tutor;
    }
}


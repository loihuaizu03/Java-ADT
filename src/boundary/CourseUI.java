
package boundary;

import Entity.Course;
import Entity.Faculty;
import Entity.Programme;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Chow Xing Shao
 */
public class CourseUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuUI = new MenuUI();

    String typeMenu[] = {"Main", "Elective", "Repeat", "Resit"};
    
    
    public int getMenuChoice() {
        menuUI.displayCourseMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public void displayEmpty(){
        System.out.println("Course List Is Currently Empty...");
    }
    
    public void displayHeader(){
        System.out.printf("%-5s%-15s%-40s%-15s%-10s%s\n","","Course Code", "Course Name", "Credit Hour", "Type", "Faculties/Programme Offered");
        System.out.println(MessageUI.GetDivider('-', 115));
    }
    
    public void displayCourseDetail(Course course){
        System.out.println("[Course Details]\n");
        System.out.printf("%s%-15s\n", "1." + "Course Code", " -> " + course.getCourseCode());
        System.out.printf("%s%-15s\n", "2." + "Course Name", " -> " + course.getCourseName());
        System.out.printf("%s%-15s\n", "3." + "Credit Hour", " -> " + course.getCreditHour());
        System.out.printf("%s%-15s\n", "4." + "Type", " -> " + course.getType());
        
    }
    
    public void displayCourseFromFaculty(Faculty faculty){
        System.out.println("[Courses From Faculty " + faculty.getFacultyCode()+"]\n");
    }
    
    public void displayCourseAvaiable(){
        System.out.println("[Courses Available]\n");
    }
    
    public void displayAddingCourse(){
        System.out.println("[Adding New Course]\n");
    }
    
    public void displayRemovingCourse(){
        System.out.println("[Removing Course]\n");
    }
    
    public void displayAddingToCourse(Course course){
        System.out.println("[Adding Programme To Course " + course.getCourseCode()+"]\n");
    }
    
    public void displayRemovingFromCourse(Course course){
        System.out.println("[Removing Programme From Course " + course.getCourseCode()+"]\n");
    }
    
    public void displayAddingTo(Programme programme){
        System.out.println("[Adding New Course To " + programme.getProgrammeCode() +"]\n");
    }
    
    public void displayRemoveFrom(Programme programme){
        System.out.println("[Removing Course From " + programme.getProgrammeCode() +"]\n");
    }
    
    public void displaySearching(){
        System.out.println("[Searching Course]\n");
    }
    
    public void displaySearchingFor(String keyword){
        System.out.println("[Searching For "+  keyword +"]\n");
    }
    
    public String changeCourseCode(Course course){
        scanner.nextLine();     //clear buffer
        System.out.print(course.getCourseCode() + " -> ?");
        String code = validateCourseCode();
        return code;
    }
    
    public String changeCourseName(Course course){
        scanner.nextLine();     //clear buffer
        System.out.print(course.getCourseName() + " -> ?");
        String name = scanner.nextLine();
        return name;
    }
    
    public int changeCreditHour(Course course){
        System.out.print(course.getCreditHour()+ " -> ?");
        int creditHour = scanner.nextInt();
        return creditHour;
    }
    
    public int changeType(Course course){
        System.out.print(course.getType()+ " -> ?");
        int type = inputType();
        return type;
    }
    
    public int chooseDetail(){
        System.out.print("\nChoose Detail You Wish To Amend -> ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseProgramme(){
        System.out.print("\nChoose Programme -> ");
        int index = scanner.nextInt();
        scanner.nextLine();     //clear buffer
        return index;
    }
    
    public int chooseCourse(){
        System.out.print("\nChoose Course -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToAdd(){
        System.out.print("Choose Course To Add Programme -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseCourseToRemove(){
        System.out.print("Choose Course To Remove Programme -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public String inputKeyWords(){
        System.out.print("Course Code To Search -> ");;
        String keyword = scanner.nextLine();
        return keyword;
    }
    
    //get course code 
    public String inputCourseCode(){
        boolean valid = false;
        String code = validateCourseCode();
        return code;
    }
    
    public String inputCourseName(){
        System.out.print("Course Name -> ");
        String name = scanner.nextLine();
        return name;
    }
    
    public int inputType(){
        boolean valid = false;
        int type = 0;
        do { 
            menuUI.displayMenu(typeMenu, "Course Type");
            type = scanner.nextInt();
            scanner.nextLine();     //clear buffer
            if(type >= 1 && type <=4){
                valid = true;
                break;
            }
            else{
                System.out.println("Choose Type In Between 1-4!");
                valid = false;
            }

            }while(!valid);
        return type;
    }
    
    public Course getCourseDetails(){
       
        //get course code
        MessageUI.zeroPrompt();
        String courseCode = inputCourseCode();
        if(courseCode.equals("0")){
            return null;
        }

        //get course name
        String courseName = inputCourseName();

        //get type
        int type = inputType();

        Course course = new Course(courseCode, courseName, type);
        
        return course;
    }
    
    //get course code with validation (8 Characters)
    public String validateCourseCode(){
        boolean valid = false;
        String code = null;
        do {            
            System.out.print("Course Code -> ");
            code = scanner.nextLine();
            if(code.length() == 8 || code.equals("0")){
                valid = true;
            }
            else{
                MessageUI.printLine();
                System.out.println("Course Code Should Be 8 Characters!");
            }
            
        } while (!valid);
        
        return code;
    }
    
    
    
    
    
    
    
}

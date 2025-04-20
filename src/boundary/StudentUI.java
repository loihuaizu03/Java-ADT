
package boundary;

import Entity.Course;
import Entity.Student;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Loi Huai Zu
 */
public class StudentUI {
    Scanner scanner = new Scanner(System.in);
    private MenuUI menuUI = new MenuUI();

    public int getMenuChoice() {
        menuUI.displayStudentMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public int getSubmenuChoice(){
        menuUI.displaySummaryStudent();
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public void displayHeader(){
        System.out.printf("%-8s%-25s%-5s%-9s%-17s%-20s%-30s%-8s\n","", "Name", "Age", "Gender", "Phone Number", "IC", "Email", 
                    "Student ID");
        System.out.println(MessageUI.GetDivider('-', 130));
    }
    
    public void displayStudentDetail(Student student){
        displayAmendingStudent(student.getId());
        System.out.printf("1.%-12s -> %s\n", "Name", student.getName());
        System.out.printf("2.%-12s -> %s\n", "IC", student.getIc());
        System.out.printf("3.%-12s -> %s\n", "Phone Number", student.getPhoneNum());
        System.out.printf("4.%-12s -> %s\n", "Email", student.getEmail());
        //System.out.printf("%s%-15s\n", "4." + "Programme Code", " -> " + student.getProgrammeCode());
    }
    
    public void displayCourseTaken(int id){
        System.out.println("Displaying Course Taken By Student " + id + "\n");
    }
    
    public void displayEmpty(){
        System.out.println("Student List Is Currently Empty...");
    }
    
    public void displaySearch(){
        System.out.println("[Student Registered Course]");
    }
    
    public void displayAdding(){
        System.out.println("[Adding New Student]\n");
    }
    
    public void displayRemoving(){
        System.out.println("[Removing Student]\n");
    }
    
    public void displayAddingCourse(){
        System.out.println("[Adding Course To Student]\n");
    }
    
    public void displayRemovingCourse(){
        System.out.println("[Removing Course To Student]\n");
    }
    
    public void displayAddingCourseTo(Student student){
        System.out.println("[Adding Course To Student "+ student.getId() + "]\n");
    }
    
    public void displayRemovingCourseFrom(Student student){
        System.out.println("[Removing Course From Student " + student.getId() +"]\n");
    }
    
    public void displayAmending(int id){
        System.out.println("[Amending Student]\n");
    }
    
    public void displayAmendingStudent(int id){
        System.out.println("[Amending Student " + id + "]\n");
    }
    
    public void displaySearchingForCourse(Course course){
        System.out.println("[Searching Student For Course " + course.getCourseCode() + "]\n");
    }
    
    public int inputKeyWords(){
        scanner.nextLine();
        System.out.print("Student ID To Search -> ");;
        int keyword = scanner.nextInt();
        return keyword;
    }
    
    public String changeName(Student student){
        System.out.print(student.getName()+ " -> ");
        scanner.nextLine();     //clear buffer
        String name = scanner.nextLine();
        return name;
    }
    
    public String changeIC(Student student){
        System.out.print(student.getIc()+ " -> ");
        scanner.nextLine();     //clear buffer
        String ic = scanner.nextLine();
        return ic;
    }
    
    public String changePhoneNum(Student student){
        System.out.print(student.getPhoneNum()+ " -> ");
        scanner.nextLine();     //clear buffer
        String phonenum = scanner.nextLine();
        return phonenum;
    }
    
    public String changeEmail(Student student){
        System.out.print(student.getEmail()+ " -> ");
        scanner.nextLine();     //clear buffer
        String email = scanner.nextLine();
        return email;
    }
    
    public int chooseStudentToAddCourse(){
        System.out.print("Choose Student To Add Course -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseStudentToRemoveCourse(){
        System.out.print("Choose Student To Remove Course -> ");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseStudentToRemove(){
        System.out.print("Choose Student To Remove ->");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseStudentToAmend(){
        System.out.print("Choose Student To Amend Details ->");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseStudent(){
        System.out.print("Choose Student ->");
        int index = scanner.nextInt();
        return index;
    }
    
    public int chooseStudentDetail(){
        System.out.print("Choose Student Detail You Wish To Amend -> ");
        int detail = scanner.nextInt();
        return detail;
    }
    
    public int chooseCourseToAdd(){
        System.out.print("Choose Course To Add To Student -> ");
        int detail = scanner.nextInt();
        return detail;
    }
    
    public int chooseCourseToRemove(){
        System.out.print("Choose Course To Remove From Student -> ");
        int detail = scanner.nextInt();
        return detail;
    }
    
    public int chooseCourseToSearch(){
        System.out.print("Choose Course To Search For Student -> ");
        int detail = scanner.nextInt();
        return detail;
    }
    
    public Student getStudentDetail(int id){
        PersonUI personUI = new PersonUI();
        String name = personUI.inputName();
        if(name == null){
            return null;
        }
        String phone = personUI.inputPhoneNumber();
        String ic = personUI.inputIC();
        String email = personUI.inputEmail();     
        Student student = new Student(name, phone, ic, email, id);
        return student;
    }
    
    public int searchStudentID() {
        System.out.print("Enter the Student ID you want to search -> ");
        int search = scanner.nextInt();
        return search;
    }
    
    
    public void displayFeesHeader() {
        System.out.printf("%-4s%-14s%-40s%-19s%-10s%-8s\n","","Course Code", "Course Name", "Credit Hour", "Type","(RM)Fees");
        System.out.println(MessageUI.GetDivider('-', 100));
    }   
    
    public void displayFees(int i, String code, String name, int credit, String type, double price) {
        System.out.printf("%1s%-2s%-15s%-45s%-15s%-10s%-3s%.2f\n", i,". ",code, name, credit, type,"RM ", price);
    }
    
    public int chooseSearchMethod() {
        MessageUI.printLine();
        menuUI.displayStudentSearch();
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseFilter() {
        MessageUI.printLine();
        menuUI.displayFilterMenu();
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseFilterCase1() {
        MessageUI.printLine();
        menuUI.displayFilterMenuCase1();
        int choice = scanner.nextInt();
        return choice;
    }
    
    public int chooseAge() {
        MessageUI.printLine();
        System.out.print("Student than below (Age)->");
        int choice = scanner.nextInt();
        return choice;
    }
    
    public void displayReportFeesHeader() {
        System.out.printf("%-4s%-35s%-25s%-39s%-20s\n","","Student Name", "Student Gender", "Total Course Taken", "(RM)Fees");
        System.out.println(MessageUI.GetDivider('-', 150));
    }       
    
    public void displayReportFees(int i, String name, char gender, int course, double price) {
        System.out.printf("%-2s%-2s%-45s%-25s%-29s%-3s%.2f\n", i,". ",name, gender, course, "RM ", price);
    }
    
    public void displayAlphabeticalOrder() {
        System.out.println("[List Students In Alphabetical Order]\n");
    }
    
    
}

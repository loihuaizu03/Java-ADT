
package boundary;

import utility.MessageUI;

/**
 *
 * @author Chow Xing Shao
 */
public class MenuUI {
    
    String header = MessageUI.GetDivider('=', 70);
    
    private String MainSystems[] = {
        "Programme Management",
        "Student Registration", 
        "Course Management",
        "Tutorial Group Management", 
        "Teaching Assignment Management",
        "Assignment Team Management",
    }; 
    
    private String Student_Menu[] = {
        "Add new students",
        "Remove a student",
        "Amend student details",
        "Search students for registered courses",
        "Add students to a few courses (main, elective, resit, repeat)",
        "Remove a student from a course (main, elective, resit, repeat)",
        "Calculate fee paid for registered courses",
        "Filters students for courses based on criteria",
        "Generate summary reports"
    };

    private String Course_Menu[] = {
        "Add a programme to courses",
        "Remove a programme from a course",
        "Add a new course to programmes",
        "Remove a course from a programme",
        "Search courses offered in a semester",
        "Amend course details for a programme",
        "List courses taken by different faculties",
        "List all courses for a programme",
        "Generate summary reports"
    };
    
    private String Programme_Menu[] = {
        "Create New Programme",
        "List All Programmes"
    };

    private String TutorialGroup_Menu[] = {
        "Add Tutorial Group To A Programme",
        "Remove A Tutorial Group From A Programme",
        "List all tutorial groups for a programme",
        "Adding Students To A Tutorial Group",
        "Remove A Student From A tutorial Group",
        "Change The Tutorial Group For A Student",
        "List All Students In A Tutorial Group And A Programme",
        "Merge Tutorial Group Based On Criteria",
        "Generate summary reports"
    };
    
    private String Tutor_Menu[] = {
        "Add New Tutor",
        "Assign Tutor To Courses",
        "Assign Tutorial Group To A Tutor",
        "Add Tutors To A Tutorial Group For A Course",
        "Search Courses Under A Tutor",
        "Search Tutor For A Course(T, P, L)",
        "List Tutors And Tutorial Groups For A Course",
        "List Courses For Each Tutor",
        "Filter Tutors Based On Criteria",
        "Generate Summary Report"
    };
    
    
    private String AssignmentTeam_Menu[] = {
        "Create Assignment Teams For A Tutorial Group",
        "Remove Assignment Team", 
        "Amend Assignment Team Details",
        "Add Students To Assignment Teams", 
        "Remove Students From Assignment Teams",
        "Merge Assignment Teams Based On Criteria", 
        "List Assignment Teams For A Tutorial Group",
        "List Students Under An Assignment Team", 
        "Generare Summary Report"
    };

    private String AssignmentAssessment_Menu[] = {
        "Create Assignment Rubric", 
        "Add Component Criteria To Assignment Rubric",
        "Remove Component Criteria From Assignment Rubric", 
        "Edit Assignment Rubric Component And Mark",
        "List Assignment Rubric Components", 
        "Grading Marks For Team Assignment",
        "Generate Mark List For Team Assignment", 
        "Generate Individual Students' Marks For A Tutorial Group",
        "Generate Summary Reports"
    };
    
    private String Search_Menu[] = {
        "Search By Select Student in the list", 
        "Search By Student ID"
    };
    
    private String Filter_Menu[] = {
        "Filter Student By Course",
        "Filter Student By Age",
        "Filter Student In Alphabetical Order"
    };
    
    private String Filter_Menu_Case1[] = {
        "Filter student only have Resit Course",
        "Filter student only have Repeat Course",
        "Filter student only have Elective Course"
    };
    
    private String studentSummary[] = {
      "Student Summary Report 1",
      "Student Summary Report 2"
    };

    
    
    /**
     * Displays the menu with its title.
     * @param menu  An array of menu items to be displayed.
     * @param title The title of the menu.
     */
    public void displayMenu(String[] menu, String title) {
        
        // Display the menu title
        System.out.print("\n\n  [" + title + "]\n\n");

        // Display menu items one by one with index
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
        System.out.print("\n*0 TO GO BACK");
        System.out.print("\nPLEASE ENTER YOUR CHOICE -> ");
    }
    
    public void displayMainMenu(){
        //Welcome Message
        System.out.println(header + "\nWelcome to Tunku Abdul Rahman University Management and Technology");
        displayMenu(MainSystems, "Main Menu");
    }
    
    public void displayStudentMenu(){
        displayMenu(Student_Menu, "Student Management");
    }
    
    public void displayCourseMenu(){
        displayMenu(Course_Menu, "Course Management");
    }
    
    public void displayProgrammeMenu(){
        displayMenu(Programme_Menu, "Programme Management");
    }
    
    public void displayTutorialGroupMenu(){
        displayMenu(TutorialGroup_Menu, "Tutorial Group Management");
    }
    
    public void displayTutorMenu(){
        displayMenu(Tutor_Menu, "Teaching Assignment Management");
    }
    
    public void displayAssignmenTeamMenu(){
        displayMenu(AssignmentTeam_Menu, "Assignment Team Management");
    }
    
    public void displayAssignmentAssessmentMenu(){
        displayMenu(AssignmentAssessment_Menu, "Assignment Assessment Management");
    }
    
    
    public void displayStudentSearch(){
        displayMenu(Search_Menu, "Search Student for registered course");
    }
    
    public void displayFilterMenu() {
        displayMenu(Filter_Menu,"Filter student");
    }
            
    public void displayFilterMenuCase1() {
        displayMenu(Filter_Menu_Case1,"Filter student");
    }
    
    public void displaySummaryStudent(){
        displayMenu(studentSummary, "Student Summary Report");
    }
    
}

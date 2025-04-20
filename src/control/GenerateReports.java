
package control;

import ADT.ArrayList;
import ADT.ListInterface;
import Entity.AssignmentTeam;
import Entity.Course;
import Entity.Programme;
import Entity.Student;
import Entity.Tutor;
import Entity.TutorialGroup;
import boundary.AssignmentTeamUI;
import boundary.CourseUI;
import boundary.ProgrammeUI;
import boundary.StudentUI;
import boundary.TutorUI;
import boundary.TutorialGroupUI;
import dao.CommonDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import utility.MessageUI;

/**
 *
 * @author Everyone
 */
public class GenerateReports {
    
    private final String UNIVERSISTY = "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY";
    
    
    //object for UI
    ProgrammeUI programmeUI = new ProgrammeUI();
    CourseUI courseUI = new CourseUI();
    AssignmentTeamUI assignmentTeamUI = new AssignmentTeamUI();
    TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
    StudentUI studentUI = new StudentUI();
    TutorUI tutorUI = new TutorUI();
    
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    
    //tutorial group list for all programme
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    private CommonDAO facultyDAO = new CommonDAO("faculty.txt");
    
    //programme dao
    private CommonDAO programmeDAO = new CommonDAO("programme.txt");
    
    //course dao
    private CommonDAO courseDAO = new CommonDAO("course.txt");
    
    private CommonDAO studentDAO = new CommonDAO("student.txt");
    
    private CommonDAO tutorDAO = new CommonDAO("tutor.txt");
    
    private CommonDAO tutorialGroupDAO = new CommonDAO("tutorialGroup.txt");

    public GenerateReports() {
        this.programmeList = programmeDAO.retrieveFromFile();
        this.courseList = courseDAO.retrieveFromFile();
        this.studentList = studentDAO.retrieveFromFile();
        this.tutorList = tutorDAO.retrieveFromFile();
        this.tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
    }
    
    public void generateHeader(String subsystem, String title, int reportLength){
        MessageUI.printLine();
        System.out.println(MessageUI.GetDivider('=', reportLength));

        // Calculate the number of spaces needed to center the subsystem text
        // Calculate the number of spaces needed to center the university name
        int spacesBeforeUniversity = (reportLength - UNIVERSISTY.length()) / 2;

        // Print the university name with the calculated spacing
        System.out.printf("%" + spacesBeforeUniversity + "s%s\n", "", UNIVERSISTY);

        // Calculate the number of spaces needed to center the subsystem text
        int spacesBeforeSubsystem = (reportLength - (subsystem.length() + 13)) / 2;

        System.out.printf("%" + spacesBeforeSubsystem + "s%s%s\n\n", "", subsystem.toUpperCase(), " SUBSYSTEM");

        // Calculate the number of spaces needed to center the title
        int spacesBeforeTitle = (reportLength - title.length()) / 2;

        // Print the title with the calculated spacing
        System.out.printf("%" + spacesBeforeTitle + "s%s\n", "", title);

        // Print the divider line below the title
        System.out.print(MessageUI.GetDivider(' ', spacesBeforeTitle));
        System.out.println(MessageUI.GetDivider('-', title.length())); 

        // Print the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, hh.mma", Locale.ENGLISH);
        String formattedDateTime = now.format(formatter);
        
        System.out.println("\nGenerated At -> " + formattedDateTime + "\n");
    }
    
    public void generateFooter(String title, int reportLength){
        String footerMsg = "END OF THE " + title;  
        
        int spacesBeforeTitle = (reportLength - footerMsg.length()) / 2;

        // Print the title with the calculated spacing
        System.out.printf("\n\n%" + spacesBeforeTitle + "s%s\n", "", footerMsg,title);
        
        System.out.println(MessageUI.GetDivider('=', reportLength));
    }

    public void courseReports1(){
        int reportSize = 115; 
        String title = "COURSE SUMMARY REPORT";
        CourseMaintenance Cm = new CourseMaintenance();
        
        generateHeader("COURSE MANAGEMENT",title, reportSize);
        
        courseUI.displayHeader();
        courseList.sort();
        courseList.list();
        
        System.out.println("\nTotal " + courseList.size() + " Courses ");
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nHIGHEST FACULTIES OFFERED : \n-> " + Cm.getMostFacultyOffered());
        
        System.out.println("\nLOWEST FACULTIES OFFERED : \n-> " + Cm.getLeastFacultyOffered());
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nHIGHEST PROGRAMME OFFERED : \n-> " + Cm.getMostProgrammeOffered());
        
        System.out.println("\nLOWEST PROGRAMME OFFERED : \n-> " + Cm.getLeastProgrammeOffered());
        
        
        generateFooter(title, reportSize);
    }
    
    public void tutGroupReports1(){
        int reportSize = 60; 
        String title = "TUTORIAL GROUP SUMMARY REPORT";
        TutorialGroupMaintenance TG = new TutorialGroupMaintenance();
        
        generateHeader("TUTORIAL GROUP MANAGEMENT",title, reportSize);
        
        tutorialGroupUI.displayHeader();
        tutorialGroupList.list();
        
        System.out.println("\nTotal " + tutorialGroupList.size() + " Tutorial Group ");
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nHIGHEST NUMBER OF STUDENT: \n-> " + TG.getMostStudentInTutGroup());
        
        System.out.println("\nLOWEST NUMBER OF STUDENT: \n-> " + TG.getLeastStudentInTutGroup());
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nThe percentage of Male and Female for each group:");
        for(int i = 1;i<=tutorialGroupList.size();i++){
            
            //if this group no student, skip
            if(tutorialGroupList.getEntry(i).getStudentList().isEmpty()){
                continue;
            }
            double malePercentage = TG.getGenderPercentage(tutorialGroupList.getEntry(i));
            double femalePercentage = 100 - malePercentage;
            System.out.printf("-> [%s], Male[%.2f%%], Female[%.2f%%]", tutorialGroupList.getEntry(i).getTutorialGroup(), malePercentage, femalePercentage);
            System.out.println(" ");
        }
        
        generateFooter(title, reportSize);
    }
        
    public void tutorReport(){
        int reportSize = 150; 
        String title = "ASSIGNMENT TEACHING SUMMARY REPORT";
        
        TutorMaintenance tm = new TutorMaintenance();
        
        generateHeader("ASSIGNMENT TEACHING MANAGEMENT", title, reportSize);
        
        tutorUI.displayHeader();
        tutorList.sort();
        tutorList.list();
        
        System.out.println("\nTotal " + tutorList.size() + " Tutors ");

        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nHIGHEST NUMBER OF TUTORIAL GROUP IN-CHARGE: \n-> " + tm.getMostTutorialGroup());
        
        System.out.println("\nLEAST NUMBER OF TUTORIAL GROUP IN-CHARGE: \n-> " + tm.getLeastTutorialGroup());
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        generateFooter(title, reportSize);
    }
    
    public void assignTeamReports1(int tutorialGroupIndex){
        int reportSize = 115; 
        String title = "ASSIGNMENT TEAM SUMMARY REPORT";
        
        AssignmentTeamMainteinance AT = new AssignmentTeamMainteinance();
        TutorialGroupMaintenance TG = new TutorialGroupMaintenance();
        
        TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
        
        generateHeader("ASSIGNMETN TEAM MANAGEMENT",title, reportSize);
        
        assignmentTeamUI.displayheader();
        tutorialGroup.getAssignmentTeamList().list();
        
        System.out.println("\nTotal " + tutorialGroup.getAssignmentTeamList().size() + " Assignment Team ");
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
        System.out.println("\nHIGHEST NUMBER OF ASSIGNMENT TEAM FOR AN TUTORIAL GROUP: \n-> " + AT.getMostAssignmentTeam());
        
        System.out.println("\nLEAST NUMBER OF ASSIGNMENT TEAM FOR AN TUTORIAL GROUP: \n-> " + AT.getLeastAssignmentTeam());
        
        System.out.println(MessageUI.GetDivider('-', reportSize));
        
//        for(int i = 1;i<=tutorialGroupList.size();i++){
//            System.out.printf("\nAverage Team Member for each group: \n-> [%s], [%.2f]", tutorialGroupList.getEntry(i).getTutorialGroup(), AT.getAverageTeamMember());
//            System.out.println(" ");
//        }
        
        generateFooter(title, reportSize);
    }        
    
    public void studentReport1(){
        int size = 150;
        String title = "STUDENT REGISTRATION SUMMARY REPORT";
        
        StudentMaintenance sm = new StudentMaintenance();
        
        generateHeader("STUDENT MANAGEMENT",title, size);
        
        studentUI.displayHeader();
        studentList.sort();
        studentList.list();
        
        System.out.println("Total " + studentList.size() + " Student in the University");
        
        System.out.println(MessageUI.GetDivider('-', size));
        
        System.out.println("\nThe Most Popular Course :");
        System.out.println(sm.getMaxCourseGroup());
        
        System.out.println("\nThe Least Popular Course :");
        System.out.println(sm.getMinCourseGroup());
        
        System.out.println(MessageUI.GetDivider('-', size));    
        
        generateFooter(title, size);
    }          
        
    public void studentReport2(){
        int size = 150;
        String title = "STUDENT REGISTRATION SUMMARY REPORT";
        
        StudentMaintenance sm = new StudentMaintenance();
        
        generateHeader("STUDENT MANAGEMENT",title, size);
        
        System.out.println("Total " + studentList.size() + " Student in the University");
        
        System.out.println(MessageUI.GetDivider('=', size));
        
        sm.calculateStudentFee();
        
        System.out.println(MessageUI.GetDivider('=', size));         
        generateFooter(title, size);
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
}

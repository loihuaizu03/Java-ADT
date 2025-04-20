package control;

import ADT.*;
import Entity.Course;
import Entity.Faculty;
import Entity.Programme;
import utility.MessageUI;
import boundary.CourseUI;
import boundary.FacultyUI;
import boundary.ProgrammeUI;
import dao.CommonDAO;

/**
 *
 * @author Chow Xing Shao
 */
public class CourseMaintenance {
    
    //programme list
    private ListInterface<Programme> programmeList = new ArrayList<>();
    //course list
    private ListInterface<Course> courseList = new ArrayList<>();
    //faculty list
    private ListInterface<Faculty> facultyList = new ArrayList<>();
    
    //programme dao
    private CommonDAO programmeDAO = new CommonDAO("programme.txt");
    //course dao
    private CommonDAO courseDAO = new CommonDAO("course.txt");
    //faculty dao
    private CommonDAO facultyDAO = new CommonDAO("faculty.txt");
    
    //object for UI
    ProgrammeUI programmeUI = new ProgrammeUI();
    CourseUI courseUI = new CourseUI();
    FacultyUI facultyUI = new FacultyUI();

    //constructor to retrieve file
    public CourseMaintenance() {
        this.programmeList = programmeDAO.retrieveFromFile();
        this.courseList = courseDAO.retrieveFromFile();
        this.facultyList = facultyDAO.retrieveFromFile();
    }
    
    public void runCourseMaintenance(){
        boolean end = false;
        
        do {     
            MessageUI.printLine();
            int choice = courseUI.getMenuChoice();
            switch (choice) {
                case 0:
                    end = true;
                    MessageUI.displayBackMessage();
                    break;
                    
                case 1:
                    MessageUI.printLine();
                    addProgrammeToCourse();
                    break;

                case 2:
                    MessageUI.printLine();
                    removeProgrammeFromCourse();
                    break;

                case 3:
                    MessageUI.printLine();
                    addNewCourse();
                    break;

                case 4:
                    MessageUI.printLine();
                    removeCourse();
                    break;
                    
                case 5:
                    MessageUI.printLine();
                    search();
                    break;
                    
                case 6:
                    MessageUI.printLine();
                    amend();
                    break;

                case 7:
                    MessageUI.printLine();
                    facultyUI.displayHeader();
                    facultyList.list();
                    
                    MessageUI.zeroPrompt();
                    int facultyIndex = facultyUI.chooseFaculty();
                    if(facultyIndex == 0){
                        MessageUI.displayBackMessage();
                        break;
                    }
                    
                    MessageUI.printLine();
                    Faculty faculty = facultyList.getEntry(facultyIndex);
                    
                    courseUI.displayCourseFromFaculty(faculty);
                    courseUI.displayHeader();
                    for(int i=1;i<programmeList.size();i++){
                        Programme programme = programmeList.getEntry(i);
                        if(programme.getFaculty().equals(faculty)){
                            programme.getCourseList().list();
                        }
                    }
                    
                    MessageUI.enterPrompt();
                    break;
                    
                case 8:
                    MessageUI.printLine();
                    programmeUI.displayheader();
                    programmeList.list();
                    MessageUI.zeroPrompt();
                    int programmeIndex = courseUI.chooseProgramme();
                    MessageUI.printLine();
                    listCoursesFromProgramme(programmeIndex);
                    MessageUI.enterPrompt();
                    break;
                    
                case 9:
                    //summary report
                    GenerateReports report = new GenerateReports();
                    
                    report.courseReports1();
                    MessageUI.enterPrompt();
                    break;

                default:
                    MessageUI.displayErrorMessage();
            }
        } while(!end);
    }
    
    //list all programmes
    public boolean listAllProgramme() {
        programmeUI.displayheader();
        if(programmeList.isEmpty()){
            programmeUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        programmeList.sort();
        programmeList.list();
        return programmeList.isEmpty();
    }
    
    public boolean listAllCourses(){
        courseUI.displayHeader();
        if(courseList.isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            courseList.sort();
            courseList.list();
        }
        return courseList.isEmpty();
    }
    
    //list courses taken by different faculties
    //list all courses for a programme
    public boolean listCoursesFromProgramme(int programmeIndex) {
        Programme programme = programmeList.getEntry(programmeIndex);
        
        courseUI.displayHeader();
        if(programme.getCourseList().isEmpty()){
            courseUI.displayEmpty();
        }
        
        //programme.getCourseList().sort();
        programme.getCourseList().list();
        return programme.getCourseList().isEmpty();
    }
    
    public boolean listProgrammeFromCourse(int courseIndex){
        programmeUI.displayheader();
        Course course = courseList.getEntry(courseIndex);
        if(course.getProgrammeList().isEmpty()){
            programmeUI.displayEmpty();
            return false;
        }
        else{
            course.getProgrammeList().list();
        }
        return course.getProgrammeList().isEmpty();
    }
    
    //add programme to course
    public void addProgrammeToCourse(){
        boolean back = false;
        boolean end = false;
        do {
            
            courseUI.displayCourseAvaiable();
            //list all course available
            if(listAllCourses()){
                back = true;
                break;
            }

            //get course
            MessageUI.zeroPrompt();
            int courseIndex = courseUI.chooseCourseToAdd();
            if(courseIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            Course course = courseList.getEntry(courseIndex);
            MessageUI.printLine();

            do{
                courseUI.displayAddingToCourse(course);
                programmeUI.displayheader();
                programmeList.list();

                //get programme 
                MessageUI.zeroPrompt();
                int programmeIndex = courseUI.chooseProgramme();
                if(programmeIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }
                
                Programme programme = programmeList.getEntry(programmeIndex);

                //add programme
                course.addProgramme(programme);

                MessageUI.printLine();
                MessageUI.displayAddSuccessful(programme.getProgrammeCode());
                courseDAO.saveToFile(courseList);
            }while(!end);
            
        }while(!back);
    }
    
    //remove programme from course
    public void removeProgrammeFromCourse(){
        boolean back = false;
        
        do { 
            
            courseUI.displayCourseAvaiable();
            //list all course available
            if(listAllCourses()){
                back = true;
                break;
            }

            //get course
            MessageUI.zeroPrompt();
            int courseIndex = courseUI.chooseCourseToRemove();
            if(courseIndex == 0){
                back = true;
                break;
            }
            MessageUI.printLine();
            
            Course course = courseList.getEntry(courseIndex);
            boolean end = false;
            do{
                courseUI.displayRemovingFromCourse(course);
                if(listProgrammeFromCourse(courseIndex)){
                    end = true;
                    break;
                }

                //get programme 
                MessageUI.zeroPrompt();
                int programmeIndex = programmeUI.chooseProgramme();
                if(programmeIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }

                Programme removedProgramme = course.removeProgramme(programmeIndex);

                MessageUI.printLine();
                MessageUI.displayRemoveSuccessful(removedProgramme.getProgrammeCode());
                courseDAO.saveToFile(courseList);
                
            }while(!end);
            
        }while(!back);
    }
    
    //add new course to programme
    public void addNewCourse(){
        boolean back = false;
        do {     
            courseUI.displayAddingCourse();
            
            if(listAllProgramme()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int programmeIndex = courseUI.chooseProgramme();
            if(programmeIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            Programme programme = programmeList.getEntry(programmeIndex);
            
            MessageUI.printLine();
            
            boolean end = false;
            do{
                courseUI.displayAddingTo(programme);
                Course course = courseUI.getCourseDetails();
                if(course == null){
                    end = true;
                    MessageUI.printLine();
                    break;
                }

                //add to programme list
                programmeList.getEntry(programmeIndex).addCourse(course);

                MessageUI.printLine();
                MessageUI.displayAddSuccessful(course.getCourseCode());
                programmeDAO.saveToFile(programmeList);

                //add to course list 
                courseList.add(course);
                courseDAO.saveToFile(courseList);
                
            }while(!end);
        }while(!back);
    }
    
    //remove course from programme
    public void removeCourse(){
        boolean back = false;
        do {     
            
            courseUI.displayRemovingCourse();
            if(listAllProgramme()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int programmeIndex = courseUI.chooseProgramme();
            if(programmeIndex==0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            MessageUI.printLine();
            Programme programme = programmeList.getEntry(programmeIndex);
            
            boolean end = false;
            do{
                courseUI.displayRemoveFrom(programme);
                if(listCoursesFromProgramme(programmeIndex)){
                    end = true;
                    MessageUI.enterPrompt();
                    break;
                }
                //get course
                int courseIndex = courseUI.chooseCourse();
                if(courseIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }

                Course removedCourse = programme.removeCourse(courseIndex);

                MessageUI.printLine();
                MessageUI.displayRemoveSuccessful(removedCourse.getCourseCode());

                programmeDAO.saveToFile(programmeList);
                
            }while(end);
        }while(!back);
    }
    
    //search courses offered in a semester
    public void search() {
        boolean back = true;
        
        ListInterface<Course> searchCourseList = new ArrayList<>();
        
        do {            
            courseUI.displaySearching();
            
            courseUI.displayHeader();
            courseList.sort();
            courseList.list();
           
            MessageUI.zeroPrompt();
            
            String keyword = courseUI.inputKeyWords();
            if(keyword.equals("0")){
                back =true;
                MessageUI.displayBackMessage();
                break;
            }
            
            keyword = keyword.toUpperCase();
            
            Course course = new Course(keyword, "", 0);
            
            if(courseList.contains(course) != null){
                searchCourseList.add(courseList.contains(course));
            }
            MessageUI.printLine();
            
            courseUI.displaySearchingFor(keyword);
            courseUI.displayHeader();
            if(searchCourseList.isEmpty()){
                MessageUI.displayResultNotFound();
                MessageUI.enterPrompt();
            }
            else{
                searchCourseList.list();
                MessageUI.enterPrompt();
            }
            
            searchCourseList.clear();
            MessageUI.printLine();
        } while (true);
    }
    
    
    //amend course details for a programme
    public void amend() {
        boolean back = false;
        do{
            if(listAllProgramme()){
                back = true;
                MessageUI.enterPrompt();
                break;
            }
            
            MessageUI.zeroPrompt();
            int programmeIndex = courseUI.chooseProgramme();
            if(programmeIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            MessageUI.printLine();
            
            Programme programme = programmeList.getEntry(programmeIndex);

            if(listCoursesFromProgramme(programmeIndex)){
                back = true;
                MessageUI.enterPrompt();
                break;
            }
            
            //get course
            MessageUI.zeroPrompt();
            int courseIndex = courseUI.chooseCourse();
            if(courseIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            MessageUI.printLine();
            
            Course course = programme.getCourseList().getEntry(courseIndex);
            
            //declare initial value in the list
            String courseCode = course.getCourseCode();
            String courseName = course.getCourseName();
            int creditHour = course.getCreditHour();
            int type = course.getType();
            
            boolean end = false;
            
            do{
                MessageUI.printLine();
                courseUI.displayCourseDetail(course);
                MessageUI.zeroPrompt();
                int courseDetail = courseUI.chooseDetail();
                switch(courseDetail){
                    case 0:
                        end = true;
                        MessageUI.displayBackMessage();
                        break;

                    //amend course code
                    case 1:
                        courseCode = courseUI.changeCourseCode(course);
                        course.setCourseCode(courseCode);
                        break;

                    //amend course name
                    case 2:
                        courseName = courseUI.changeCourseName(course);
                        course.setCourseName(courseName);
                        break;

                    //amend credit hour
                    case 3:
                        creditHour = courseUI.changeCreditHour(course);
                        course.setCreditHour(creditHour);
                        break;

                    //amend course type
                    case 4:
                        type = courseUI.changeType(course);
                        course.setType(type);
                        break;
                }
            }while(!end);
            
            //create a new course with amended detail
            Course replaceCourse = new Course(courseCode, courseName, type);
            
            //replace it to the list
            courseList.replace(courseIndex, replaceCourse);
            courseDAO.saveToFile(courseList);
            
            MessageUI.displayChangeSuccessful();
            
            
        }while(!back);
    }
    
    
    public String getMostFacultyOffered(){
        int max = courseList.getEntry(1).getFacultyCount();
        
        String courseID = null;
        String courseName = null;
        
        for(int i=1;i<=courseList.size();i++){
            Course course = courseList.getEntry(i);
            
            if(course != null){
                int facultyCount = course.getFacultyCount();
                
                if(max <= facultyCount){
                    max = facultyCount;
                    courseID = course.getCourseCode();
                    courseName = course.getCourseName();
                }
            }
        }
        
        return "[" + max + "] " + courseID + " " + courseName;
    }
    
    public String getLeastFacultyOffered(){
        int min = courseList.getEntry(1).getFacultyCount();
        
        String courseID = null;
        String courseName = null;
        
        for(int i=1;i<courseList.size()+1;i++){
            Course course = courseList.getEntry(i);
            
            if(course != null){
                int facultyCount = course.getFacultyCount();
                if(min >= facultyCount){
                    min = facultyCount;
                    courseID = course.getCourseCode();
                    courseName = course.getCourseName();
                }
            }
        }
        
        return "[" + min + "] " + courseID + " " + courseName + "\n";
    }
    
    public String getMostProgrammeOffered(){
        int max = courseList.getEntry(1).getProgrammeCount();
        
        String courseID = null;
        String courseName = null;
        
        for(int i=1;i<courseList.size()+1;i++){
            Course course = courseList.getEntry(i);
            
            if(courseList.getEntry(i) != null){
                int programmeCount = courseList.getEntry(i).getProgrammeCount();
                if(max <= programmeCount){
                    max = programmeCount;
                    courseID = course.getCourseCode();
                    courseName = course.getCourseName();
                }
            }
        }
        return "[" + max + "] " + courseID + " " + courseName;
    }
    
    public String getLeastProgrammeOffered(){
        int min = courseList.getEntry(1).getProgrammeCount();
        
        String courseID = null;
        String courseName = null;
        for(int i=1;i<courseList.size()+1;i++){
            Course course = courseList.getEntry(i);
            
            if(courseList.getEntry(i) != null){
                int programmeCount = courseList.getEntry(i).getProgrammeCount();
                if(min >= programmeCount){
                    min = programmeCount;
                    courseID = course.getCourseCode();
                    courseName = course.getCourseName();
                }
            }
        }
        return "[" + min + "] " + courseID + " " + courseName + "\n";
    }
    
    
    
    public static void main(String[] args) {
        CourseMaintenance cm = new CourseMaintenance();
        cm.runCourseMaintenance();
        
    }
}

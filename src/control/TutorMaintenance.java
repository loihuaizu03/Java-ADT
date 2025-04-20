
package control;

import ADT.ArrayList;
import ADT.ListInterface;
import Entity.Tutor;
import Entity.Course;
import Entity.TutorialGroup;
import boundary.CourseUI;
import boundary.TutorUI;
import boundary.TutorialGroupUI;
import dao.CommonDAO;
import utility.MessageUI;

/**
 *
 * @author Soh Kee Yhang
 */
public class TutorMaintenance {
    
    //tutor list
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    
    //tutorial group list
    private ListInterface<TutorialGroup> tutorialGroupList =new ArrayList<>();
    
    //course list
    private ListInterface<Course> courseList = new ArrayList<>();
    
    //dao
    private CommonDAO tutorDAO = new CommonDAO("tutor.txt");
    private CommonDAO tutorialGroupDAO = new CommonDAO("tutorialGroup.txt");
    private CommonDAO courseDAO = new CommonDAO("course.txt");
    
    //object for UI
    TutorUI tutorUI = new TutorUI();
    CourseUI courseUI = new CourseUI();
    TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
            

    public TutorMaintenance() {
        this.tutorList = tutorDAO.retrieveFromFile();
        this.tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
        this.courseList = courseDAO.retrieveFromFile();
    }
    
    public void runTutorMaintenance(){
        boolean end = false;
        do {     
            MessageUI.printLine();
            int choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayBackMessage();
                    end = true;
                    break;
                
                case 1:
                    MessageUI.printLine();
                    addNewTutor();
                    break;
                
                case 2:
                    MessageUI.printLine();
                    assignTutorToCourse();
                    break;

                case 3:
                    MessageUI.printLine();
                    assignGroupsToTutor();
                    break;
                    
                case 4:
                    MessageUI.printLine();
                    addTutorToGroupForCourse();
                    break;
                    
                case 5:
                    MessageUI.printLine();
                    searchCourseUnderTutor();
                    break;

                case 6:    
                    MessageUI.printLine();
                    searchTutorForCourse();
                    break; 
                    
                case 7:
                    MessageUI.printLine();
                    if(listAllCourses()){
                        MessageUI.enterPrompt();
                        break;
                    }
                    int courseIndex = tutorUI.chooseCourseToView();
                    MessageUI.printLine();
                    tutorUI.displayTutorFrom(courseList.getEntry(courseIndex));
                    if(listTutorAndGroupUnderCourse(courseIndex)){
                        break;
                    }
                    MessageUI.enterPrompt();
                    break;
                    
                case 8:
                    MessageUI.printLine();
                    if(listAllTutors()){
                        break;
                    }
                    int tutorIndex = tutorUI.chooseTutor();
                    MessageUI.printLine();
                    
                    Tutor tutor = tutorList.getEntry(tutorIndex);
                    tutorUI.displayCoursesFrom(tutor);
                    
                    if(listCoursesFromTutor(tutorIndex)){
                        break;
                    }
                    
                    MessageUI.enterPrompt();
                    break;
                    
                case 9:
                    MessageUI.printLine();
                    filter();
                    MessageUI.enterPrompt();
                    break;
                    
                case 10:
                    MessageUI.printLine();
                    GenerateReports reports = new GenerateReports();
                    reports.tutorReport();
                    MessageUI.enterPrompt();
                    
                    break;
                    
                default:
                    MessageUI.displayErrorMessage();
            }
        } while(!end);
    }
    
    public boolean listAllTutors(){
        tutorUI.displayHeader();
        if(tutorList.isEmpty()){
            tutorUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutorList.list();
        }
        
        return tutorList.isEmpty();
    }
    
    public boolean listAllTutorialGroup(){
        tutorialGroupUI.displayHeader();
        if(tutorialGroupList.isEmpty()){
            tutorialGroupUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutorialGroupList.list();
        }
        return tutorialGroupList.isEmpty();
    }
    
    public boolean listAllCourses(){
        courseUI.displayHeader();
        if(courseList.isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            courseList.list();
        }
        return courseList.isEmpty();
    }
    
    //list tutors for a course
    public boolean listCoursesFromTutor(int tutorIndex){
        courseUI.displayHeader();
        
        Tutor tutor = tutorList.getEntry(tutorIndex);
        
       
        if(tutor.getCourseList().isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutor.getCourseList().list();
        }
        
        return tutor.getCourseList().isEmpty();
    }
    
    //list tutor and tutorial group for each course
    public boolean listTutorAndGroupUnderCourse(int courseIndex){
        Course course = courseList.getEntry(courseIndex);
        
        if(course.getTutorList().isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            for(int i =0;i<course.getTutorList().size();i++){
                Tutor tutor = course.getTutorList().getEntry(i);
                if(tutor != null){
                    System.out.println("Tutor " + tutor.getId());
                    if(tutor.getTutorialGroupList() != null){
                        tutorialGroupUI.displayHeader();
                        tutor.getTutorialGroupList().list();
                        System.out.println("\n\n");
                    }
                    else{
                        System.out.println("-> This Tutor Have No Tutorial Group");
                    }
                    
                        
                        

                    
                }
            }
        }
        return tutorList.isEmpty();
    }
    
    public boolean listGroupsFromTutor(int tutorIndex){
        tutorialGroupUI.displayHeader();
        
        Tutor tutor = tutorList.getEntry(tutorIndex);
        
        if(tutor.getTutorialGroupList().isEmpty()){
            tutorialGroupUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutor.getTutorialGroupList().list();
        }
        return tutor.getTutorialGroupList().isEmpty();
    } 
    
    public boolean listTutorUnderCourse(int courseIndex){
        Course course = courseList.getEntry(courseIndex);
        tutorUI.displayHeader();
        
        if(course.getTutorList().isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        course.getTutorList().list();
        
        return course.getTutorList().isEmpty();
    }
    
    //add new tutor
    public void addNewTutor(){
        boolean back = false;
        do {   
            
            tutorUI.displayAddingTutor();
            listAllTutors();
            
            MessageUI.zeroPrompt();
            //declare student current id
            int currentID = 0;
            if(tutorList.isEmpty()){
                currentID = 1000;
            }
            else{
                String num = tutorList.getEntry(tutorList.size()).getId();
                //get id of previous added tutor
                currentID = Integer.parseInt(num.substring(1));
            }
            
            Tutor tutor = tutorUI.getTutorDetail(++currentID);
            if(tutor == null){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            tutorList.add(tutor);
            tutorDAO.saveToFile(tutorList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(tutor.getId());
            
        } while (!back);
    }
    
    //assign tutor to courses
    public void assignTutorToCourse(){
        boolean back = false;
        do {            
            if(listAllCourses()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int courseIndex = tutorUI.chooseCourseToAdd();
            if(courseIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            Course course = courseList.getEntry(courseIndex);
            
            tutorUI.displayAssignTutor(course);
            if(listAllTutors()){
                back = true;
                break;
            }
            
            int tutorIndex = tutorUI.chooseTutor();
            Tutor tutor = tutorList.getEntry(tutorIndex);
            
            tutor.addCourse(course);
            tutorDAO.saveToFile(tutorList);
            
            course.addTutor(tutor);
            courseDAO.saveToFile(courseList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(tutor.getId());
            
        } while (!back);
    }
    
    //assign tutorial groups to a tutor
    public void assignGroupsToTutor(){
        boolean back = false;
        do {            
            if(listAllTutors()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int tutorIndex = tutorUI.chooseTutorToAddGroup();
            if(tutorIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            Tutor tutor = tutorList.getEntry(tutorIndex);
            
            tutorUI.displayAssignGroup(tutor);
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            int groupIndex = tutorUI.chooseTutorialGroup();
            TutorialGroup tutorialGroup = tutor.getTutorialGroupList().getEntry(groupIndex);
            
            tutor.addTutorialGroup(tutorialGroup);
            tutorDAO.saveToFile(tutorList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(tutor.getId());
            
        } while (!back);
    }
    
    public void addTutorToGroupForCourse(){
        boolean back = false;
        do {            
            if(listAllCourses()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int courseIndex = tutorUI.chooseCourseToAddToGroup();
            if(courseIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            MessageUI.printLine();
            
            Course course = courseList.getEntry(courseIndex);
            
            if(listTutorUnderCourse(courseIndex)){
                back = true;
                break;
            }
            
            int tutorIndex = tutorUI.chooseTutor();
            Tutor tutor = tutorList.getEntry(tutorIndex);
            MessageUI.printLine();
            
            if(tutorialGroupList.isEmpty()){
                tutorialGroupUI.displayEmpty();
                MessageUI.enterPrompt();
                back = true;
                break;
            }
            
            tutorialGroupUI.displayHeader();
            tutorialGroupList.list();
            
            int tutorialGroupIndex = tutorialGroupUI.chooseTutorialGroup();
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            course.getTutorList().getEntry(tutorIndex).addTutorialGroup(tutorialGroup);
            
            courseDAO.saveToFile(courseList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(tutor.getId());
            
        } while (!back);
    }
    
    //search courses under a tutor
    public void searchCourseUnderTutor(){
        boolean back = false;
        
        ListInterface<Course> searchCourseList = new ArrayList<>();
        do {
            tutorUI.displaySearchCourse();
            
            if(listAllTutors()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int tutorIndex = tutorUI.chooseTutorToSearch();
            if(tutorIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            Tutor tutor = tutorList.getEntry(tutorIndex);
            
            tutorUI.displaySearchCourseFrom(tutor);
            
            MessageUI.zeroPrompt();
            String keyword = courseUI.inputKeyWords();
            
            keyword = keyword.toUpperCase();
            
            Course course = new Course(keyword, "", 0);
            if(tutor.getCourseList().contains(course) != null){
                searchCourseList.add(tutor.getCourseList().contains(course));
            }
            MessageUI.printLine();
            
            tutorUI.displaySearchingFor(keyword);
            
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
                    
        }while(!back);
    }
    
    //search tutor for a course
    public void searchTutorForCourse(){
        boolean back = false;
        
        ListInterface<Tutor> searchCourseList = new ArrayList<>();
        do {
            tutorUI.displaySearchTutor();
            
            if(listAllCourses()){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int courseIndex = tutorUI.chooseCourseToSearch();
            if(courseIndex == 0){
                back = true;
                break;
            }
            Course course = courseList.getEntry(courseIndex);
            
            MessageUI.printLine();
            tutorUI.displaySearchTutorFrom(course);
            
            String keyword = tutorUI.inputKeyWords();
            
            keyword = keyword.toUpperCase();
            
            Tutor tutor = new Tutor("",0,' ', "", "", "", 0, keyword);
            
            if(course.getTutorList().contains(tutor) != null){
                searchCourseList.add(course.getTutorList().contains(tutor));
            }
            MessageUI.printLine();
            
            courseUI.displaySearchingFor(keyword);
            tutorUI.displayHeader();
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
                    
        }while(!back);
    }
    
    //filter
    public void filter(){
        boolean back = false;
        do {
            tutorUI.displayFilter();
            tutorList.sort();
            tutorList.list();
            back = true;
            break;
        }while(!back);
    }
    
    //get most tutorial group incharge
    public String getMostTutorialGroup(){
        int most = 0;
        
        String tutorID = null;
        String tutorName = null;
        
        for(int i=1;i<=tutorList.size();i++){
            Tutor tutor = tutorList.getEntry(i);
            if(tutor.getTutorialGroupCount() >= most){
                most = tutor.getTutorialGroupCount();
                tutorID = tutor.getId();
                tutorName = tutor.getName();
            }
        }
        
        return "[" + most + "] " + tutorID + " " + tutorName + "\n";
        
    }
    
    //get least tutorial group incharge
    public String getLeastTutorialGroup(){
        int min = 100;
        
        String tutorID = null;
        String tutorName = null;
        
        for(int i=1;i<=tutorList.size();i++){
            Tutor tutor = tutorList.getEntry(i);
            if(tutor.getTutorialGroupCount() <= min){
                min = tutor.getTutorialGroupCount();
                tutorID = tutor.getId();
                tutorName = tutor.getName();
            }
        }
        
        return "[" + min + "] " + tutorID + " " + tutorName + "\n";
        
    }
    
    public static void main(String[] args) {
        TutorMaintenance tm = new TutorMaintenance();
        tm.runTutorMaintenance();
        
    }
    
    
    
}

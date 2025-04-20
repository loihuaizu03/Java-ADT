
package control;

import ADT.*;
import Entity.AssignmentTeam;
import Entity.*;
import Entity.Student;
import dao.CommonDAO;
import utility.MessageUI;
import boundary.AssignmentTeamUI;
import boundary.ProgrammeUI;
import boundary.StudentUI;
import boundary.TutorialGroupUI;

/**
 *
 * @author Varsyathini
 */
public class AssignmentTeamMainteinance {
    
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    private ListInterface<Student> studentList = new ArrayList<>();
            
    
    //dao
    private CommonDAO tutorialGroupDAO = new CommonDAO("tutorialGroup.txt");
    private CommonDAO studentDAO = new CommonDAO("studentList.txt");

    //object for UI
    AssignmentTeamUI assignmentTeamUI = new AssignmentTeamUI();
    TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
    StudentUI studentUI = new StudentUI();
    
    public AssignmentTeamMainteinance() {
        this.tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
        this.studentList = studentDAO.retrieveFromFile();
    }
    
    public void runAssignmentTeamMaintenance(){
        boolean end = false;
        MessageUI.printLine();
        do { 
            
            int choice = assignmentTeamUI.getMenuChoice();
            switch(choice){
                case 0:
                    MessageUI.displayBackMessage();
                    end = true;
                    break;
                    
                case 1:
                    MessageUI.printLine();
                    createAssignmentTeam();
                    break;

                case 2:
                    MessageUI.printLine();
                    removeAssignmentTeam();
                    break;
                    
                case 3:
                    MessageUI.printLine();
                    amend();
                    break;
                    
                case 4:
                    MessageUI.printLine();
                    addStudent();
                    break;  
                
                case 5:
                    MessageUI.printLine();
                    removeStudent();
                    break;  
                    
                case 6:
                    //merge assignment team
                    MessageUI.printLine();
                    mergeAssignmentTeam();
                    break;  
                    
                case 7:
                    MessageUI.printLine();
                    if(listAllTutorialGroup()){
                        break;
                    }
                    int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
                    if(tutorialGroupIndex == 0){
                        MessageUI.displayBackMessage();
                        break;
                    }
                    MessageUI.printLine();

                    if(!listTeamsUnderGroup(tutorialGroupIndex)){
                        MessageUI.enterPrompt();
                        MessageUI.printLine();
                        break;
                    }
                    
                    break;  
                    
                    
                case 8:
                    MessageUI.printLine();
                    if(listAllTutorialGroup()){
                        break;
                    }

                    //choose tutorial group
                    MessageUI.zeroPrompt();
                    int groupIndex = assignmentTeamUI.inputTutorialGroup();
                    if(groupIndex == 0){
                        MessageUI.displayBackMessage();
                        break;
                    }
                    MessageUI.printLine();
                    
                    TutorialGroup tutorialGroup = tutorialGroupList.getEntry(groupIndex);
                    if(listTeamsUnderGroup(groupIndex)){
                        MessageUI.displayBackMessage();
                        break;
                    }
                    MessageUI.zeroPrompt();
                    int teamIndex = assignmentTeamUI.chooseToView();
                    if(teamIndex == 0){
                        MessageUI.displayBackMessage();
                        break;
                    }
                    MessageUI.printLine();
                    
                    listStudentUnderTeam(groupIndex, teamIndex);
                    
                    MessageUI.enterPrompt();
                    break;
                    
                case 9:
                    MessageUI.printLine();
                    GenerateReports reports = new GenerateReports();
                    
                    listAllTutorialGroup();
                    
                    groupIndex = assignmentTeamUI.chooseGroupToGenerateTeamReport();
                    if(groupIndex == 0){
                        break;
                    }
                    
                    reports.assignTeamReports1(groupIndex);
                    MessageUI.enterPrompt();
                    MessageUI.printLine();
                    break;
                 
                    
                default:
                    MessageUI.displayErrorMessage();
            }
        }while(!end);
    }
    
    //list all tutorial group for all programme
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
    
    //list students from tutorial group
    public boolean listStudentsFromTutorialGroup(int tutorialGroupIndex){
        
        TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
        studentUI.displayHeader();
        
        if(tutorialGroup.getStudentList().isEmpty()){
            studentUI.displayEmpty();
        }
        else{
            tutorialGroup.getStudentList().list();
        }
        return tutorialGroup.getStudentList().isEmpty();
        
    }
    
    //list teams under tutorial group
    public boolean listTeamsUnderGroup(int tutorialGroupIndex){
        assignmentTeamUI.displayheader();
        
        TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);

        if (tutorialGroup.getAssignmentTeamList().isEmpty()) {
            assignmentTeamUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutorialGroup.getAssignmentTeamList().list();
        }
        
        return tutorialGroup.getAssignmentTeamList().isEmpty();
    }
    
    public boolean listStudentUnderTeam(int tutorialGroupIndex, int teamIndex){
        studentUI.displayHeader();
        
        TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
        AssignmentTeam assignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(teamIndex);
        
        if(assignmentTeam.getStudents().isEmpty()){
            studentUI.displayEmpty();
        }
        
        assignmentTeam.getStudents().list();
        
        return assignmentTeam.getStudents().isEmpty();
    }
    
    //create assignment team for a tutorial group
    public void createAssignmentTeam(){
        boolean back = false;
        do{
            assignmentTeamUI.displayAddingTeam();
            if(listAllTutorialGroup()){
                back = true;
                break;
                
            }

            //choose tutorial group to add
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            assignmentTeamUI.displayAddingTeamTo(tutorialGroup);
            
            AssignmentTeam assignmentTeam = assignmentTeamUI.getTeamDetail();
            
            tutorialGroupList.getEntry(tutorialGroupIndex).addAssignmentTeam(assignmentTeam);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(assignmentTeam.getTeamName());
            
            
        }while(!back);
    }
     
    //remove assignment team
    public void removeAssignmentTeam(){
        boolean back = false;
        do{
            assignmentTeamUI.displayRemovingTeam();
            if(listAllTutorialGroup()){
                back = true;
                break;
            }

            //choose tutorial group to add
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
            
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            boolean end = false;
            do{
                assignmentTeamUI.displayRemovingTeamFrom(tutorialGroup);
                if(listTeamsUnderGroup(tutorialGroupIndex)){
                    back = true;
                    MessageUI.enterPrompt();
                    break;
                }
                MessageUI.zeroPrompt();
                int teamIndex = assignmentTeamUI.chooseTeamToRemove();
                if(teamIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }
                
                MessageUI.printLine();
                MessageUI.displayRemoveSuccessful(tutorialGroup.removeAssignmentTeam(teamIndex));
                tutorialGroupDAO.saveToFile(tutorialGroupList);
            }while(!end);
        }while(!back);
    }
    
    //amend assignment team details
    public void amend() {
        boolean back = false;
        do{
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            //choose tutorial group to add
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
            
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            MessageUI.printLine();
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);

            if(listTeamsUnderGroup(tutorialGroupIndex)){
                back = true;
                break;
            }
            
            //get team
            MessageUI.zeroPrompt();
            int teamIndex = assignmentTeamUI.chooseAssignmentTeam();
            if(teamIndex == 0){
                back = true;
                break;
            }
            MessageUI.printLine();
            
            AssignmentTeam assignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(teamIndex);
            
            //declare initial value in the list
            String teamName = assignmentTeam.getTeamName();
            int maxStudent = assignmentTeam.getMaxStudents();
            
            boolean end = false;
            do{
                MessageUI.printLine();
                assignmentTeamUI.displayTeamDetail(assignmentTeam);
                MessageUI.zeroPrompt();
                int teamDetail = assignmentTeamUI.chooseDetail();
                switch(teamDetail){
                    case 0:
                        end = true;
                        MessageUI.displayBackMessage();
                        break;

                    //amend team name
                    case 1:
                        teamName = assignmentTeamUI.changeTeamName(assignmentTeam);
                        assignmentTeam.setTeamName(teamName);
                        break;

                    //amend max students
                    case 2:
                        maxStudent = assignmentTeamUI.changeMaxStudent(assignmentTeam);
                        assignmentTeam.setMaxStudents(maxStudent);
                        break;
                }
            }while(!end);
            
            AssignmentTeam replaceTeam = new AssignmentTeam(maxStudent, teamName);
            
            tutorialGroupList.getEntry(tutorialGroupIndex).getAssignmentTeamList().replace(teamIndex, replaceTeam);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            
            MessageUI.displayChangeSuccessful();
            
            
        }while(!back);
    }
    
    //add student to assignment team
    public void addStudent(){
        boolean back = false;
        do{
            
            if(listAllTutorialGroup()){
                back = true;
                break;
            }

            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
            
            
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            MessageUI.printLine();
            
            assignmentTeamUI.displayTeamsUnderGroup(tutorialGroup);
            if(listTeamsUnderGroup(tutorialGroupIndex)){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int teamIndex = assignmentTeamUI.chooseTeamAddStudent();
            if(teamIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            AssignmentTeam assignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(teamIndex);
            MessageUI.printLine();
            
            boolean end = false;
            do{
                assignmentTeamUI.displayAddingStudentTo(assignmentTeam);
                if(listStudentsFromTutorialGroup(tutorialGroupIndex)){
                    back = true;
                    break;
                }
                
                MessageUI.zeroPrompt();
                int studentIndex = assignmentTeamUI.chooseStudentToAdd();
                if(studentIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }

                Student student = tutorialGroup.getStudentList().getEntry(studentIndex);
                assignmentTeam.addStudent(student);
                tutorialGroupDAO.saveToFile(tutorialGroupList);

                MessageUI.printLine();
                MessageUI.displayAddSuccessful(student.getId());
                
            }while(!end);
        }while(!back);
    }
    
    //remove student from assignment team
    public void removeStudent(){
        boolean back = false;
        do{
            if(listAllTutorialGroup()){
                back = true;
                break;
            }

            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = assignmentTeamUI.inputTutorialGroup();
            
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            MessageUI.printLine();
            
            assignmentTeamUI.displayTeamsUnderGroup(tutorialGroup);
            if(listTeamsUnderGroup(tutorialGroupIndex)){
                back = true;
                break;
            }
            
            MessageUI.zeroPrompt();
            int teamIndex = assignmentTeamUI.chooseTeamRemoveStudent();
            if(teamIndex == 0){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            AssignmentTeam assignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(teamIndex);
            
            MessageUI.printLine();
            
            boolean end = false;
            do{
            
                assignmentTeamUI.displayRemovingStudentFrom(assignmentTeam);

                if(listStudentUnderTeam(tutorialGroupIndex, teamIndex)){
                    back = true;
                    MessageUI.enterPrompt();
                    break;
                }

                MessageUI.zeroPrompt();
                int studentIndex = assignmentTeamUI.chooseStudentToRemove();
                if(studentIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }
                
                MessageUI.printLine();
                MessageUI.displayRemoveSuccessful(assignmentTeam.removeStudent(studentIndex).getId());
                tutorialGroupDAO.saveToFile(tutorialGroupList);
                
            }while(!end);
        }while(!back);
    }
    
    //merge assignment team based on criteria
    public void mergeAssignmentTeam(){
        boolean back = false;
        do {
            assignmentTeamUI.displayMergeGroup();
            
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToMerge();
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            if(listTeamsUnderGroup(tutorialGroupIndex)){
                back= true;
                break;
            }
            
            int teamIndex = assignmentTeamUI.chooseTeamToMerge();
            
            AssignmentTeam assignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(teamIndex);
            
            AssignmentTeam lastAssignmentTeam = tutorialGroup.getAssignmentTeamList().getEntry(tutorialGroup.getAssignmentTeamList().size());
            
            
            for(int i=1;i<=lastAssignmentTeam.getStudents().size();i++){
                Student student = lastAssignmentTeam.getStudents().getEntry(i);
                assignmentTeam.addStudent(student);
            }
            MessageUI.printLine();
            MessageUI.displayMergeSuccessful();
            
            //remove last group in the list
            tutorialGroup.getAssignmentTeamList().remove(tutorialGroup.getAssignmentTeamList().size());
            
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            
            
            
            
        }while(!back);
    }
    
    
    public String getMostAssignmentTeam() {
        int max = tutorialGroupList.getEntry(1).getTeamCount();
        String assignmentTeam = null; 

        for (int i = 1; i <= tutorialGroupList.size(); i++) {
            if (tutorialGroupList.getEntry(i) != null) {
                int assignTeamCount = tutorialGroupList.getEntry(i).getTeamCount();
                if (max <= assignTeamCount) {
                    max = assignTeamCount;
                    assignmentTeam = tutorialGroupList.getEntry(i).getTutorialGroup();
                }
            }
        }
        return "[" + max + "] " + assignmentTeam;
    }
    
    public String getLeastAssignmentTeam() {
        int min = tutorialGroupList.getEntry(1).getTeamCount();
        String assignmentTeam = null; 
        
        for (int i = 1; i <= tutorialGroupList.size(); i++) {
            if (tutorialGroupList.getEntry(i) != null) {
                int assignTeamCount = tutorialGroupList.getEntry(i).getTeamCount();
                if (min >= assignTeamCount) {
                    min = assignTeamCount;
                    assignmentTeam = tutorialGroupList.getEntry(i).getTutorialGroup();
                }
            }
        }
        return "[" + min + "] " + assignmentTeam;
    }

    public double getAverageTeamMember() {
        double totalAverage = 0.0; // Variable to store the sum of all averages
        int count = 0; // Variable to count the number of tutorial groups with valid data

        for (int i = 1; i <= tutorialGroupList.size(); i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(i);
            if (tutorialGroup != null) {
                int totalMembers = 0;
                int numberOfTeams = tutorialGroup.getAssignmentTeamList().size();
        
                for (int j = 1; j <= numberOfTeams; j++) {
                    totalMembers += tutorialGroup.getAssignmentTeamList().getEntry(j).getStudents().size();
                }

                // Calculate the average only if there are teams in the tutorial group
                if (numberOfTeams > 0) {
                    double average = (double) totalMembers / numberOfTeams;
                    totalAverage += average; // Add the average to the total sum
                    count++; // Increment the count of tutorial groups
                }
            }
        }
        return totalAverage;
    }
    
    
    public static void main(String[] args) {
        AssignmentTeamMainteinance am = new AssignmentTeamMainteinance();
        am.runAssignmentTeamMaintenance();
        
    }
    
}
